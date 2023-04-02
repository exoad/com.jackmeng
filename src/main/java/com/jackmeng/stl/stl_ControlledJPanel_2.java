// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import javax.swing.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class stl_ControlledJPanel_2
            extends JPanel
{
      private final transient List< Consumer< Graphics > > drawingMethods = new ArrayList<>();
      private final transient List< Consumer< Float > > fpsListeners = new ArrayList<>();
      private final long startTime;
      private long frameCount;

      public stl_ControlledJPanel_2()
      {
            super();
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(this::redraw, 0, 16, TimeUnit.MILLISECONDS);
            startTime = System.nanoTime();
      }

      private void redraw()
      {
            frameCount++;
            long elapsed = System.nanoTime() - startTime;
            float fps = (float) (frameCount * 1e9 / elapsed);
            SwingUtilities.invokeLater(() -> fpsListeners.forEach(x -> x.accept(fps)));
            repaint();
      }

      public void addDrawingMethod(Consumer< Graphics > method)
      {
            drawingMethods.add(method);
      }

      public void removeDrawingMethod(Consumer< Graphics > method)
      {
            drawingMethods.remove(method);
      }

      public void addFPSListener(Consumer< Float > listener)
      {
            fpsListeners.add(listener);
      }

      public void removeFPSListener(Consumer< Float > listener)
      {
            fpsListeners.remove(listener);
      }

      @Override protected void paintComponent(Graphics g)
      {
            super.paintComponent(g);
            drawingMethods.forEach(x -> x.accept(g));
      }
}