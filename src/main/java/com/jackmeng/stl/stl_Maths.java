// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public final class stl_Maths
{
    private stl_Maths() {}

    public static int binomial_coefficient(int n, int k)
    {
        int result = 1;
        for (int i = 1; i <= k; i++)
            result = result * (n - k + i) / i;
        return result;
    }

    public static double randomRange(double start, double end)
    {
    
    }
}
