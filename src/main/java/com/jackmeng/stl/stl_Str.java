package com.jackmeng.stl;

import java.util.Map;

public final class stl_Str
{
    private stl_Str()
    {
    }

    public static String n_copies(int n, String sequence)
    {
        StringBuilder sb = new StringBuilder();
        while(n-- > 0)
            sb.append(sequence);
        return sb.toString();
    }

    public static boolean is_empty(CharSequence e)
    {
        return e == null || e.length() == 0 || e.isEmpty() || (e instanceof String && ((String) e).isBlank() || ((String)e).matches("\\s+"));
    }

    public static String interpolate0(String keyStart, String keyEnd, String template, String... payloads)
    {
        for (int i = 0; i < payloads.length; i += 2)
            template = template.replace(keyStart + payloads[i] + keyEnd, payloads[i + 1]);
        return template;
    }

    public static String interpolate1_1(String keyStart, String keyEnd, String template, Map< String, String > vars)
    {
        for (Map.Entry< String, String > e : vars.entrySet())
            template = template.replace(keyStart + e.getKey() + keyEnd, e.getValue());
        return template;
    }

    public static String interpolate2(String keyStart, String keyEnd, String template, String... payloads)
    {
        for (int i = 0; i < payloads.length; i++)
            template = template.replace(keyStart + i + keyEnd, payloads[i]);
        return template;
    }

    public static String interpolate3(String keyStart, String keyEnd, String template, String[][] payloads)
    /*
     * Payload formatting:
     * {
     * {
     * key,
     * value
     * }
     * }
     */
    {
        for (String[] a : payloads)
            template = template.replace(keyStart + a[0] + keyEnd, a[1]);
        return template;
    }

    public static String insert_nl(String input, int maxChars, String optionalPad)
    {
        String pad = optionalPad == null ? "\n" : optionalPad;
        StringBuilder sb = new StringBuilder();
        String[] w = input.split(" ");
        int i = 0;
        for(String r : w)
        {
            if(i + r.length() > maxChars)
            {
                sb.append(pad);
                i = 0;
            }
            if(r.length() > maxChars)
            {
                int j = 0;
                while(j < r.length())
                {
                    sb.append(j + maxChars < r.length() ? r.substring(j, j + maxChars): r.substring(j));
                    j += maxChars;
                    sb.append(pad);
                }
            }
            else
            {
                sb.append(r).append(" ");
                i += r.length() + 1;
            }
        }
        return sb.toString().trim();
    }
}
