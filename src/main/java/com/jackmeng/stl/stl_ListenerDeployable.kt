// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Callback
import java.lang.Void

interface stl_ListenerDeployable<A>:stl_Callback<Void? , Iterable<A>?>
{
	override fun call(e:Iterable<A>):Void
	{
		return null as Void
	}
	
	fun empty(length:Int)
	{
	}
}