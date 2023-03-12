// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class stl_SwingBuffered2
{
  private BufferedImage buffer;
  private Graphics2D bufferGraphics;
  private boolean isDirty = true;

  public stl_SwingBuffered2(JComponent component)
  {
    this(component.getWidth(), component.getHeight());
  }

  public stl_SwingBuffered2(int width, int height)
  {
    this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    this.bufferGraphics = buffer.createGraphics();
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  }

  public Graphics2D getGraphics()
  {
    isDirty = true;
    return bufferGraphics;
  }

  public void paintComponent(Graphics g)
  {
    if (isDirty)
    {
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(buffer, 0, 0, null);
      isDirty = false;
    }
  }

  public void setSize(int width, int height)
  {
    bufferGraphics.dispose();
    buffer.flush();
    buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    bufferGraphics = buffer.createGraphics();
    bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    isDirty = true;
  }

}