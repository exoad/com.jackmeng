// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Pt

interface stl_Curve
{
	fun points(segments:Int):Array<stl_Pt?>?
	fun controls(pts:Array<stl_Pt?>?)
	fun controls():Array<stl_Pt?>?
	fun tension(tension:Float)
	fun tension():Float
	fun continuity(continuity:Float)
	fun continuity():Float
	fun bias(bias:Float)
	fun bias():Float
}