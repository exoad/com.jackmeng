// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JTree
import javax.swing.border.Border
import javax.swing.tree.DefaultMutableTreeNode
import java.awt.*
import java.awt.geom.RoundRectangle2D

object stl_SwingHelper
{
	fun rrect_border_uniform(round_factor:Int , color:Color? , thickness:Int):Border
	{
		return `$rr_corner_border_01`(round_factor , thickness , color)
	}
	
	fun rrect_clip(x:Int , y:Int , arc_w:Int , arc_h:Int , w:Int , h:Int):Shape
	{
		return RoundRectangle2D.Float(x.toFloat() , y.toFloat() , w.toFloat() , h.toFloat() , arc_w.toFloat() ,
			arc_h.toFloat()
		)
	}
	
	fun default_gdev():GraphicsDevice
	{
		return GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice
	}
	
	fun default_gconf():GraphicsConfiguration
	{
		return default_gdev().defaultConfiguration
	}
	
	fun acc_mem():Int
	{
		return default_gdev().availableAcceleratedMemory
	}
	
	val componentTreeWithInfo:stl_Struct.struct_Pair<JTree , Map<String , Component>>
		get()
		{
			val rootNode=DefaultMutableTreeNode("Components")
			val componentInfoMap:MutableMap<String , Component> =HashMap()
			for (window in Window.getWindows()) traverseComponentHierarchy(window , rootNode , componentInfoMap)
			val componentTree=JTree(rootNode)
			componentTree.isRootVisible=false
			return stl_Struct.struct_Pair.make(componentTree , componentInfoMap)
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
		val compList:MutableList<Component> =ArrayList()
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
		
		init
		{
			i=myInsets ?: Insets(
				1.coerceAtLeast(strokeThickness-1) , 1.coerceAtLeast(strokeThickness-1) ,
				1.coerceAtLeast(strokeThickness-1) , 1.coerceAtLeast(strokeThickness-1)
			)
		}
		
		override fun paintBorder(c:Component , g:Graphics , x:Int , y:Int , width:Int , height:Int)
		{
			val g2:Graphics2D=g as Graphics2D
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON)
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL , RenderingHints.VALUE_STROKE_PURE)
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR)
			g2.stroke=BasicStroke(strokeThickness.toFloat())
			g2.color=color
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