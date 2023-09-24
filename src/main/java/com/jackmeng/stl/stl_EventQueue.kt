// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Runnable
import java.util.LinkedList
import kotlin.jvm.Synchronized

class stl_EventQueue:Runnable
{
	private val eventQueue:LinkedList<Runnable>=LinkedList()
	private var isProcessingEvents:Boolean
	
	init
	{
		isProcessingEvents=false
	}
	
	@Synchronized
	fun add(event:Runnable)
	{
		eventQueue.add(event)
		if (!isProcessingEvents) run()
	}
	
	@Synchronized
	override fun run()
	{
		isProcessingEvents=true
		while (!eventQueue.isEmpty())
		{
			val event=eventQueue.remove()
			event.run()
		}
		isProcessingEvents=false
	}
}