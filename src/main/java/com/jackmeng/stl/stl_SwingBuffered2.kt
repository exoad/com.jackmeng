// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.swing.JComponent
import java.awt.RenderingHints
import java.awt.Graphics

class stl_SwingBuffered2(width:Int , height:Int)
{
	private var buffer:BufferedImage
	private var bufferGraphics:Graphics2D
	private var isDirty=true
	
	constructor(component:JComponent):this(component.width , component.height)
	{
	}
	
	init
	{
		buffer=BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB)
		bufferGraphics=buffer.createGraphics()
		bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON)
		bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
	}
	
	val graphics:Graphics2D
		get()
		{
			isDirty=true
			return bufferGraphics
		}
	
	fun paintComponent(g:Graphics)
	{
		if (isDirty)
		{
			val g2=g as Graphics2D
			g2.drawImage(buffer , 0 , 0 , null)
			isDirty=false
		}
	}
	
	fun setSize(width:Int , height:Int)
	{
		bufferGraphics.dispose()
		buffer.flush()
		buffer=BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB)
		bufferGraphics=buffer.createGraphics()
		bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON)
		bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
		isDirty=true
	}
}