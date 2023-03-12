// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;
import java.awt.*;

public class stl_SwingBuffered
    extends JComponent
    implements
    Runnable
{

  private final transient BlockingQueue< Runnable > queue;
  private transient Image buffer;
  private boolean running;

  public stl_SwingBuffered()
  {
    queue = new LinkedBlockingQueue<>();
    buffer = null;
    running = false;
  }

  public void start()
  {
    if (!running)
    {
      running = true;
      new Thread(this).start();
    }
  }

  public void stop()
  {
    running = false;
    queue.clear();
  }

  public void draw(Runnable task)
  {
    queue.offer(task);
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        Runnable task = queue.take();
        task.run();
        repaint();
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override protected void paintComponent(Graphics g)
  {
    if (buffer == null)
      buffer = createImage(getWidth(), getHeight());

    Graphics bg = buffer.getGraphics();
    bg.clearRect(0, 0, getWidth(), getHeight());

    // Execute all tasks in the queue
    while (!queue.isEmpty())
    {
      Runnable task = queue.poll();
      if (isVisible(task))
        task.run();
    }

    super.paintComponent(bg);
    bg.dispose();

    g.drawImage(buffer, 0, 0, null);
  }

  private boolean isVisible(Runnable task)
  {
    if (task instanceof Drawable)
    {
      Rectangle bounds = ((Drawable) task).getBounds();
      return getVisibleRect().intersects(bounds);
    }
    return true;
  }

  public interface Drawable
        extends
        Runnable
  {
    Rectangle getBounds();
  }

}