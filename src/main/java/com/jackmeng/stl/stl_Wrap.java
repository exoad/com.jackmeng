package com.jackmeng.stl;

public class stl_Wrap <T>
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
