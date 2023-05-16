// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

/**
 * Type base interface that is to be implemented by
 * all custom defined Type interfaces or objects that are meant
 * to be implemented with the standard type system.
 *
 * For all suffix: "_t" based files
 *
 * @author Jack Meng
 */
public interface stl_type< T >
        extends
        Comparable< T >
{

    /**
     * Defaulted for the name of the type!!!
     *
     * @return The name of the type.
     */
    default String name_type()
    {
        return this.getClass().getCanonicalName() + "->" + getClass().getTypeName();
    }

    interface type_Numerical< E extends Number >
            extends stl_type< E >
    {

        /**
         * Generic Numerical Addition.
         * Where it is "this + (param.value)"
         *
         * @param e
         *            The other type to be added.
         * @return A new object instance that has the value of the final operation.
         */
        E plus(E e);

        /**
         * Generic Numerical Subtraction.
         * Where it is "this - (param.value)"
         *
         * @param e
         *            The other type to be subtracted.
         * @return A new object instance that has the value of the final operation.
         */
        E minus(E e);

        /**
         * Generic Numerical Multiplication.
         * Where it is "this * (param.value)"
         *
         * @param e
         *            The other type to be multiplied by.
         * @return A new object instance that has the value of the final operation.
         */
        E times(E e);

        /**
         * Generic Numerical Division.
         * Where it is "this / (param.value)"
         *
         * @param e
         *            The other type to be divided by.
         * @return A new object instance that has the value of the final operation.
         */
        E divide(E e);

        /**
         * Generic Numerical Modulo (Remainder Division).
         * Where it is "this % (param.value)"
         *
         * @param e
         *            The other type to be modded.
         * @return A new object instance that has the value of the final operation.
         */
        E mod(E e);

        /**
         * Generic Bitwise Negation.
         * Where it is "~this"
         *
         * @return A new object instance that has the value of the final operation.
         */
        E not();

        /**
         * Generic Bitwise OR.
         * Where it is "this | (param.value)"
         *
         * @param e
         *            The other type to be bitwise ORed.
         * @return A new object instance that has the value of the final operation.
         */
        E or(E e);

        /**
         * Generic Bitwise XOR.
         * Where it is "this ^ (param.value)"
         *
         * @param e
         *            The other type to be bitwise XORed.
         * @return A new object instance that has the value of the final operation.
         */
        E xor(E e);

        /**
         * Generic Bitwise AND.
         * Where it is "this &#38; (param.value)"
         *
         * @param e
         *            The other type to be bitwise ANDed.
         * @return A new object instance that has the value of the final operation.
         */
        E and(E e);

        /**
         * Generic Bitwise Left Shift.
         * Where it is "this &#60;&#60; (param.value)"
         *
         * @param e
         *            The other type to left shift by.
         * @return A new object instance that has the value of the final operation.
         */
        E left(E e);

        /**
         * Generic Bitwise Right Shift.
         * Where it is "this &#62;&#62; (param.value)"
         *
         * @param e
         *            The other type to right shift by.
         * @return A new object instance that has the value of the final operation.
         */
        E right(E e);

        /**
         * Generic Bitwise Right Shift.
         * Where it is "this &#62;&#62;&#62; (param.value)" with zero fill.
         *
         * Discarding bits and fill from the left with zeroes.
         *
         * @param e
         *            The other type to right shift by.
         * @return A new object instance that has the value of the final operation.
         */
        E right_2(E e);
    }
}
