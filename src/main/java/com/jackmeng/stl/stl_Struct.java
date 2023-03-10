// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public final class stl_Struct
{

    public static final class struct_Pair< A, B >
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

            return this.hashCode() + "_PAIR_" + first.getClass().getSimpleName() + " + " + second.getClass().getSimpleName()
                    + ":[" + first + "," + second + "]";
        }

        public static <F, L> stl_Struct.struct_Pair<F, L> make(F first, L last)
        {
            return new stl_Struct.struct_Pair<>(first, last);
        }

        public static stl_Struct.struct_Pair<?,?> make(Object[] e)
        {
            return stl_Struct.struct_Pair.make(e[0], e[1]);
        }

        @Override public boolean equals(Object cum)
        {
            if (!(cum instanceof struct_Pair<?, ?>))
                return false;
            return ((struct_Pair<?, ?>) cum).first.equals(first) && ((struct_Pair<?, ?>) cum).second.equals(second);
        }

        @Override public int hashCode()
        {
            return (((((1 << 1) - 1) & first.hashCode())
                    ^ ((((1 << (Integer.BYTES * 8) + 1) - 1) & first.hashCode()) >> ((Integer.BYTES * 8) / 2))) << ((Integer.BYTES
                    * 8) / 2))

                    | ((((1 << 1) - 1) & second.hashCode())
                    ^ ((((1 << (Integer.BYTES * 8) + 1) - 1) & second.hashCode()) >> ((Integer.BYTES * 8) / 2)));
        }
    }

    public static final class struct_Trio< A, B, C >
    {
        public final A first;
        public final B second;
        public final C third;

        public struct_Trio(A first, B second, C third)
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
}