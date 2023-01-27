package com.jackmeng.stl;

import java.io.Serializable;

public class stl_Wrap <T>
    implements
        Serializable
{
    public T obj;

    public stl_Wrap(T e)
    {
        this.obj = e;
    }

    public T obj()
    {
        return obj;
    }

    public void obj(T obj)
    {
        this.obj = obj;
    }
}
