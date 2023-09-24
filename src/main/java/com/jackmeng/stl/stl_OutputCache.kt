// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.HashMap
import java.util.function.Function

class stl_OutputCache<I , O>(private val function:Function<I , O>)
{
	private val cache:MutableMap<I , O?> =HashMap()
	operator fun get(input:I):O?
	{
		var output=cache[input]
		if (output==null)
		{
			output=function.apply(input)
			cache[input]=output
		}
		return output
	}
	
	fun clearCache()
	{
		cache.clear()
	}
}