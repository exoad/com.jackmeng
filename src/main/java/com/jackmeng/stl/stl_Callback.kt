// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

/**
 * A functional callback.
 * A callback is defined as basically lambda. Additionally it can be
 * thought of as a crossover between [java.util.function.Consumer] and a [java.util.function.Supplier].
 * @param <T> The return type; if nothing is to be returned, put [Void] and return Null in the implementation
 * @param <E> The parameter type; if nothing is to be taken, put [com.jackmeng.stl.types.Null_t].
</E></T> */
fun interface stl_Callback<T , E>
{
	/**
	 * Call this callback with the specified attributes.
	 * @param arg The specified parameter type
	 * @return The specified return type. Return "null" if the return type was set to [Void]
	 */
	fun call(arg:E):T
}