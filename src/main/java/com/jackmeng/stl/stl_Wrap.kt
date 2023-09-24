// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.jvm.Synchronized

class stl_Wrap<T>(var obj:T)
{
	fun obj():T
	{
		return obj
	}
	
	fun obj(obj:T)
	{
		this.obj=obj
	}
	
	@Synchronized
	fun s_obj(obj:T)
	{
		this.obj=obj
	}
}