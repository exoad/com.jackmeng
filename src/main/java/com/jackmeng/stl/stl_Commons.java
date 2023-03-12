package com.jackmeng.stl;

import java.util.Locale;

public final class stl_Commons
{
    private stl_Commons()
    {
    }

    public static int array_dim(Class< ? > type)
    {
        return stl_Str.instances(type.getCanonicalName(), "[]");
    }

    public static < T, E > Object if_else(boolean expr, T if_, E else_)
    {
        return expr ? if_ : else_;
    }

    public static < T, E > Object nilable(T _check, E else_)
    {
        return if_else(_check == null, _check, else_);
    }

    public static boolean is_array(Class< ? > type)
    {
        return type.getCanonicalName().contains("[]");
    }

    public static < T > stl_ArrItr< T > for_each(T[] e)
    {
        return new stl_ArrItr<>(e);
    }

    public enum SysArch { X64, X86, ARM, ERR }

    public static SysArch sys_bitness()
    {
        String arch = System.getProperty("os.arch").toLowerCase(Locale.ENGLISH);

        return arch.contains("64") ? SysArch.X64 :
                arch.contains("86") ? SysArch.X86 : arch.contains("arm") ? SysArch.ARM :
                                SysArch.ERR;
    }
}
