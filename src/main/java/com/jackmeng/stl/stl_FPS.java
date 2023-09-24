// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Runnable
import java.util.Arrays
import java.lang.InterruptedException
import java.util.ArrayList
import java.util.function.Consumer

/**
 * This class is useful to represent FPS
 * Calling FPS.interrupt() represents a new
 * Frame has been drawn.
 *
 * @author Jack Meng
 */
class stl_FPS:Thread()
{
	var fPS=0.0
		private set
	var min=Double.MAX_VALUE
		private set
	var max=0.0
		private set
	private val listeners:MutableList<Runnable>=ArrayList()
	fun addUpdatePromise(vararg promises:Runnable?)
	{
		listeners.addAll(Arrays.asList(*promises))
	}
	
	private fun notifyPromises()
	{
		listeners.forEach(Consumer { obj:Runnable-> obj.run() })
	}
	
	override fun run()
	{
		while (true)
		{
			var last=System.nanoTime()
			try
			{
				sleep(1000L)
			} catch (e:InterruptedException)
			{ // IGNORED
			}
			fPS=1000000000.0/(System.nanoTime()-last)
			if (fPS>max) max=fPS
			if (fPS<min) min=fPS
			notifyPromises()
			last=System.nanoTime()
		}
	}
}