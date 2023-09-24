// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JPanel
import java.awt.Graphics
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.Executors
import java.lang.Runnable
import java.util.concurrent.TimeUnit
import javax.swing.SwingUtilities
import java.util.ArrayList
import java.util.function.Consumer

class stl_ControlledJPanel_2:JPanel()
{
	@Transient
	private val drawingMethods:MutableList<Consumer<Graphics>>=ArrayList()
	
	@Transient
	private val fpsListeners:MutableList<Consumer<Float>>=ArrayList()
	private val startTime:Long
	private var frameCount:Long=0
	
	init
	{
		val executor=Executors.newSingleThreadScheduledExecutor()
		executor.scheduleAtFixedRate({ redraw() } , 0 , 16 , TimeUnit.MILLISECONDS)
		startTime=System.nanoTime()
	}
	
	private fun redraw()
	{
		frameCount++
		val elapsed=System.nanoTime()-startTime
		val fps=(frameCount*1e9/elapsed).toFloat()
		SwingUtilities.invokeLater { fpsListeners.forEach(Consumer { x:Consumer<Float>-> x.accept(fps) }) }
		repaint()
	}
	
	fun addDrawingMethod(method:Consumer<Graphics>)
	{
		drawingMethods.add(method)
	}
	
	fun removeDrawingMethod(method:Consumer<Graphics>)
	{
		drawingMethods.remove(method)
	}
	
	fun addFPSListener(listener:Consumer<Float>)
	{
		fpsListeners.add(listener)
	}
	
	fun removeFPSListener(listener:Consumer<Float>)
	{
		fpsListeners.remove(listener)
	}
	
	override fun paintComponent(g:Graphics)
	{
		super.paintComponent(g)
		drawingMethods.forEach(Consumer { x:Consumer<Graphics>-> x.accept(g) })
	}
}