// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_BlurHash
import java.util.Arrays
import com.jackmeng.stl.stl_BlurHash.blurhash_base83
import java.lang.IllegalArgumentException

/*----------------------------------------------------------- /
/ implementation of this: https://github.com/woltapp/blurhash /
/ in java                                                     /
/------------------------------------------------------------*//*-------------------------------------------------------- /
/ taken from the original Halcyon speed image manipulation /
/ library CloudSpin                                        /
/---------------------------------------------------------*/
/**
 * A blurring to hash implementation in Java
 *
 * @author Jack Meng
 */
object stl_BlurHash
{
	/**
	 * Represents the colors;
	 */
	val __ll=DoubleArray(256)
	
	init
	{
		for (i in __ll.indices)
		{
			val _m=i/255.0
			__ll[i]=if (com.jackmeng.stl._m<=0.04045) com.jackmeng.stl._m/12.92
			else Math.pow(
				(com.jackmeng.stl._m+0.055)/1.055 ,
				2.4
			)
		}
	}
	
	/**
	 * Finds a max value in an array (2D)
	 *
	 * @param val
	 * The array
	 * @return A max value
	 */
	fun max(`val`:Array<DoubleArray>):Double
	{
		var max=0.0
		for (doubles in `val`) for (aDouble in doubles) if (aDouble>max) max=aDouble
		return max
	}
	
	/**
	 * Converts the given number to be within the linear range
	 *
	 * @param val
	 * The number to convert
	 * @return The converted number
	 */
	fun to_linear(`val`:Int):Double
	{
		return if (`val`<0) __ll[0] else if (`val`>=256) __ll[255] else __ll[`val`]
	}
	
	/**
	 * Converts the given number to be within the sRGB range
	 *
	 * @param val
	 * The number to convert
	 * @return The converted number
	 */
	fun _as_linear(`val`:Double):Int
	{
		var _l=Arrays.binarySearch(__ll , `val`)
		if (_l<0) _l=_l.inv()
		return if (_l<0) 0 else if (_l>=256) 255 else _l
	}
	
	/**
	 * Encodes the given values into a BlurHash
	 *
	 * @param pixels
	 * The pixels to encode
	 * @param width
	 * The width of the image
	 * @param height
	 * The height of the image
	 * @param componentX
	 * The x-component of the center of the image
	 * @param componentY
	 * The y-component of the center of the image
	 * @return The encoded BlurHash as a String
	 */
	fun enc(pixels:IntArray , width:Int , height:Int , componentX:Int , componentY:Int):String
	{
		val factors=Array(componentX*componentY) { DoubleArray(3) }
		for (j in 0 until componentY)
		{
			for (i in 0 until componentX)
			{
				val normalisation:Double=if (i==0&&j==0) 1 else 2.toDouble()
				var r=0.0
				var g=0.0
				var b=0.0
				for (x in 0 until width)
				{
					for (y in 0 until height)
					{
						val basis=(normalisation*Math.cos(Math.PI*i*x/width)*Math.cos(Math.PI*j*y/height))
						val pixel=pixels[y*width+x]
						r+=basis*to_linear(pixel shr 16 and 0xff)
						g+=basis*to_linear(pixel shr 8 and 0xff)
						b+=basis*to_linear(pixel and 0xff)
					}
				}
				val scale=1.0/(width*height)
				val index=j*componentX+i
				factors[index][0]=r*scale
				factors[index][1]=g*scale
				factors[index][2]=b*scale
			}
		}
		val factorsLength=factors.size
		val hash=CharArray(1+1+4+2*(factorsLength-1))
		val sizeFlag=componentX.toLong()-1+(componentY-1)*9L
		blurhash_base83.encode(sizeFlag , 1 , hash , 0)
		val maximumValue:Double
		if (factorsLength>1)
		{
			val actualMaximumValue=max(factors)
			val quantisedMaximumValue=
				Math.floor(Math.max(0.0 , Math.min(82.0 , Math.floor(actualMaximumValue*166-0.5))))
			maximumValue=(quantisedMaximumValue+1)/166
			blurhash_base83.encode(Math.round(quantisedMaximumValue) , 1 , hash , 1)
		}
		else
		{
			maximumValue=1.0
			blurhash_base83.encode(0 , 1 , hash , 1)
		}
		val dc=factors[0]
		blurhash_base83.encode(blurhash_base83.encodeDC(dc) , 4 , hash , 2)
		for (i in 1 until factorsLength) blurhash_base83.encode(
			blurhash_base83.encodeAC(factors[i] , maximumValue) ,
			2 ,
			hash ,
			6+2*(i-1)
		)
		return String(hash)
	}
	
	/**
	 * Decodes the given BlurHash into an array of pixels
	 *
	 * @param blurHash
	 * The BlurHash to decode (String)
	 * @param width
	 * The width of the image
	 * @param height
	 * The height of the image
	 * @param punch
	 * The punch value of the image; often regarded as the
	 * "sharpness" of the image
	 * @return The decoded pixels
	 */
	fun dec(blurHash:String , width:Int , height:Int , punch:Double):IntArray
	{
		val blurHashLength=blurHash.length
		require(blurHashLength>=6) { "BlurHash must be at least 6 characters long" }
		val sizeInfo=blurhash_base83.decode(blurHash.substring(0 , 1))
		val sizeY=sizeInfo/9+1
		val sizeX=sizeInfo%9+1
		val quantMaxValue=blurhash_base83.decode(blurHash.substring(1 , 2))
		val rmV=(quantMaxValue+1)/166.0*punch
		val colors=Array(sizeX*sizeY) { DoubleArray(3) }
		blurhash_base83.decodeDC(blurHash.substring(2 , 6) , colors[0])
		for (i in 1 until sizeX*sizeY)
		{
			blurhash_base83.decodeAC(blurHash.substring(4+i*2 , 6+i*2) , rmV , colors[i])
		}
		val pixels=IntArray(width*height)
		var pos=0
		for (j in 0 until height)
		{
			for (i in 0 until width)
			{
				var r=0.0
				var g=0.0
				var b=0.0
				for (y in 0 until sizeY)
				{
					for (x in 0 until sizeX)
					{
						val basic=Math.cos(Math.PI*x*i/width)*Math.cos(Math.PI*y*j/height)
						val color=colors[x+y*sizeX]
						r+=color[0]*basic
						g+=color[1]*basic
						b+=color[2]*basic
					}
				}
				pixels[pos++]=
					255 shl 24 or (_as_linear(r) and 255 shl 16) or (_as_linear(g) and 255 shl 8) or (_as_linear(b) and 255)
			}
		}
		return pixels
	}
	
