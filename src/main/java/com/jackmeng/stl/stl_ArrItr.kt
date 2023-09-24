// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.NoSuchElementException

/**
 * A simple transformer class for turning a regular array into an Iterable
 * type via Iterator.
 *
 * @author Jack Meng
 */
abstract class stl_ArrItr<T>
/**
 * @param arr
 * The target primitive array.
 */(private val arr:Array<T>):MutableIterator<T>
{
	private var i=0
	override fun hasNext():Boolean
	{
		return i<arr.size
	}
	
	override fun next():T
	{
		return if (hasNext()) arr[i++] else throw NoSuchElementException()
	}
}