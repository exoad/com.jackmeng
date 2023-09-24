// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.StringBuilder

/**
 * A helper class with concatting multiple instances of
 * [com.jackmeng.stl.stl_AnsiColors] to create a finalized colorized Ansi
 * String.
 *
 * This AnsiMake is not very intuitive and requires a lot of verbosity to be used in order to create a *multicolored* string.
 *
 * For example, it is easy to create non changing colors with a single AnsiMake:
 *
 * @author Jack Meng
 */
class stl_AnsiMake
{
	private val payload:Array<Any>
	private val colors:Array<stl_AnsiColors>
	
	/**
	 * Initializes with a set amount of colors to be appended.
	 * @param start
	 * @param payload
	 */
	constructor(start:Array<stl_AnsiColors> , payload:Array<Any>)
	{
		colors=start
		this.payload=payload
	}
	
	constructor(color:stl_AnsiColors , payload:Any)
	{
		colors=arrayOf(color)
		this.payload=arrayOf(payload)
	}
	
	/**
	 * @return String
	 */
	override fun toString():String
	{
		val sb=StringBuilder()
		for (e in colors) sb.append(e.color())
		for (r in payload) sb.append(r)
		sb.append(stl_AnsiColors.RESET.color())
		return sb.toString()
	}
}