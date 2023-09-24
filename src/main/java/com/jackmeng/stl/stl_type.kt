// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_type

/**
 * Type base interface that is to be implemented by
 * all custom defined Type interfaces or objects that are meant
 * to be implemented with the standard type system.
 *
 * For all suffix: "_t" based files
 *
 * @author Jack Meng
 */
interface stl_type<T>:Comparable<T>
{
	/**
	 * Defaulted for the name of the type!!!
	 *
	 * @return The name of the type.
	 */
	fun name_type():String?
	{
		return this.javaClass.canonicalName+"->"+javaClass.typeName
	}
	
	interface type_Numerical<E:Number?>:stl_type<E>
	{
		/**
		 * Generic Numerical Addition.
		 * Where it is "this + (param.value)"
		 *
		 * @param e
		 * The other type to be added.
		 * @return A new object instance that has the value of the final operation.
		 */
		operator fun plus(e:E):E
		
		/**
		 * Generic Numerical Subtraction.
		 * Where it is "this - (param.value)"
		 *
		 * @param e
		 * The other type to be subtracted.
		 * @return A new object instance that has the value of the final operation.
		 */
		operator fun minus(e:E):E
		
		/**
		 * Generic Numerical Multiplication.
		 * Where it is "this * (param.value)"
		 *
		 * @param e
		 * The other type to be multiplied by.
		 * @return A new object instance that has the value of the final operation.
		 */
		operator fun times(e:E):E
		
		/**
		 * Generic Numerical Division.
		 * Where it is "this / (param.value)"
		 *
		 * @param e
		 * The other type to be divided by.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun divide(e:E):E
		
		/**
		 * Generic Numerical Modulo (Remainder Division).
		 * Where it is "this % (param.value)"
		 *
		 * @param e
		 * The other type to be modded.
		 * @return A new object instance that has the value of the final operation.
		 */
		operator fun mod(e:E):E
		
		/**
		 * Generic Bitwise Negation.
		 * Where it is "~this"
		 *
		 * @return A new object instance that has the value of the final operation.
		 */
		operator fun not():E
		
		/**
		 * Generic Bitwise OR.
		 * Where it is "this | (param.value)"
		 *
		 * @param e
		 * The other type to be bitwise ORed.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun or(e:E):E
		
		/**
		 * Generic Bitwise XOR.
		 * Where it is "this ^ (param.value)"
		 *
		 * @param e
		 * The other type to be bitwise XORed.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun xor(e:E):E
		
		/**
		 * Generic Bitwise AND.
		 * Where it is "this &#38; (param.value)"
		 *
		 * @param e
		 * The other type to be bitwise ANDed.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun and(e:E):E
		
		/**
		 * Generic Bitwise Left Shift.
		 * Where it is "this &#60;&#60; (param.value)"
		 *
		 * @param e
		 * The other type to left shift by.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun left(e:E):E
		
		/**
		 * Generic Bitwise Right Shift.
		 * Where it is "this &#62;&#62; (param.value)"
		 *
		 * @param e
		 * The other type to right shift by.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun right(e:E):E
		
		/**
		 * Generic Bitwise Right Shift.
		 * Where it is "this &#62;&#62;&#62; (param.value)" with zero fill.
		 *
		 * Discarding bits and fill from the left with zeroes.
		 *
		 * @param e
		 * The other type to right shift by.
		 * @return A new object instance that has the value of the final operation.
		 */
		fun right_2(e:E):E
	}
}