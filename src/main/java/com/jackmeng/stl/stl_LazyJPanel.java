// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import javax.swing.JPanel;
import java.awt.*;

public abstract class stl_LazyJPanel
        extends
        JPanel
{
    protected boolean construction = false;
    protected final boolean finished;

    protected abstract void constr();

    protected stl_LazyJPanel()
    {
        finished = true;
    }

    @Override public void paint(Graphics g)
    {
        sync();
        super.paint(g);
    }

    @Override public void paintAll(Graphics g)
    {
        sync();
        super.paintAll(g);
    }

    @Override public void paintComponents(Graphics g)
    {
        sync();
        super.paintComponents(g);
    }

    @Override public void repaint()
    {
        sync();
        super.repaint();
    }

    @Override public void repaint(long time)
    {
        sync();
        super.repaint(time);
    }

    @Override public void repaint(int x, int y, int x2, int y2)
    {
        sync();
        super.repaint(x, y, x2, y2);
    }

    @Override public void repaint(long time, int x, int y, int x2, int y2)
    {
        sync();
        super.repaint(time, x, y, x2, y2);
    }

    @Override public void update(Graphics g)
    {
        sync();
        super.update(g);
    }

    public final synchronized void sync()
    {
        if (!construction && getParent() != null)
        {
            constr();
            construction = true;
            validate();
        }
    }
}