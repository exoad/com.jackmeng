// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Maths

class stl_Bezier(private val controlPoints:List<Double> , private val degree:Int)
{
	fun interpolate(t:Double):Double
	{
		var result=0.0
		for (i in 0..degree)
		{
			val controlPoint=controlPoints[i]
			val coefficient=(stl_Maths.binomial_coefficient(degree , i)*Math.pow(1-t , degree.toDouble()-i)*Math.pow(
				t ,
				i.toDouble()
			))
			result+=controlPoint*coefficient
		}
		return result
	}
}