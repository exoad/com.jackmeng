// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_BlurHash_2
import kotlin.math.*
import java.awt.Color
import java.math.BigInteger
import java.lang.StringBuilder

object stl_BlurHash_2
{
	fun encode(pixels:IntArray , width:Int , height:Int , componentX:Int , componentY:Int):String
	{
		val numComponents=componentX*componentY
		val colors=IntArray(numComponents)
		val colorCoords=Array(numComponents) { DoubleArray(2) }
		for (y in 0 until componentY)
		{
			for (x in 0 until componentX)
			{
				val index=y*componentX+x
				val coords=getColorCoordinates(x , y , componentX , componentY)
				colorCoords[index]=coords
				var rAcc=0.0
				var gAcc=0.0
				var bAcc=0.0
				for (j in 0 until height)
				{
					for (i in 0 until width)
					{
						val s=(if (x==0) 1 else 2)*cos(Math.PI*i/width)
						val t=(if (y==0) 1 else 2)*cos(Math.PI*j/height)
						val factor=s*t/width/height
						val pixelIndex=j*width+i
						val c=Color(pixels[pixelIndex] , true)
						rAcc+=factor*c.red
						gAcc+=factor*c.green
						bAcc+=factor*c.blue
					}
				}
				val r=rAcc.roundToInt()
				val g=gAcc.roundToInt()
				val b=bAcc.roundToInt()
				colors[index]=packColor(r , g , b)
			}
		}
		val colorValues=arrayOfNulls<BigInteger>(numComponents)
		val maxColorValue=BigInteger.valueOf(1L shl 24).subtract(BigInteger.ONE)
		for (i in 0 until numComponents)
		{
			val color=colors[i]
			val r=color shr 16 and 0xff
			val g=color shr 8 and 0xff
			val b=color and 0xff
			val value=BigInteger.valueOf(encodeColor(r , maxColorValue).toLong()).shiftLeft(16)
				.or(BigInteger.valueOf(encodeColor(g , maxColorValue).toLong())).shiftLeft(16)
				.or(BigInteger.valueOf(encodeColor(b , maxColorValue).toLong()))
			colorValues[i]=value
		}
		val size=getSize(componentX , componentY)
		return encodeBlurhash(size[0] , size[1] , colorCoords , colorValues)
	}
	
	private fun getColorCoordinates(x:Int , y:Int , componentX:Int , componentY:Int):DoubleArray
	{
		val result=DoubleArray(2)
		result[0]=(2*x+1)/(2.0*componentX)
		result[1]=(2*y+1)/(2.0*componentY)
		return result
	}
	
	private fun packColor(r:Int , g:Int , b:Int):Int
	{
		return r shl 16 or (g shl 8) or b
	}
	
	private fun encodeColor(color:Int , maxColorValue:BigInteger):Int
	{
		val quantized=BigInteger.valueOf(color.toLong()).multiply(BigInteger.valueOf(69L))
			.add(maxColorValue.divide(BigInteger.valueOf(2L))).divide(maxColorValue)
		return quantized.toInt()
	}
	
	private fun getSize(componentX:Int , componentY:Int):IntArray
	{
		val maxDimension=componentX.coerceAtLeast(componentY)
		val smallDimension=componentX.coerceAtMost(componentY)
		val x=ceil(sqrt(maxDimension*smallDimension.toDouble())).toInt()
		val y=ceil(maxDimension.toDouble()*smallDimension/x).toInt()
		return intArrayOf(x , y)
	}
	
	private fun encodeBlurhash(width:Int , height:Int , colorCoords:Array<DoubleArray> , colorValues:Array<BigInteger?>):String
	{
		val numComponents=colorCoords.size
		val sizeFlag=
			0.0.coerceAtLeast(9.0.coerceAtMost(floor(ln(width.toDouble())/ln(2.0)-1))).toInt()+0.0.coerceAtLeast(
				9.0.coerceAtMost(
					floor(
						ln(height.toDouble())/ln(2.0)-1
					)
				)
			).toInt()*9
		val builder=StringBuilder()
		builder.append(getBase83(sizeFlag , 1))
		for (i in 0 until numComponents)
		{
			val value=colorValues[i]
			val coords=colorCoords[i]
			val quantizedX=0.0.coerceAtLeast(18.0.coerceAtMost(floor(coords[0]*19))).toInt()
			val quantizedY=0.0.coerceAtLeast(18.0.coerceAtMost(floor(coords[1]*19))).toInt()
			val quantized=quantizedY*19+quantizedX
			builder.append(getBase83(quantized , 1))
			builder.append(getBase83(value!!.toInt() , 4))
		}
		return builder.toString()
	}
	
	private val BASE83_ALPHABET=
		"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#$%*+,-.:;=?@[]^_{|}~".toCharArray()
	
	private fun getBase83(value:Int , numChars:Int):String
	{
		var value=value
		val chars=CharArray(numChars)
		for (i in numChars-1 downTo 0)
		{
			chars[i]=BASE83_ALPHABET[value%83]
			value/=83
		}
		return String(chars)
	}
}