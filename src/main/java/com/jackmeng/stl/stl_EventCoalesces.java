// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class stl_EventCoalesces
        implements
        Runnable
{
    private Timer timer;

    public stl_EventCoalesces(int delay, Runnable lambda)
    {
        timer = new Timer(delay, e -> {
            timer.stop();
            lambda.run();
        });
    }

    @Override public void run()
    {
        if (!SwingUtilities.isEventDispatchThread())
            SwingUtilities.invokeLater(timer::restart);
        else
            timer.restart();
    }
}