	object blurhash_base83
	{
		val TABLE=charArrayOf(
			'0' ,
			'1' ,
			'2' ,
			'3' ,
			'4' ,
			'5' ,
			'6' ,
			'7' ,
			'8' ,
			'9' ,
			'A' ,
			'B' ,
			'C' ,
			'D' ,
			'E' ,
			'F' ,
			'G' ,
			'H' ,
			'I' ,
			'J' ,
			'K' ,
			'L' ,
			'M' ,
			'N' ,
			'O' ,
			'P' ,
			'Q' ,
			'R' ,
			'S' ,
			'T' ,
			'U' ,
			'V' ,
			'W' ,
			'X' ,
			'Y' ,
			'Z' ,
			'a' ,
			'b' ,
			'c' ,
			'd' ,
			'e' ,
			'f' ,
			'g' ,
			'h' ,
			'i' ,
			'j' ,
			'k' ,
			'l' ,
			'm' ,
			'n' ,
			'o' ,
			'p' ,
			'q' ,
			'r' ,
			's' ,
			't' ,
			'u' ,
			'v' ,
			'w' ,
			'x' ,
			'y' ,
			'z' ,
			'#' ,
			'$' ,
			'%' ,
			'*' ,
			'+' ,
			',' ,
			'-' ,
			'.' ,
			':' ,
			';' ,
			'=' ,
			'?' ,
			'@' ,
			'[' ,
			']' ,
			'^' ,
			'_' ,
			'{' ,
			'|' ,
			'}' ,
			'~'
		)
		
		/**
		 * @param val
		 * @param exp
		 * @return double
		 */
		fun signpow(`val`:Double , exp:Double):Double
		{
			return Math.copySign(Math.pow(Math.abs(`val`) , exp) , `val`)
		}
		
		/**
		 * Encodes with Base 83.
		 *
		 * @param val
		 * The value to encode
		 * @param length
		 * The length of the value
		 * @param buff
		 * The buffer to write to (contains values)
		 * @param offset
		 * The offset to start writing at
		 */
		fun encode(`val`:Long , length:Int , buff:CharArray , offset:Int)
		{
			var _i=1
			for (i in 1..length)
			{
				val curr=`val`.toInt()/_i%83
				buff[offset+length-i]=TABLE[curr]
				_i*=83
			}
		}
		
		/**
		 * @param val
		 * @return long
		 */
		fun encodeDC(`val`:DoubleArray):Long
		{
			return ((_as_linear(`val`[0]).toLong() shl 16)+(_as_linear(`val`[1]).toLong() shl 8)+_as_linear(`val`[2]))
		}
		
		/**
		 * @param val
		 * @param m
		 * @return long
		 */
		fun encodeAC(`val`:DoubleArray , m:Double):Long
		{
			return Math.round(
					(Math.floor(
						Math.max(
							0.0 , Math.min(
								18.0 , Math.floor(
									signpow(
										`val`[0]/m , 0.5
									)*9+9.5
								)
							)
						)
					)*19*19)+(Math.floor(
						Math.max(
							0.0 , Math.min(
								18.0 , Math.floor(
									signpow(
										`val`[1]/m , 0.5
									)*9+9.5
								)
							)
						)
					)*19)+Math.floor(
						Math.max(
							0.0 , Math.min(
								18.0 , Math.floor(
									signpow(
										`val`[2]/m , 0.5
									)*9+9.5
								)
							)
						)
					)
				)
		}
		
		/**
		 * Decodes from Base 83
		 *
		 * @param str
		 * An Encoded String
		 * @return The decoded string from base 83
		 */
		fun decode(str:String):Int
		{
			var temp=0
			for (c in str.toCharArray())
			{
				val i=find(c)
				temp=temp*83+i
			}
			return temp
		}
		
		/**
		 * @param str
		 * @param rMv
		 * @param color
		 */
		fun decodeAC(str:String , rMv:Double , color:DoubleArray)
		{
			val aV=decode(str)
			val qR=aV/(19*19)
			val qG=aV/19%19
			val qB=aV%19
			color[0]=signpow((qR-9.0)/9.0 , 2.0)*rMv
			color[1]=signpow((qG-9.0)/9.0 , 2.0)*rMv
			color[2]=signpow((qB-9.0)/9.0 , 2.0)*rMv
		}
		
		/**
		 * @param str
		 * @param colors
		 */
		fun decodeDC(str:String , colors:DoubleArray)
		{
			val dV=decode(str)
			colors[0]=to_linear(dV shr 16)
			colors[1]=to_linear(dV shr 8 and 0xFF)
			colors[2]=to_linear(dV and 255)
		}
		
		/**
		 * @param c
		 * @return int
		 */
		fun find(c:Char):Int
		{
			for (i in TABLE.indices) if (TABLE[i]==c) return i
			return -1
		}
	}
}