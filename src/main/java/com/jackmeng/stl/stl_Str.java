package com.jackmeng.stl;

import java.util.Map;

public final class stl_Str
{
    private stl_Str() {}

    public static String interpolate0(String keyStart, String keyEnd, String template, String... payloads)
    {
        for(int i = 0; i < payloads.length; i+=2)
            template = template.replace(keyStart + payloads[i] + keyEnd, payloads[i + 1]);
        return template;
    }

    public static String interpolate1_1(String keyStart, String keyEnd, String template, Map<String, String> vars)
    {
        for(Map.Entry<String, String> e : vars.entrySet())
            template = template.replace(keyStart + e.getKey() + keyEnd, e.getValue());
        return template;
    }

    public static String interpolate2(String keyStart, String keyEnd, String template, String... payloads)
    {
        for(int i = 0; i < payloads.length; i++)
            template = template.replace(keyStart + i + keyEnd, payloads[i]);
        return template;
    }

    public static String interpolate3(String keyStart, String keyEnd, String template, String[][] payloads)
            /*
            Payload formatting:
            {
                {
                    key,
                    value
                }
            }
             */
    {
        for (String[] a : payloads)
            template = template.replace(keyStart + a[0] + keyEnd, a[1]);
        return template;
    }
}
