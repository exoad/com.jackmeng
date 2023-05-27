package com.jackmeng.stl;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Basic Direction Interface with certain attributes of the user's system hardwares.
 *
 * The primary target is the Screen
 *
 * @author Jack Meng
 */
public final class stl_Ware {
    private stl_Ware() {}

    static Random $00_rng = new Random();

    public static Optional<Desktop> desktop()
    {
        return Desktop.isDesktopSupported() ? Optional.of(Desktop.getDesktop()) : Optional.empty();
    }

    public static void screen_cursorTo(int x, int y)
    {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if(r != null)
            r.mouseMove(x, y);
    }

    public static Dimension screen_Size()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static void screen_cursorToRnd()
    {
        desktop().ifPresent(x->screen_cursorTo($00_rng.nextInt(screen_Size().width), $00_rng.nextInt(screen_Size().height)));
    }

    /**
     * Guranteed to be 99% of the time returning a result with value unless the engine
     * is in Headless mode
     * @param x Screen X
     * @param y Screen Y
     * @return
     */
    public static Optional<Color> screen_colorAt(int x, int y)
    {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Optional<Color> color = Optional.empty();
        if(r != null)
            color = Optional.of(r.getPixelColor(x, y));
        return color;
    }

    public static Optional<Color> screen_colorAt_Rnd()
    {
        return screen_colorAt($00_rng.nextInt(screen_Size().width), $00_rng.nextInt(screen_Size().height));
    }
}
