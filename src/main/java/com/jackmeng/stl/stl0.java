package com.jackmeng.stl;

import java.util.Timer;

final class stl0 {
    private stl0() {}

    public static final Timer STL_TIMER0 = new Timer("STL_TIMER00_com.jackmeng");

    public static boolean isGeneric(String str) throws ClassNotFoundException
    {
        return Class.forName(str).getTypeParameters().length > 0;
    }

    public static String dirm()
    {
        return System.getProperty("file.separator");
    }

    public static boolean isGeneric(Class<?> e)
    {
        return e.getTypeParameters().length > 0;
    }
}
