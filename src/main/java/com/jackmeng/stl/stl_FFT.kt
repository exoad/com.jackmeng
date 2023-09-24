// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Complex.plus
import com.jackmeng.stl.stl_Complex.times
import com.jackmeng.stl.stl_Complex.minus
import com.jackmeng.stl.stl_Complex
import java.lang.IllegalArgumentException
import com.jackmeng.stl.stl_FFT

object stl_FFT
{
	fun cooleytukey(x:Array<stl_Complex?>):Array<stl_Complex?>
	{
		val n=x.size
		if (n==1) return arrayOf(x[0])
		require(n%2==0) { "n is not a power of 2" }
		val even=arrayOfNulls<stl_Complex>(n/2)
		for (i in 0 until n/2) even[i]=x[2*i]
		val q=cooleytukey(even)
		for (i in 0 until n/2) even[i]=x[2*i+1]
		val r=cooleytukey(even)
		val y=arrayOfNulls<stl_Complex>(n)
		for (k in 0 until n/2)
		{
			val kth=-2*k*Math.PI/n
			val wk=stl_Complex(Math.cos(kth) , Math.sin(kth))
			y[k]=q[k]!!.plus(wk.times(r[k]!!))
			y[k+n/2]=q[k]!!.minus(wk.times(r[k]!!))
		}
		return y
	}
}