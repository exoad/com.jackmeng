// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_KDTree.KDNode
import java.util.Comparator
import java.util.function.ToDoubleFunction

class stl_KDTree(points:List<DoubleArray>)
{
	class KDNode(var point:DoubleArray)
	{
		var left:KDNode?=null
		var right:KDNode?=null
	}
	
	private val root:KDNode?
	
	init
	{
		root=buildTree(points , 0)
	}
	
	private fun buildTree(points:List<DoubleArray> , depth:Int):KDNode?
	{
		if (points.isEmpty()) return null
		val k=points[0].size
		val axis=depth%k
		if (points.size==1) return KDNode(points[0])
		points.sort(Comparator.comparingDouble { a:DoubleArray-> a[axis] })
		val medianIndex=points.size/2
		val medianPoint=points[medianIndex]
		val node=KDNode(medianPoint)
		val leftPoints=points.subList(0 , medianIndex)
		val rightPoints=points.subList(medianIndex+1 , points.size)
		node.left=buildTree(leftPoints , depth+1)
		node.right=buildTree(rightPoints , depth+1)
		return node
	}
	
	fun nearestNeighbor(queryPoint:DoubleArray):KDNode?
	{
		return nearestNeighbor(root , queryPoint , root , 0)
	}
	
	private fun nearestNeighbor(currentNode:KDNode? , queryPoint:DoubleArray , bestNode:KDNode? , depth:Int):KDNode?
	{
		var bestNode=bestNode
		if (currentNode==null) return bestNode
		val bestDistance=distance(queryPoint , bestNode!!.point)
		val currentDistance=distance(queryPoint , currentNode.point)
		if (currentDistance<bestDistance) bestNode=currentNode
		val k=queryPoint.size
		val axis=depth%k
		val goodSide:KDNode?
		val badSide:KDNode?
		if (queryPoint[axis]<currentNode.point[axis])
		{
			goodSide=currentNode.left
			badSide=currentNode.right
		}
		else
		{
			goodSide=currentNode.right
			badSide=currentNode.left
		}
		bestNode=nearestNeighbor(goodSide , queryPoint , bestNode , depth+1)
		if (distance(queryPoint , bestNode!!.point)>Math.abs(queryPoint[axis]-currentNode.point[axis])) bestNode=
			nearestNeighbor(badSide , queryPoint , bestNode , depth+1)
		return bestNode
	}
	
	private fun distance(a:DoubleArray , b:DoubleArray):Double
	{
		var sum=0.0
		for (i in a.indices) sum+=(a[i]-b[i])*(a[i]-b[i])
		return Math.sqrt(sum)
	}
}