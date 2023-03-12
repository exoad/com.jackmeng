// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public class stl_FFT
{
    public static stl_Complex[] cooleytukey(stl_Complex[] x)
    {
        int n = x.length;

        if (n == 1)
            return new stl_Complex[] { x[0] };

        if (n % 2 != 0)
            throw new IllegalArgumentException("n is not a power of 2");

        stl_Complex[] even = new stl_Complex[n / 2];
        for (int i = 0; i < n / 2; i++)
            even[i] = x[2 * i];

        stl_Complex[] q = cooleytukey(even);

        stl_Complex[] odd = even;
        for (int i = 0; i < n / 2; i++)
            odd[i] = x[2 * i + 1];

        stl_Complex[] r = cooleytukey(odd);

        stl_Complex[] y = new stl_Complex[n];
        for (int k = 0; k < n / 2; k++)
        {
            double kth = -2 * k * Math.PI / n;
            stl_Complex wk = new stl_Complex(Math.cos(kth), Math.sin(kth));
            y[k] = q[k].plus(wk.times(r[k]));
            y[k + n / 2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }
}