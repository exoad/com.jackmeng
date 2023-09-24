// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Complex
import java.util.Objects

class stl_Complex(private var real:Double , private var imaginary:Double)
{
	fun real():Double
	{
		return real
	}
	
	fun imaginary():Double
	{
		return imaginary
	}
	
	fun real(r:Double)
	{
		real=r
	}
	
	fun imaginary(i:Double)
	{
		imaginary=i
	}
	
	fun re():Double
	{
		return real()
	}
	
	fun im():Double
	{
		return imaginary()
	}
	
	operator fun plus(stl_complex:stl_Complex):stl_Complex
	{
		return stl_Complex(this.real()+stl_complex.real() , this.imaginary()+stl_complex.imaginary())
	}
	
	operator fun minus(stl_complex:stl_Complex):stl_Complex
	{
		return stl_Complex(this.real()-stl_complex.real() , this.imaginary()-stl_complex.imaginary())
	}
	
	fun `$minus0`(stl_complex:stl_Complex):stl_Complex
	{
		return plus(stl_Complex(-stl_complex.real() , -stl_complex.imaginary()))
	}
	
	operator fun times(stl_complex:stl_Complex):stl_Complex
	{
		return stl_Complex(
			this.real()*stl_complex.real()-this.imaginary()*stl_complex.imaginary() ,
			this.real()*this.imaginary()-stl_complex.real()*stl_complex.imaginary()
		)
	}
	
	fun divides(stl_complex:stl_Complex):stl_Complex
	{
		return this.times(stl_complex.recp())
	}
	
	fun abs():Double
	{
		return Math.hypot(imaginary , real)
	}
	
	fun sin():stl_Complex
	{
		return stl_Complex(
			Math.sin(real)*Math.cosh(imaginary) , Math.cos(real)*Math.sinh(
				imaginary
			)
		)
	}
	
	fun cos():stl_Complex
	{
		return stl_Complex(
			Math.cos(real)*Math.cosh(imaginary) , -Math.sin(real)*Math.sinh(
				imaginary
			)
		)
	}
	
	fun recp():stl_Complex
	{
		return stl_Complex(
			real/(real*real+imaginary*imaginary) , imaginary/(real*real+imaginary*imaginary)
		)
	}
	
	fun conj():stl_Complex
	{
		return stl_Complex(real , -imaginary)
	}
	
	fun scale(k:Double):stl_Complex
	{
		return stl_Complex(k*this.real() , k*this.imaginary())
	}
	
	override fun hashCode():Int
	{
		return Objects.hash(imaginary , real)
	}
}