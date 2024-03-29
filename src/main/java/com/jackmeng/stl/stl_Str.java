// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.Map;

public final class stl_Str
{
    private stl_Str()
    {
    }

    public static String from_char_arr(char[] e)
    {
        StringBuilder sb = new StringBuilder();
        for (char x : e)
            sb.append(x);
        return sb.toString();
    }

    public static String n_copies(int n, String sequence)
    {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0)
            sb.append(sequence);
        return sb.toString();
    }

    public static boolean is_empty(CharSequence e)
    {
        if (e == null || e.length() == 0 || e.isEmpty())
            return true;
        if (e instanceof String && ((String) e).isBlank())
            return true;
        assert e instanceof String;
        return ((String) e).matches("\\s+");
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

    public static int instances(String payload, String toFind)
    {
        int last = 0, i = 0;
        while ((last = payload.indexOf(toFind, last)) != -1)
        {
            i++;
            last += toFind.length() - 1;
        }
        return i;
    }

    public static boolean is_one_type_commaed(String input)
    {
        String[] parts = input.split(",");
        Class< ? > clazz = null;
        boolean isValid = true;
        for (String part : parts)
        {
            if (part.isEmpty())
            {
                isValid = false;
                break;
            }
            Object obj = (part.length() == 1) ? part.charAt(0) : part;
            if (clazz == null)
            {
                clazz = obj.getClass();
            }
            else
            {
                if (!obj.getClass().equals(clazz))
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public static boolean parse_bool(String content)
    {
        content = content.toLowerCase();
        return content.equals("1") || content.equals("on") || content.equals("true") || content.equals("positive")
                || content.equals("in");
    }

    public static String insert_nl(String input, int maxChars, String optionalPad)
    {
        String pad = optionalPad == null ? "\n" : optionalPad;
        StringBuilder sb = new StringBuilder();
        String[] w = input.split(" ");
        int i = 0;
        for (String r : w)
        {
            if (i + r.length() > maxChars)
            {
                sb.append(pad);
                i = 0;
            }
            if (r.length() > maxChars)
            {
                int j = 0;
                while (j < r.length())
                {
                    sb.append(j + maxChars < r.length() ? r.substring(j, j + maxChars) : r.substring(j));
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
