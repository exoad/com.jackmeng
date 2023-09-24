// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl.types

import com.jackmeng.stl.stl_type.type_Numerical
import com.jackmeng.stl.types.UInt_t

/**
 * Represents a standard Java 32 Bitness Unsigned Integer type.
 *
 * This is not to be a wrapper class!!
 *
 * @author Jack Meng
 */
class UInt_t private constructor(v:Int):Number() , type_Numerical<UInt_t?>
{
	private val `val`:Int
	
	init
	{
		`val`=v and -0x1 // simplify bits
	}
	
	override operator fun compareTo(o:UInt_t):Int
	{
		assert(o!=null)
		return Integer.compare(o.`val` xor Int.MAX_VALUE , `val` xor Int.MAX_VALUE)
	}
	
	override fun equals(e:Any?):Boolean
	{
		return e is UInt_t&&`val`==e.`val`
	}
	
	override fun intValue():Int
	{
		return `val`
	}
	
	override fun longValue():Long
	{
		return (`val` and 0xFFFFFFFFL).toLong()
	}
	
	override fun floatValue():Float
	{
		return toLong().toFloat()
	}
	
	override fun doubleValue():Double
	{
		return toLong().toDouble()
	}
	
	override operator fun plus(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val`+e.`val`)
	}
	
	override operator fun minus(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val`-e.`val`)
	}
	
	override operator fun times(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val`*e.`val`)
	}
	
	override fun divide(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val`/e.`val`)
	}
	
	override operator fun mod(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val`%e.`val`)
	}
	
	override fun not():UInt_t
	{
		return UInt_t(`val`.inv())
	}
	
	override fun or(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` or e.`val`)
	}
	
	override fun xor(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` xor e.`val`)
	}
	
	override fun and(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` and e.`val`)
	}
	
	override fun left(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` shl e.`val`)
	}
	
	override fun right(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` shr e.`val`)
	}
	
	override fun right_2(e:UInt_t):UInt_t
	{
		assert(e!=null)
		return UInt_t(`val` ushr e.`val`)
	}
	
	companion object
	{
		/**
		 * ZERO = 0
		 */
		val ZERO=UInt_t(0)
		
		/**
		 * ONE = 1
		 */
		val ONE=UInt_t(1)
		
		/**
		 * MAX_VALUE = ?
		 */
		val MAX_VALUE=UInt_t(-1)
		
		/**
		 * Turn a regular long into an unsigned integer.
		 *
		 * @param t A value
		 * @return An [.UInt_t] object
		 */
		fun make(t:Long):UInt_t
		{
			return UInt_t(t.toInt())
		}
	}
}