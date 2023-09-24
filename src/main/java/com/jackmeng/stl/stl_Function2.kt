// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Function

interface stl_Function2<T1 , T2 , R>
{
	fun apply(t1:T1 , t2:T2):R
	fun curry():stl_Function<T1 , stl_Function<T2 , R>>?
	{
		return stl_Function { t1:T1-> stl_Function { t2:T2-> apply(t1 , t2) } }
	}
}