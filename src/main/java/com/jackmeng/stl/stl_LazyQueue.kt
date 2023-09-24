// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.*

class stl_LazyQueue<T>
{
	private val queue:Queue<T> =LinkedList()
	private val lazyQueue:Queue<T> =LinkedList()
	fun enqueue(item:T)
	{
		lazyQueue.add(item)
	}
	
	fun dequeue():T
	{
		if (queue.isEmpty()) drainLazyQueue()
		return queue.poll()
	}
	
	fun peek():T
	{
		if (queue.isEmpty()) drainLazyQueue()
		return queue.peek()
	}
	
	val isEmpty:Boolean
		get()=queue.isEmpty()&&lazyQueue.isEmpty()
	
	fun size():Int
	{
		return queue.size+lazyQueue.size
	}
	
	private fun drainLazyQueue()
	{
		while (!lazyQueue.isEmpty()) queue.add(lazyQueue.poll())
	}
}