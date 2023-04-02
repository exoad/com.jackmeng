// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public final class stl_Maths
{
    private stl_Maths()
    {
    }

    public static int binomial_coefficient(int n, int k)
    {
        int result = 1;
        for (int i = 1; i <= k; i++)
            result = result * (n - k + i) / i;
        return result;
    }

    public static double clamp_with_epsilon(double value, double min, double max, double epsilon)
    {
        return Math.max(min - epsilon, Math.min(max + epsilon, value));
    }

    public static double exponential_clamp(double value, double min, double max, double exponent)
    {
        double range = max - min;
        double normalized = (value - min) / range;
        double clamped = Math.max(0, Math.min(1, normalized));
        double buffer = range * (1 - Math.pow(clamped, exponent));
        return Math.max(min - buffer, Math.min(max + buffer, value));
    }

    public static double smoothstep_Clamp(double value, double min, double max, double smoothness)
    {
        double range = max - min;
        double normalized = (value - min) / range;
        double clamped = Math.max(0, Math.min(1, normalized));
        double smooth = clamped * clamped * (3 - 2 * clamped);
        double buffer = range * (1 - smoothness * smooth);
        return Math.max(min - buffer, Math.min(max + buffer, value));
    }

    public static int clamp(int value, int min, int max)
    {
        return Math.max(min, Math.min(max, value));
    }

}
