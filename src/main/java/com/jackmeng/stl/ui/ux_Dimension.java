// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.ui;

import java.awt.Dimension;
import java.io.Serializable;

/**
 * A more feature rich abstraction of the java.awt.Dimension
 *
 * @author Jack Meng
 */
public class ux_Dimension
    implements
    Serializable
{
  private int width = 0;
  private int height = 0;

  public ux_Dimension()
  {

  }

  public ux_Dimension(int w, int h)
  {
    this.width = Math.abs(w);
    this.height = Math.abs(h);
  }

  public ux_Dimension add(ux_Dimension e)
  {
    return new ux_Dimension(width + e.width, height + e.height);
  }

  public ux_Dimension sub(ux_Dimension e)
  {
    return new ux_Dimension(Math.abs(width - e.width), Math.abs(height - e.height));
  }

  public ux_Dimension scale(float factor)
  {
    return new ux_Dimension((int) width * (int) factor, height * (int) factor);
  }

  public int width()
  {
    return width;
  }

  public int height()
  {
    return height;
  }

  public ux_Dimension change(int width, int height)
  {
    return new ux_Dimension(width, height);
  }

  /**
   * Unless using raw with raw Swing Components (e.g. JPanel, JLabel, etc..), this
   * should never be called by the developer if using
   * com.jackmeng.stl.ui Components
   *
   * @return A java.awt.Dimension based object
   */
  public Dimension make()
  {
    return new Dimension(width, height);
  }

  public static ux_Dimension make(Dimension dim)
  {
    return new ux_Dimension(dim.width, dim.height);
  }
}