// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.List;

public class stl_Bezier
{
    private final List< Double > controlPoints;
    private final int degree;

    public stl_Bezier(List< Double > controlPoints, int degree)
    {
        this.controlPoints = controlPoints;
        this.degree = degree;
    }

    public double interpolate(double t)
    {
        double result = 0;
        for (int i = 0; i <= degree; i++)
        {
            double controlPoint = controlPoints.get(i);
            double coefficient = stl_Maths.binomial_coefficient(degree, i) * Math.pow(1 - t, (double) degree - i)
                    * Math.pow(t, i);
            result += controlPoint * coefficient;
        }
        return result;
    }

}
