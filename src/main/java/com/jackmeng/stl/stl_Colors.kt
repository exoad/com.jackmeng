// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.awt.Color
import java.util.Optional

object stl_Colors
{
	/**
	 * Attempts to turn a HEX represented Color into the java.awt.Color object
	 * @param hex A HEX String
	 * @return A color object
	 * @see java.awt.Color
	 */
	fun hexToRGB(hex:String?):Color
	{
		var hex=hex!!
		if (!hex.startsWith("#")) hex="#$hex"
		return Color(
			Integer.valueOf(hex.substring(1 , 3) , 16) ,
			Integer.valueOf(hex.substring(3 , 5) , 16) ,
			Integer.valueOf(hex.substring(5 , 7) , 16)
		)
	}
	
	fun RGBAtoHex(alpha:Int , red:Int , green:Int , blue:Int):String
	{
		return String.format("#%02x%02x%02x%02x" , alpha , red , green , blue)
	}
	
	fun awt_ColorInvert(r:Color):Color
	{
		return Color((255f-r.red)/255f , (255f-r.green)/255f , (255f-r.blue)/255f , (255f-r.alpha)/255f)
	}
	
	fun averageOf(vararg colors:Color):Optional<Color>
	{
		if (colors.isEmpty()) return Optional.empty()
		else if (colors.size==1) return Optional.of(
			colors[0]
		)
		val sum=stl_Struct.make_quad(
			colors[0].red.toFloat() ,
			colors[0].green.toFloat() ,
			colors[0].red.toFloat() ,
			colors[0].alpha.toFloat()
		)
		var x=1
		while (x<colors.size)
		{
			sum.first+=colors[x].red.toFloat()
			sum.second+=colors[x].green.toFloat()
			sum.third+=colors[x].blue.toFloat()
			sum.fourth+=colors[x].alpha.toFloat()
			x++
		}
		return Optional.of(Color(sum.first/x , sum.second/x , sum.third/x , sum.fourth/x))
	}
}