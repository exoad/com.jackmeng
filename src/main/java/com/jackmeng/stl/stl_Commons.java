// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import com.jackmeng.stl.types.Null_t;

public final class stl_Commons
{
    private stl_Commons()
    {
    }

    public static char[] COMMON_ASCII_TABLE()
    {
        return new char[]
                {
                '0',
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                'A',
                'B',
                'C',
                'D',
                'E',
                'F',
                'G',
                'H',
                'I',
                'J',
                'K',
                'L',
                'M',
                'N',
                'O',
                'P',
                'Q',
                'R',
                'S',
                'T',
                'U',
                'V',
                'W',
                'X',
                'Y',
                'Z',
                'a',
                'b',
                'c',
                'd',
                'e',
                'f',
                'g',
                'h',
                'i',
                'j',
                'k',
                'l',
                'm',
                'n',
                'o',
                'p',
                'q',
                'r',
                's',
                't',
                'u',
                'v',
                'w',
                'x',
                'y',
                'z',
                '#',
                '$',
                '%',
                '*',
                '+',
                ',',
                '-',
                '.',
                ':',
                ';',
                '=',
                '?',
                '@',
                '[',
                ']',
                '^',
                '_',
                '{',
                '|',
                '}',
                '~'
        };
    }

    public static void async(Runnable task)
    {
        stl0.INTERNAL.EXECS.submit(task);
    }

    public static void async(stl_Callback< Void, ? extends Null_t> task)
    {
        stl0.INTERNAL.EXECS.submit(() -> task.call(null));
    }

    public static <T> boolean array_has(T[] array, T key)
    {
        for(T r : array) if(r.equals(key)) return true;
        return false;
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

    public enum SysArch {
        X64, X86, ARM, ERR
    }

    public static SysArch sys_bitness()
    {
        String arch = System.getProperty("os.arch").toLowerCase(Locale.ENGLISH);

        return arch.contains("64") ? SysArch.X64
                : arch.contains("86") ? SysArch.X86 : arch.contains("arm") ? SysArch.ARM : SysArch.ERR;
    }

    public static < T > stl_Listener< T > consumer2listener(Consumer< T > consume)
    {
        return new stl_Listener< T >() {
            @Override public Void call(T e)
            {
                consume.accept(e);
                return null;
            }
        };
    }

    public static String tmpdir()
    {
        return System.getProperty("java.io.tmpdir");
    }
}
