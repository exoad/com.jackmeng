// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Void

interface stl_ListenerDeployable<A>:stl_Callback<Void? , Iterable<A>?>
{
	override fun call(arg:Iterable<A>?):Void?
	{
		return null
	}
	
	fun empty(length:Int)
	{
	}
}