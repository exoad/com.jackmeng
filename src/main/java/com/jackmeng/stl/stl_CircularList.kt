// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

class stl_CircularList<T>
{
	private var head:Node<T>?=null
	private var tail:Node<T>?=null
	
	private class Node<T>(var data:T)
	{
		var next:Node<T>?=null
	}
	
	fun add(data:T)
	{
		val newNode=Node(data)
		if (head==null)
		{
			head=newNode
			tail=newNode
			newNode.next=head
		}
		else
		{
			tail!!.next=newNode
			tail=newNode
			tail!!.next=head
		}
	}
	
	val isEmpty:Boolean
		get()=head==null
	
	fun size():Int
	{
		var size=0
		var currentNode=head
		do
		{
			size++
			currentNode=currentNode!!.next
		} while (currentNode!==head)
		return size
	}
	
	operator fun get(index:Int):T
	{
		var currentNode=head
		var currentIndex=0
		while (currentIndex<index)
		{
			currentNode=currentNode!!.next
			currentIndex++
		}
		return currentNode!!.data
	}
	
	fun remove(index:Int)
	{
		if (head==null) return
		if (index==0)
		{
			if (head===tail)
			{
				head=null
				tail=null
			}
			else
			{
				head=head!!.next
				tail!!.next=head
			}
			return
		}
		var currentNode=head
		var currentIndex=0
		while (currentIndex<index-1)
		{
			currentNode=currentNode!!.next
			currentIndex++
		}
		currentNode!!.next=currentNode.next!!.next
		if (currentNode.next==null) tail=currentNode
	}
}