package com.jackmeng.stl;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class stl_Chrono
{

    public static String format_millis(String format)
    {
        long t = System.currentTimeMillis();
        Date e = new Date(t);
        return new SimpleDateFormat(format).format(e);
    }

    public static String format_millis()
    {
        return format_millis("MM_dd_YYYY__HH_mm_ss");
    }
}
