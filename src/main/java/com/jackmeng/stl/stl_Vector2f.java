// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public class stl_Vector2f
{
    public float x, y;

    public stl_Vector2f()
    {
        this.x = 0.0F;
        this.y = 0.0F;
    }

    public stl_Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public stl_Vector2f add(stl_Vector2f e)
    {
        return new stl_Vector2f(x + e.x, y + e.y);
    }

    public stl_Vector2f sub(stl_Vector2f e)
    {
        return new stl_Vector2f(x - e.x, y - e.y);
    }

    public stl_Vector2f mult(float k)
    {
        return new stl_Vector2f(k * x, k * y);
    }

    public stl_Vector2f div(float k)
    {
        return new stl_Vector2f(x / k, y / k);
    }

    public float mag()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(stl_Vector2f e)
    {
        return x * e.x + y * e.y;
    }

    public stl_Vector2f cross(stl_Vector2f e)
    {
        return new stl_Vector2f(y * e.x - x * e.y, x * e.y - y * e.x);
    }

    public float dist(stl_Vector2f e)
    {
        return (float) Math.sqrt((x - e.x) * (x - e.x) + (y - e.y) * (y - e.y));
    }

    public stl_Vector2f norm()
    {
        return div(mag());
    }

    public float theta()
    {
        return (float) Math.atan2(y, x);
    }

    public stl_Vector2f rot0(float theta)
    {
        return new stl_Vector2f((float) (x * Math.cos(theta) - y * Math.sin(theta)),
                (float) (x * Math.sin(theta) + y * Math.cos(theta)));
    }

    public void rot1(float theta)
    {
        this.x = (float) (x * Math.cos(theta) - y * Math.sin(theta));
        this.y = (float) (x * Math.sin(theta) + y * Math.cos(theta));
    }

    public stl_Vector2f lerp(stl_Vector2f vec, float a)
    {
        return add(vec.sub(this).mult(a));
    }

    public stl_Vector2f reflect(stl_Vector2f vec)
    {
        return sub(vec.mult(2 * dot(vec)));
    }
}
