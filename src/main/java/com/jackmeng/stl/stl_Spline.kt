// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

class stl_Spline(private val x:List<Double> , private val y:List<Double>)
{
	private val n:Int=x.size
	private val z:DoubleArray
	
	init
	{
		val h=DoubleArray(n)
		val b=DoubleArray(n)
		val u=DoubleArray(n)
		val v=DoubleArray(n)
		z=DoubleArray(n+1)
		for (i in 0 until n-1)
		{
			h[i]=x[i+1]-x[i]
			b[i]=(y[i+1]-y[i])/h[i]
		}
		u[1]=2*(h[0]+h[1])
		v[1]=6*(b[1]-b[0])
		for (i in 2 until n-1)
		{
			u[i]=2*(h[i]+h[i-1])-h[i-1]*h[i-1]/u[i-1]
			v[i]=6*(b[i]-b[i-1])-h[i-1]*v[i-1]/u[i-1]
		}
		z[n]=0.0
		for (i in n-2 downTo 1) z[i]=(v[i]-h[i]*z[i+1])/u[i]
		z[0]=0.0
	}
	
	fun interpolate(t:Double):Double
	{
		var i=0
		var j=n-1
		while (j-i>1)
		{
			val k=(i+j)/2
			if (x[k]>t) j=k else i=k
		}
		val dx=t-x[i]
		return y[i]+dx*(z[i]/2+dx*(z[j]-z[i])/(6*(x[j]-x[i]))+(y[j]-y[i])/(x[j]-x[i]))
	}
}