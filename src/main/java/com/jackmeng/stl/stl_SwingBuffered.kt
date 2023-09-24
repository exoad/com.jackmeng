// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JComponent
import java.lang.Runnable
import java.util.concurrent.BlockingQueue
import java.awt.Image
import java.util.concurrent.LinkedBlockingQueue
import java.lang.InterruptedException
import java.awt.Graphics
import com.jackmeng.stl.stl_SwingBuffered.Drawable
import java.awt.Rectangle

class stl_SwingBuffered:JComponent() , Runnable
{
	@Transient
	private val queue:BlockingQueue<Runnable>
	
	@Transient
	private var buffer:Image?
	private var running:Boolean
	
	init
	{
		queue=LinkedBlockingQueue()
		buffer=null
		running=false
	}
	
	fun start()
	{
		if (!running)
		{
			running=true
			Thread(this).start()
		}
	}
	
	fun stop()
	{
		running=false
		queue.clear()
	}
	
	fun draw(task:Runnable)
	{
		queue.offer(task)
	}
	
	override fun run()
	{
		while (running)
		{
			try
			{
				val task=queue.take()
				task.run()
				repaint()
			} catch (e:InterruptedException)
			{
				e.printStackTrace()
			}
		}
	}
	
	override fun paintComponent(g:Graphics)
	{
		if (buffer==null) buffer=createImage(width , height)
		val bg=buffer!!.graphics
		bg.clearRect(0 , 0 , width , height)
		
		// Execute all tasks in the queue
		while (!queue.isEmpty())
		{
			val task=queue.poll()
			if (isVisible(task)) task.run()
		}
		super.paintComponent(bg)
		bg.dispose()
		g.drawImage(buffer , 0 , 0 , null)
	}
	
	private fun isVisible(task:Runnable):Boolean
	{
		if (task is Drawable)
		{
			val bounds=task.bounds
			return visibleRect.intersects(bounds)
		}
		return true
	}
	
	interface Drawable:Runnable
	{
		val bounds:Rectangle
	}
}