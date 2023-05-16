// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Internal Library functions
 */
final class stl0
{
    private stl0()
    {
    }

    public final ExecutorService EXECS = Executors.newFixedThreadPool(8);

    public static final stl0 INTERNAL = new stl0();

    public static final Timer STL_TIMER0 = new Timer("STL_TIMER00_com.jackmeng");

    public static boolean isGeneric(String str) throws ClassNotFoundException
    {
        return Class.forName(str).getTypeParameters().length > 0;
    }

    public static String dirm()
    {
        return System.getProperty("file.separator");
    }

    public static boolean isGeneric(Class< ? > e)
    {
        return e.getTypeParameters().length > 0;
    }
}
