package com.jackmeng.stl;

public final class stl_Commons
{
    private stl_Commons()
    {}

    public static int array_dim(Class<?> type)
    {
        return stl_Str.instances(type.getCanonicalName(), "[]");
    }

    public static <T, E> Object if_else(boolean expr, T if_, E else_)
    {
        return expr ? if_ : else_;
    }

    public static boolean is_array(Class<?> type)
    {
        return type.getCanonicalName().contains("[]");
    }
}
