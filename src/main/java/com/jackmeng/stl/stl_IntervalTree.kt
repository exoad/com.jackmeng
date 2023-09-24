// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.ArrayList

class stl_IntervalTree
{
	class Interval_Node(var interval:stl_Int_Interval)
	{
		var max:Int=interval.high
		var left:Interval_Node?=null
		var right:Interval_Node?=null
		
	}
	
	private var root:Interval_Node?=null
	fun insert(interval:stl_Int_Interval)
	{
		root=insert(root , interval)
	}
	
	private fun insert(node:Interval_Node? , interval:stl_Int_Interval):Interval_Node
	{
		if (node==null) return Interval_Node(interval)
		val low=interval.low
		if (low<node.interval.low) node.left=insert(node.left , interval) else node.right=insert(node.right , interval)
		if (node.max<interval.high) node.max=interval.high
		return node
	}
	
	fun search(point:Int):List<stl_Int_Interval>
	{
		val results:MutableList<stl_Int_Interval> =ArrayList()
		search(root , point , results)
		return results
	}
	
	private fun search(node:Interval_Node? , point:Int , results:MutableList<stl_Int_Interval>)
	{
		if (node==null) return
		if (point>=node.interval.low&&point<=node.interval.high) results.add(node.interval)
		if (node.left!=null&&point<=node.left!!.max) search(node.left , point , results)
		if (node.right!=null&&point<=node.right!!.max) search(node.right , point , results)
	}
}