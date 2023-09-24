// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.Graphics
import com.jackmeng.stl.stl_Images
import com.jackmeng.stl.stl_SwingHelper
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.Kernel
import java.awt.image.ConvolveOp
import java.awt.Color

object stl_Images
{
	fun awtimg2bi(i:Image):BufferedImage
	{
		if (i is BufferedImage) return i
		val img=BufferedImage(i.getWidth(null) , i.getHeight(null) , BufferedImage.TYPE_INT_ARGB)
		val g=img.graphics
		g.drawImage(i , 0 , 0 , null)
		g.dispose()
		compat_Img(img)
		return img
	}
	
	fun make_compatible(img:BufferedImage):BufferedImage
	{
		val dst=stl_SwingHelper.default_gconf().createCompatibleImage(
			img.width , img.height , img.transparency
		)
		val g2=dst.createGraphics()
		g2.drawImage(img , 0 , 0 , null)
		g2.dispose()
		make_compatible(img)
		return dst
	}
	
	fun compat_Img(e:BufferedImage):BufferedImage
	{
		val r=compat_Img(e.width , e.height , e)
		val g2=r.createGraphics()
		g2.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT , RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT)
		g2.drawImage(e , 0 , 0 , null)
		g2.dispose()
		return r
	}
	
	fun compat_Img(w:Int , h:Int , r:BufferedImage):BufferedImage
	{
		return stl_SwingHelper.default_gconf().createCompatibleImage(w , h , r.transparency)
	}
	
	fun gaussian_filter(img:BufferedImage? , radius:Int , sigma:Double):BufferedImage
	{
		val size=radius*2+1
		val data=FloatArray(size*size)
		var sum=0.0
		for (y in -radius..radius)
		{
			for (x in -radius..radius)
			{
				val value=Math.exp(-(x*x+y*y)/(2*sigma*sigma))
				val index=(y+radius)*size+(x+radius)
				data[index]=value.toFloat()
				sum+=value
			}
		}
		assert(sum!=0.0)
		for (i in data.indices) data[i]/=sum.toFloat()
		val kernel=Kernel(size , size , data)
		val op=ConvolveOp(kernel , ConvolveOp.EDGE_NO_OP , null)
		return op.filter(img , null)
	}
	
	fun mask(image:BufferedImage , mask:BufferedImage?):BufferedImage
	{
		val width=image.width
		val height=image.height
		val maskedImage=BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB)
		val g2d=maskedImage.createGraphics()
		g2d.drawImage(image , 0 , 0 , null)
		g2d.color=Color.BLACK
		g2d.drawImage(mask , 0 , 0 , null)
		g2d.dispose()
		return maskedImage
	}
	
	fun width_subimg(img:BufferedImage , newwidth:Int , newheight:Int):Image
	{
		return (if (img.width>img.height) img.getSubimage(
			img.width/2-img.height/2 ,
			0 ,
			img.height ,
			img.height
		)
		else img.getSubimage(0 , img.height/2-img.width/2 , img.width , img.width)).getScaledInstance(
				newwidth ,
				newheight ,
				Image.SCALE_AREA_AVERAGING
			)
	}
}