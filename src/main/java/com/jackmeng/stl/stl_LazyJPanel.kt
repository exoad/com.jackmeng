// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JPanel
import java.awt.Graphics
import kotlin.jvm.Synchronized

abstract class stl_LazyJPanel protected constructor():JPanel()
{
	protected var construction=false
	protected val finished=true
	protected abstract fun constr()
	override fun paint(g:Graphics)
	{
		sync()
		super.paint(g)
	}
	
	override fun paintAll(g:Graphics)
	{
		sync()
		super.paintAll(g)
	}
	
	override fun paintComponents(g:Graphics)
	{
		sync()
		super.paintComponents(g)
	}
	
	override fun repaint()
	{
		sync()
		super.repaint()
	}
	
	override fun repaint(time:Long)
	{
		sync()
		super.repaint(time)
	}
	
	override fun repaint(x:Int , y:Int , x2:Int , y2:Int)
	{
		sync()
		super.repaint(x , y , x2 , y2)
	}
	
	override fun repaint(time:Long , x:Int , y:Int , x2:Int , y2:Int)
	{
		sync()
		super.repaint(time , x , y , x2 , y2)
	}
	
	override fun update(g:Graphics)
	{
		sync()
		super.update(g)
	}
	
	@Synchronized
	fun sync()
	{
		if (!construction&&parent!=null)
		{
			constr()
			construction=true
			validate()
		}
	}
}