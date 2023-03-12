// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public class stl_AnsiMake
{
    private final Object[] payload;
    private final stl_AnsiColors[] colors;

    public stl_AnsiMake(stl_AnsiColors[] start, Object[] payload)
    {
        this.colors = start;
        this.payload = payload;
    }

    public stl_AnsiMake(stl_AnsiColors color, Object payload)
    {
        this.colors = new stl_AnsiColors[] { color };
        this.payload = new Object[] { payload };
    }

    /**
     * @return String
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (stl_AnsiColors e : colors)
            sb.append(e.color());
        for (Object r : payload)
            sb.append(r);
        sb.append(stl_AnsiColors.RESET.color());
        return sb.toString();
    }
}