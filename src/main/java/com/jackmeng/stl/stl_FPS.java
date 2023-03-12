// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is useful to represent FPS
 * Calling FPS.interrupt() represents a new
 * Frame has been drawn.
 *
 * @author Jack Meng
 */
public class stl_FPS
        extends Thread
{
    private double fps, min = 3000000000.0d, max = 0.0d;
    private final List< Runnable > listeners = new ArrayList<>();

    public void addUpdatePromise(Runnable... promises)
    {
        listeners.addAll(Arrays.asList(promises));
    }

    private void notifyPromises()
    {
        listeners.forEach(Runnable::run);
    }

    @Override public void run()
    {
        while (true)
        {
            long last = System.nanoTime();
            try
            {
                Thread.sleep(1000L);
            } catch (InterruptedException e)
            {
                // IGNORED
            }
            fps = 1000000000.0 / (System.nanoTime() - last);
            if (fps > max)
                max = fps;
            if (fps < min)
                min = fps;
            notifyPromises();
            last = System.nanoTime();
        }
    }

    public double getFPS()
    {
        return fps;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

}