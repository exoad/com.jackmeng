// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

interface stl_Monad<T>
{
	fun <R> bind(f:stl_Function<in T? , stl_Monad<R>?>):stl_Monad<R>?
	
	companion object
	{
		fun <T> unit(value:T):stl_Monad<T>
		{
			return object:stl_Monad<T>
			{
				override fun <R> bind(f:stl_Function<in T? , stl_Monad<R>?>):stl_Monad<R>?
				{
					return f.apply(value)
				}
			}
		}
	}
}