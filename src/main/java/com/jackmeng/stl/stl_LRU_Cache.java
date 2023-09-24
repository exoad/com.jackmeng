// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.HashMap

class stl_LRU_Cache<K , V>(private val capacity:Int , numColors:Int , blockSize:Int)
{
	private val map:MutableMap<K? , Node<K? , V?>>
	private val tail:Node<K? , V?>
	private val numColors:Int
	
	// TODO: Change this to an AtomicReferenceArray instead of a primitive one.
	private val colorBlocks:Array<Array<Node<K? , V?>>>
	
	init
	{
		map=HashMap<K , Node<K , V>>(capacity)
		val head=Node<K? , V?>(null , null)
		tail=Node(null , null)
		head.next=tail
		tail.prev=head
		this.numColors=numColors
		colorBlocks=Array<Array<Node<* , *>>>(numColors) { arrayOfNulls<Node<* , *>>(blockSize) }
		for (i in 0 until numColors)
		{
			for (j in 0 until blockSize) colorBlocks[i][j]=Node<K? , V?>(null , null)
			colorBlocks[i][0].prev=head
			colorBlocks[i][blockSize-1].next=tail
			for (j in 0 until blockSize-1)
			{
				colorBlocks[i][j].next=colorBlocks[i][j+1]
				colorBlocks[i][j+1].prev=colorBlocks[i][j]
			}
		}
	}
	
	operator fun get(key:K):V?
	{
		val node=map[key] ?: return null
		removeNode(node)
		addToFront(node , getColor(key))
		return node.value
	}
	
	fun put(key:K , value:V)
	{
		var node=map[key]
		if (node!=null)
		{
			node.value=value
			removeNode(node)
			addToFront(node , getColor(key))
		}
		else
		{
			if (map.size==capacity) evict()
			node=Node(key , value)
			map[key]=node
			addToFront(node , getColor(key))
		}
	}
	
	private fun evict()
	{
		for (i in 0 until numColors)
		{
			if (colorBlocks[i][0].next!==tail)
			{
				val node=colorBlocks[i][0].next
				removeNode(node)
				map.remove(node!!.key)
				return
			}
		}
	}
	
	private fun removeNode(node:Node<K? , V?>?)
	{
		node!!.prev!!.next=node.next
		node.next!!.prev=node.prev
	}
	
	private fun addToFront(node:Node<K? , V?> , color:Int)
	{
		val tailPrev=tail.prev
		node.next=tail
		node.prev=tailPrev
		tailPrev!!.next=node
		tail.prev=node
		colorBlocks[color][0].prev=node
		node.nextBlock=colorBlocks[color][0]
		node.prevBlock=null
		colorBlocks[color][0]=node
	}
	
	private fun getColor(key:K):Int
	{
		return key.hashCode()%numColors
	}
	
	private class Node<K , V> internal constructor(var key:K , var value:V)
	{
		var prev:Node<K , V>?=null
		var next:Node<K , V>?=null
		
		// TODO: warning-unused
		var prevBlock:Node<K , V>?=null
		var nextBlock:Node<K , V>?=null
	}
}