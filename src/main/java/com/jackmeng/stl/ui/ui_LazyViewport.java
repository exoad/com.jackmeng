// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.ui;

import javax.swing.JViewport;

import java.awt.Point;

/**
 * Mostly for a JScrollPane where an update in the component
 * view will not trigger the JScrollPane to update itself.
 *
 * @author Jack Meng
 */
public final class ui_LazyViewport
    extends JViewport
{

  private boolean locked = false;

  @Override public void setViewPosition(Point p)
  {
    if (locked())
      return;
    super.setViewPosition(p);
  }

  public boolean locked()
  {
    return locked;
  }

  public void locked(boolean locked)
  {
    this.locked = locked;
  }
}