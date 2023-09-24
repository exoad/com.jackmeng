// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JPanel
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.Executors
import java.lang.Runnable
import java.util.concurrent.TimeUnit
import java.awt.Graphics
import java.awt.Color
import java.util.ArrayList
import java.util.function.Consumer

class stl_ControlledJPanel(private var fps:Int):JPanel()
{
	@Transient
	private var executor:ScheduledExecutorService
	
	@Transient
	private val fpsListeners:MutableList<Consumer<Float>> =ArrayList()
	
	@Transient
	private var scheduledFuture:ScheduledFuture<*>
	
	init
	{
		executor=Executors.newSingleThreadScheduledExecutor()
		scheduledFuture=
			executor.scheduleAtFixedRate({ this.repaint() } , 0 , (1000/fps).toLong() , TimeUnit.MILLISECONDS)
	}
	
	override fun paintComponent(g:Graphics)
	{
		super.paintComponent(g)
		g.color=Color.RED
		g.fillRect(0 , 0 , width , height)
		val currentFPS=1000f/scheduledFuture.getDelay(TimeUnit.MILLISECONDS)
		for (listener in fpsListeners) listener.accept(currentFPS)
	}
	
	fun target_fps(fps:Int)
	{
		this.fps=fps
		scheduledFuture.cancel(false)
		executor.shutdown()
		executor=Executors.newSingleThreadScheduledExecutor()
		scheduledFuture=
			executor.scheduleAtFixedRate({ this.repaint() } , 0 , (1000/fps).toLong() , TimeUnit.MILLISECONDS)
	}
	
	fun fps():Int
	{
		return fps
	}
	
	fun addFPSListener(listener:Consumer<Float>)
	{
		fpsListeners.add(listener)
	}
	
	fun removeFPSListener(listener:Consumer<Float>)
	{
		fpsListeners.remove(listener)
	}
}