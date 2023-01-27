package com.jackmeng.stl;

public final class stl_Commons
{
    private stl_Commons()
    {}

    public static int array_dim(Class<?> type)
    {
        return stl_Str.instances(type.getCanonicalName(), "[]");
    }

    public static boolean is_array(Class<?> type)
    {
        return type.getCanonicalName().contains("[]");
    }
}
