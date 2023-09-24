// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.awt.image.BufferedImage
import java.awt.Color

class stl_RayCaster(
		private val screenWidth:Int , private val screenHeight:Int , private val playerPos:DoubleArray , private val playerAngle:Double , private val fov:Double , private val map:Array<DoubleArray>
)
{
	fun castRays():BufferedImage
	{
		val image=BufferedImage(screenWidth , screenHeight , BufferedImage.TYPE_INT_RGB)
		val angleStep=fov/screenWidth
		var currentAngle=playerAngle-fov/2
		for (x in 0 until screenWidth)
		{
			val rayAngle=currentAngle*Math.PI/180
			var distanceToWall=0.0
			var hitWall=false
			val rayPos=doubleArrayOf(playerPos[0] , playerPos[1])
			while (!hitWall&&distanceToWall<map.size)
			{
				distanceToWall+=0.1
				val testX=(rayPos[0]+Math.sin(rayAngle)*distanceToWall).toInt()
				val testY=(rayPos[1]+Math.cos(rayAngle)*distanceToWall).toInt()
				if (testX<0||testX>=map[0].size||testY<0||testY>=map.size)
				{
					hitWall=true
					distanceToWall=map.size.toDouble()
				}
				else if (map[testY][testX]==1.0)
				{
					hitWall=true
				}
			}
			assert(distanceToWall!=0.0)
			val ceiling=(screenHeight/2.0-screenHeight/distanceToWall).toInt()
			val floor=screenHeight-ceiling
			for (y in 0 until screenHeight)
			{
				if (y<ceiling) image.setRGB(x , y , Color.BLUE.rgb)
				else if (y>floor) image.setRGB(
					x ,
					y ,
					Color.GREEN.rgb
				)
				else image.setRGB(x , y , Color.WHITE.rgb)
			}
			currentAngle+=angleStep
		}
		return image
	}
	
	companion object
	{
		fun raycast(
				map:Array<IntArray> , posX:Double , posY:Double , dirX:Double , dirY:Double , planeX:Double , planeY:Double , pixels:IntArray , width:Int , height:Int , color:Color
		)
		{
			for (x in 0 until width)
			{
				val cameraX=2*x/width.toDouble()-1
				val rayDirX=dirX+planeX*cameraX
				val rayDirY=dirY+planeY*cameraX
				var mapX=posX.toInt()
				var mapY=posY.toInt()
				var sideDistX:Double
				var sideDistY:Double
				val integral_Dist_X=0.1f
				val deltaDistX=Math.abs(integral_Dist_X/rayDirX)
				val deltaDistY=Math.abs(1/rayDirY)
				var perpWallDist:Double
				var stepX:Int
				var stepY:Int
				var hit=false
				var side=0
				if (rayDirX<0)
				{
					stepX=-1
					sideDistX=(posX-mapX)*deltaDistX
				}
				else
				{
					stepX=1
					sideDistX=(mapX+1.0-posX)*deltaDistX
				}
				if (rayDirY<0)
				{
					stepY=-1
					sideDistY=(posY-mapY)*deltaDistY
				}
				else
				{
					stepY=1
					sideDistY=(mapY+1.0-posY)*deltaDistY
				}
				while (!hit)
				{
					if (sideDistX<sideDistY)
					{
						sideDistX+=deltaDistX
						mapX+=stepX
						side=0
					}
					else
					{
						sideDistY+=deltaDistY
						mapY+=stepY
						side=1
					}
					if (map[mapX][mapY]>0) hit=true
				}
				perpWallDist=if (side==0) (mapX-posX+(1.0-stepX)/2)/rayDirX else (mapY-posY+(1.0-stepY)/2)/rayDirY
				val lineHeight=(height/perpWallDist).toInt()
				var drawStart=-lineHeight/2+height/2
				if (drawStart<0) drawStart=0
				var drawEnd=lineHeight/2+height/2
				if (drawEnd>=height) drawEnd=height-1
				for (y in drawStart..drawEnd)
				{
					val colorValue=color.rgb
					pixels[x+y*width]=colorValue
				}
			}
		}
	}
}