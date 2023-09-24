// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.awt.Color
import javax.swing.border.Border
import com.jackmeng.stl.stl_SwingHelper.``$rr_corner_border_01`
import java.awt.Component
import java.awt.Container
import java.util.ArrayList

object stl_SwingHelper
{
	fun rrect_border_uniform(round_factor:Int , color:Color? , thickness:Int):Border
	{
		return `$rr_corner_border_01`(round_factor , thickness , color)
	}
	
	fun rrect_clip(x:Int , y:Int , arc_w:Int , arc_h:Int , w:Int , h:Int):Shape
	{
		return RoundRectangle2D.Float(x , y , w , h , arc_w , arc_h)
	}
	
	fun default_gdev():GraphicsDevice
	{
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
	}
	
	fun default_gconf():GraphicsConfiguration
	{
		return default_gdev().getDefaultConfiguration()
	}
	
	fun acc_mem():Int
	{
		return default_gdev().getAvailableAcceleratedMemory()
	}
	
	val componentTreeWithInfo:struct_Pair<JTree , Map<String , Component>>
		get()
		{
			val rootNode=DefaultMutableTreeNode("Components")
			val componentInfoMap:MutableMap<String , Component>=HashMap()
			for (window in Window.getWindows()) traverseComponentHierarchy(window , rootNode , componentInfoMap)
			val componentTree=JTree(rootNode)
			componentTree.setRootVisible(false)
			return struct_Pair.make<JTree , Map<String , Component>>(componentTree , componentInfoMap)
		}
	
	private fun traverseComponentHierarchy(
			component:Component , node:DefaultMutableTreeNode , componentInfoMap:MutableMap<String , Component>
	)
	{
		val componentNode=DefaultMutableTreeNode(getComponentInfo(component))
		node.add(componentNode)
		componentInfoMap[component.name]=component
		if (component is Container) for (child in component.components) traverseComponentHierarchy(
			child ,
			componentNode ,
			componentInfoMap
		)
	}
	
	fun listComponents_OfContainer(c:Container):List<Component>
	{
		val comps=c.components
		val compList:MutableList<Component>=ArrayList()
		for (comp in comps)
		{
			compList.add(comp)
			if (comp is Container) compList.addAll(listComponents_OfContainer(comp))
		}
		return compList
	}
	
	private fun getComponentInfo(component:Component):String
	{
		val className=component.javaClass.simpleName
		val name=component.name
		val bounds:Rectangle=component.bounds
		val location=component.location
		return className+" - "+name+" - x: "+location.x+", y: "+location.y+", width: "+bounds.width+", height: "+bounds.height
	}
	
	private class `$rr_corner_border_01`(val radius:Int , val strokeThickness:Int , val color:Color? , myInsets:Insets?):
			Border
	{
		val i:Insets
		
		constructor(radius:Int , thickness:Int , color:Color?):this(radius , thickness , color , null)
		{
		}
		
		init
		{
			i=if (myInsets==null) Insets(
				Math.max(1 , strokeThickness-1) ,
				Math.max(1 , strokeThickness-1) ,
				Math.max(1 , strokeThickness-1) ,
				Math.max(1 , strokeThickness-1)
			)
			else myInsets
		}
		
		override fun paintBorder(c:Component , g:Graphics , x:Int , y:Int , width:Int , height:Int)
		{
			val g2:Graphics2D=g as Graphics2D
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON)
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL , RenderingHints.VALUE_STROKE_PURE)
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR)
			g2.setStroke(BasicStroke(strokeThickness))
			g2.setColor(color)
			g2.drawRoundRect(x , y , width , height , radius , radius)
			g2.dispose()
		}
		
		override fun getBorderInsets(c:Component):Insets
		{
			return i
		}
		
		override fun isBorderOpaque():Boolean
		{
			return true
		}
	}
}