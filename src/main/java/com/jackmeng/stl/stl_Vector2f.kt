// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Vector2f

class stl_Vector2f
{
	var x:Float
	var y:Float
	
	constructor()
	{
		x=0.0f
		y=0.0f
	}
	
	constructor(x:Float , y:Float)
	{
		this.x=x
		this.y=y
	}
	
	fun add(e:stl_Vector2f):stl_Vector2f
	{
		return stl_Vector2f(x+e.x , y+e.y)
	}
	
	fun sub(e:stl_Vector2f):stl_Vector2f
	{
		return stl_Vector2f(x-e.x , y-e.y)
	}
	
	fun mult(k:Float):stl_Vector2f
	{
		return stl_Vector2f(k*x , k*y)
	}
	
	operator fun div(k:Float):stl_Vector2f
	{
		return stl_Vector2f(x/k , y/k)
	}
	
	fun mag():Float
	{
		return Math.sqrt((x*x+y*y).toDouble()).toFloat()
	}
	
	fun dot(e:stl_Vector2f):Float
	{
		return x*e.x+y*e.y
	}
	
	fun cross(e:stl_Vector2f):stl_Vector2f
	{
		return stl_Vector2f(y*e.x-x*e.y , x*e.y-y*e.x)
	}
	
	fun dist(e:stl_Vector2f):Float
	{
		return Math.sqrt(((x-e.x)*(x-e.x)+(y-e.y)*(y-e.y)).toDouble()).toFloat()
	}
	
	fun norm():stl_Vector2f
	{
		return div(mag())
	}
	
	fun theta():Float
	{
		return Math.atan2(y.toDouble() , x.toDouble()).toFloat()
	}
	
	fun rot0(theta:Float):stl_Vector2f
	{
		return stl_Vector2f(
			(x*Math.cos(theta.toDouble())-y*Math.sin(theta.toDouble())).toFloat() ,
			(x*Math.sin(theta.toDouble())+y*Math.cos(theta.toDouble())).toFloat()
		)
	}
	
	fun rot1(theta:Float)
	{
		x=(x*Math.cos(theta.toDouble())-y*Math.sin(theta.toDouble())).toFloat()
		y=(x*Math.sin(theta.toDouble())+y*Math.cos(theta.toDouble())).toFloat()
	}
	
	fun lerp(vec:stl_Vector2f , a:Float):stl_Vector2f
	{
		return add(vec.sub(this).mult(a))
	}
	
	fun reflect(vec:stl_Vector2f):stl_Vector2f
	{
		return sub(vec.mult(2*dot(vec)))
	}
}