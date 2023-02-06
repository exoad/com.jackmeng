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
}
