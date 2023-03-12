// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl.types;

import com.jackmeng.stl.stl_type;

/**
 * Represents a standard Java 32 Bitness Unsigned Integer type.
 *
 * This is not to be a wrapper class!!
 *
 * @author Jack Meng
 */
public final class UInt_t
        extends
        Number
        implements
        stl_type.type_Numerical<UInt_t>
{

    private final int val;

    private UInt_t(int v) {
        val = v & 0xFFFFFFFF; // simplify bits
    }

    /**
     * ZERO = 0
     */
    public static final UInt_t ZERO = new UInt_t(0);

    /**
     * ONE = 1
     */
    public static final UInt_t ONE = new UInt_t(1);

    /**
     * MAX_VALUE = ?
     */
    public static final UInt_t MAX_VALUE = new UInt_t(-1);

    /**
     * Turn a regular long into an unsigned integer.
     *
     * @param t A value
     * @return An {@link #UInt_t(int)} object
     */
    public static UInt_t make(long t) {
        return new UInt_t((int) t);
    }

    @Override
    public int compareTo(UInt_t o) {
        assert o != null;
        return Integer.compare(o.val ^ Integer.MAX_VALUE, this.val ^ Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object e) {
        return e instanceof UInt_t && val == ((UInt_t) e).val;
    }

    @Override
    public int intValue() {
        return val;
    }

    @Override
    public long longValue() {
        return val & 0xFFFFFFFFL;
    }

    @Override
    public float floatValue() {
        return longValue();
    }

    @Override
    public double doubleValue() {
        return longValue();
    }

    @Override
    public UInt_t plus(UInt_t e) {
        assert e != null;
        return new UInt_t(val + e.val);
    }

    @Override
    public UInt_t minus(UInt_t e) {
        assert e != null;
        return new UInt_t(val - e.val);
    }

    @Override
    public UInt_t times(UInt_t e) {
        assert e != null;
        return new UInt_t(val * e.val);
    }

    @Override
    public UInt_t divide(UInt_t e) {
        assert e != null;
        return new UInt_t(val / e.val);
    }

    @Override
    public UInt_t mod(UInt_t e) {
        assert e != null;
        return new UInt_t(val % e.val);
    }

    @Override
    public UInt_t not() {
        return new UInt_t(~val);
    }

    @Override
    public UInt_t or(UInt_t e) {
        assert e != null;
        return new UInt_t(val | e.val);
    }

    @Override
    public UInt_t xor(UInt_t e) {
        assert e != null;
        return new UInt_t(val ^ e.val);
    }

    @Override
    public UInt_t and(UInt_t e) {
        assert e != null;
        return new UInt_t(val & e.val);
    }

    @Override
    public UInt_t left(UInt_t e) {
        assert e != null;
        return new UInt_t(val << e.val);
    }

    @Override
    public UInt_t right(UInt_t e) {
        assert e != null;
        return new UInt_t(val >> e.val);
    }

    @Override
    public UInt_t right_2(UInt_t e) {
        assert e != null;
        return new UInt_t(val >>> e.val);
    }

}
