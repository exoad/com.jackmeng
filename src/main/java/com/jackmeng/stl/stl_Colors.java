// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.awt.Color;
import java.util.Optional;

public final class stl_Colors
{

    private stl_Colors() {}

    /**
     * Attempts to turn a HEX represented Color into the java.awt.Color object
     * @param hex A HEX String
     * @return A color object
     * @see java.awt.Color
     */
    public static Color hexToRGB(String hex)
    {
        assert hex != null;
        if (!hex.startsWith("#"))
            hex = "#" + hex;
        return new Color(
                Integer.valueOf(hex.substring(1, 3), 16),
                Integer.valueOf(hex.substring(3, 5), 16),
                Integer.valueOf(hex.substring(5, 7), 16));
    }

    public static String RGBAtoHex(int alpha, int red, int green, int blue)
    {
        return String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
    }

    public static Color awt_ColorInvert(Color r)
    {
        return new Color((255F - r.getRed()) / 255F, (255F - r.getGreen()) / 255F, (255F - r.getBlue()) / 255F, (255F - r.getAlpha()) / 255F);
    }

    public static Optional<Color> averageOf(Color... colors)
    {
        if(colors == null || colors.length == 0)
            return Optional.empty();
        else if (colors.length == 1)
            return Optional.of(colors[0]);
        stl_Struct.struct_Quad<Float, Float, Float, Float> sum = stl_Struct.make_quad((float) colors[0].getRed(), (float) colors[0].getGreen(), (float) colors[0].getRed(), (float) colors[0].getAlpha());
        int x = 1;
        for(;x < colors.length; x++)
        {
            sum.first += colors[x].getRed();
            sum.second += colors[x].getGreen();
            sum.third += colors[x].getBlue();
            sum.fourth += colors[x].getAlpha();
        }

        return Optional.of(new Color(sum.first / x, sum.second / x, sum.third / x, sum.fourth / x));
    }
}
