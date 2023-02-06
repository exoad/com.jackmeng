package com.jackmeng.stl;

import java.util.List;

public class stl_Spline
{
    private final List<Double> x;
    private final List<Double> y;
    private final int n;
    private final double[] z;

    public stl_Spline(List<Double> x, List<Double> y)
    {
        this.x = x;
        this.y = y;
        this.n = x.size();
        double[] h = new double[n];
        double[] b = new double[n];
        double[] u = new double[n];
        double[] v = new double[n];
        this.z = new double[n + 1];
        for (int i = 0; i < n - 1; i++)
        {
            h[i] = x.get(i + 1) - x.get(i);
            b[i] = (y.get(i + 1) - y.get(i)) / h[i];
        }
        u[1] = 2 * (h[0] + h[1]);
        v[1] = 6 * (b[1] - b[0]);
        for (int i = 2; i < n - 1; i++)
        {
            u[i] = 2 * (h[i] + h[i - 1]) - h[i - 1] * h[i - 1] / u[i - 1];
            v[i] = 6 * (b[i] - b[i - 1]) - h[i - 1] * v[i - 1] / u[i - 1];
        }
        z[n] = 0;
        for (int i = n - 2; i > 0; i--)
            z[i] = (v[i] - h[i] * z[i + 1]) / u[i];
        z[0] = 0;
    }

    public double interpolate(double t)
    {
        int i = 0;
        int j = n - 1;
        while (j - i > 1) {
            int k = (i + j) / 2;
            if (x.get(k) > t)
                j = k;
            else
                i = k;
        }
        double dx = t - x.get(i);
        return y.get(i) + dx * (z[i] / 2 + dx * (z[j] - z[i]) / (6 * (x.get(j) - x.get(i))) + (y.get(j) - y.get(i)) / (x.get(j) - x.get(i)));
    }
}
