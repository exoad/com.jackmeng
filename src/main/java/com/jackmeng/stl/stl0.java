package com.jackmeng.stl;

final class stl0 {
    private stl0() {}

    public static boolean isGeneric(String str) throws ClassNotFoundException
    {
        return Class.forName(str).getTypeParameters().length > 0;
    }

    public static boolean isGeneric(Class<?> e)
    {
        return e.getTypeParameters().length > 0;
    }
}
