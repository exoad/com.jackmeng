// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.swing.*;
import java.awt.*;

public class stl_ControlledJPanel extends JPanel
{
  private int fps;
  private transient ScheduledExecutorService executor;
  private transient List< Consumer< Float > > fpsListeners = new ArrayList<>();
  private transient ScheduledFuture< ? > scheduledFuture;

  public stl_ControlledJPanel(int fps)
  {
    this.fps = fps;
    this.executor = Executors.newSingleThreadScheduledExecutor();
    this.scheduledFuture = this.executor.scheduleAtFixedRate(this::repaint, 0, 1000 / fps, TimeUnit.MILLISECONDS);
  }

  @Override protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.fillRect(0, 0, getWidth(), getHeight());
    float currentFPS = 1000f / scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
    for (Consumer< Float > listener : fpsListeners)
    {
      listener.accept(currentFPS);
    }
  }

  public void setFPS(int fps)
  {
    this.fps = fps;
    scheduledFuture.cancel(false);
    executor.shutdown();
    executor = Executors.newSingleThreadScheduledExecutor();
    scheduledFuture = executor.scheduleAtFixedRate(this::repaint, 0, 1000 / fps, TimeUnit.MILLISECONDS);
  }

  public void addFPSListener(Consumer< Float > listener)
  {
    fpsListeners.add(listener);
  }

  public void removeFPSListener(Consumer< Float > listener)
  {
    fpsListeners.remove(listener);
  }
}