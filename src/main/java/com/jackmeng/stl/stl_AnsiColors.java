// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

/**
 * A constant enum for defining all common Ansi Colors and the ability to make
 * custom ones. This class is used in conjunction with
 * {@link com.jackmeng.stl.stl_AnsiMake} to create
 * Ansi colored Strings.
 *
 * @author Jack Meng
 * @see com.jackmeng.stl.stl_AnsiMake
 */
public enum stl_AnsiColors {
    RED_BG("\u001B[41m"), GREEN_BG("\u001B[42m"), YELLOW_BG("\u001B[43m"), BLUE_BG("\u001B[44m"), MAGENTA_BG(
            "\u001B[45m"), CYAN_BG("\u001B[46m"), WHITE_BG("\u001B[47m"), BLACK_BG(
                    "\u001B[40m"), RESET("\u001B[0m"), BOLD(
                            "\u001B[1m"), UNDERLINE("\u001B[4m"), BLINK("\u001B[5m"), REVERSE("\u001B[7m"), HIDDEN(
                                    "\u001B[8m"), RED_TXT(
                                            "\u001B[31m"), GREEN_TXT("\u001B[32m"), YELLOW_TXT(
                                                    "\u001B[33m"), BLUE_TXT("\u001B[34m"), MAGENTA_TXT(
                                                            "\u001B[35m"), CYAN_TXT("\u001B[36m"), WHITE_TXT(
                                                                    "\u001B[37m"), BLACK_TXT("\u001B[30m");

    private final String color;

    stl_AnsiColors(String color)
    {
        this.color = color;
    }

    /**
     * @return The internal String value of the color
     */
    public String color()
    {
        return color;
    }

    /**
     * Makes the current Ansi color brighter by modifying the string.
     *
     * @return The modified color in a String. The color is automatically appended.
     */
    public String brighter()
    {
        return color.endsWith(";1m") ? color : color + ";1m";
    }

    /**
     * Make your own Ansi color string provided a color id.
     *
     * @param id
     *            The color id in the range of [0,255]
     * @return If the provided color is valid in the range [0,255], the custom color
     *         will be returned else a {@link #WHITE_TXT} will be returned.
     */
    public static String construct(int id)
    {
        return id >= 0 && id <= 255 ? "\u001b[38;5;" + id + "m" : WHITE_TXT.color;
    }
}