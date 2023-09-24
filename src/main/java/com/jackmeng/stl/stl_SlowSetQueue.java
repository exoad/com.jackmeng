// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.Throws
import java.lang.UnsupportedOperationException
import java.util.*

class stl_SlowSetQueue<T>(override val size:Int):Queue<T>
{
	private val queue:Queue<T> =LinkedList()
	private val set:MutableSet<T> =HashSet()
	override fun add(t:T):Boolean
	{
		if (set.add(t)) queue.add(t)
		return true
	}
	
	override fun addAll(arg0:Collection<T>):Boolean
	{
		var ret=false
		for (t in arg0) if (set.add(t))
		{
			queue.add(t)
			ret=true
		}
		return ret
	}
	
	@Throws(NoSuchElementException::class)
	override fun remove():T
	{
		val ret=queue.remove()
		set.remove(ret)
		return ret
	}
	
	override fun remove(arg0:Any):Boolean
	{
		val ret=queue.remove(arg0)
		set.remove(arg0)
		return ret
	}
	
	override fun removeAll(arg0:Collection<*>?):Boolean
	{
		val ret=queue.removeAll(arg0)
		set.removeAll(arg0)
		return ret
	}
	
	override fun clear()
	{
		set.clear()
		queue.clear()
	}
	
	override operator fun contains(arg0:Any):Boolean
	{
		return set.contains(arg0)
	}
	
	override fun containsAll(arg0:Collection<*>?):Boolean
	{
		return set.containsAll(arg0)
	}
	
	override fun isEmpty():Boolean
	{
		return set.isEmpty()
	}
	
	override fun iterator():MutableIterator<T>
	{
		return queue.iterator()
	}
	
	override fun retainAll(arg0:Collection<*>?):Boolean
	{
		throw UnsupportedOperationException()
	}
	
	override fun size():Int
	{
		return queue.size
	}
	
	override fun toArray():Array<Any>
	{
		return queue.toTypedArray()
	}
	
	override fun <T> toArray(arg0:Array<T>?):Array<T>
	{
		return queue.toArray(arg0)
	}
	
	override fun element():T
	{
		return queue.element()
	}
	
	override fun offer(e:T):Boolean
	{
		return queue.offer(e)
	}
	
	override fun peek():T
	{
		return queue.peek()
	}
	
	override fun poll():T
	{
		return queue.poll()
	}
	
	override fun contains(element:T):Boolean
	{
		TODO("Not yet implemented")
	}
}