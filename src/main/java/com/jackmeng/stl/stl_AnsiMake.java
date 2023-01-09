package com.jackmeng.stl;

import java.io.Serializable;

public class stl_AnsiMake
    implements
        Serializable
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