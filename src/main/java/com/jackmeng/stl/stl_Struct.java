// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public final class stl_Struct
{

    public static < F, L > stl_Struct.struct_Pair< F, L > make_pair(F first, L last)
    {
        return struct_Pair.make(first, last);
    }

    public static < F, M, L > stl_Struct.struct_Triple< F, M, L > make_triple(F first, M middle, L last)
    {
        return new struct_Triple<>(first, middle, last);
    }

    public static < F, M1, M2, L > stl_Struct.struct_Quad< F, M1, M2, L > make_quad(F first, M1 middle1, M2 middle2,
            L last)
    {
        return new struct_Quad<>(first, middle1, middle2, last);
    }

    public static < F, L, N > stl_Struct.struct_NamedPair< F, L, N > make_namedpair(F first, L last, N firstname,
            N secondname)
    {
        return new struct_NamedPair<>(first, last, firstname, secondname);
    }

    public static final class struct_NamedPair< A, B, T >
            extends struct_Pair< A, B >
    {
        public final T first_, second_;

        public struct_NamedPair(A first, B second, T firstName, T secondName)
        {
            super(first, second);
            this.first_ = firstName;
            this.second_ = secondName;
        }

        @Override public String toString()
        {
            return super.toString() + first_ + "+" + second_;
        }

    }

    public static class struct_Pair< A, B >
    {
        public final A first;
        public final B second;

        public struct_Pair(A first, B second)
        {
            this.first = first;
            this.second = second;
        }

        public String toString()
        {

            return this.hashCode() + "_PAIR_" + first.getClass().getSimpleName() + " + "
                    + second.getClass().getSimpleName()
                    + ":[" + first + "," + second + "]";
        }

        public static < F, L > stl_Struct.struct_Pair< F, L > make(F first, L last)
        {
            return new stl_Struct.struct_Pair<>(first, last);
        }

        public static stl_Struct.struct_Pair< ?, ? > make(Object[] e)
        {
            return stl_Struct.struct_Pair.make(e[0], e[1]);
        }

        @Override public boolean equals(Object cum)
        {
            if (!(cum instanceof struct_Pair< ?, ? >))
                return false;
            return ((struct_Pair< ?, ? >) cum).first.equals(first) && ((struct_Pair< ?, ? >) cum).second.equals(second);
        }

        @Override public int hashCode()
        {
            return (((((1 << 1) - 1) & first.hashCode())
                    ^ ((((1 << (Integer.BYTES * 8) + 1) - 1) & first.hashCode()) >> ((Integer.BYTES * 8)
                            / 2))) << ((Integer.BYTES
                                    * 8) / 2))

                    | ((((1 << 1) - 1) & second.hashCode())
                            ^ ((((1 << (Integer.BYTES * 8) + 1) - 1) & second.hashCode()) >> ((Integer.BYTES * 8)
                                    / 2)));
        }
    }

    public static final class struct_Triple< A, B, C >
    {
        public final A first;
        public final B second;
        public final C third;

        public struct_Triple(A first, B second, C third)
        {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public Object[] to_array()
        {
            return stl0.isGeneric(first.getClass()) || stl0.isGeneric(second.getClass())
                    || stl0.isGeneric(third.getClass()) ? null : new Object[] { first, second, third };
        }
    }

    public static final class struct_Quad< A, B, C, D >
    {
        public final A first;
        public final B second;
        public final C third;
        public final D fourth;

        public struct_Quad(A first, B second, C third, D fourth)
        {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

    }
}