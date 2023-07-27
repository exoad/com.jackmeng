// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.ui;

import java.util.function.Consumer;

/**
 * Provides some very basic and tedious reducing functions for both the library
 * itself and outside developers
 *
 * @author Jack Meng
 */
public final class stl_UIHelper
{
  private stl_UIHelper()
  {
  }

  public static < T > ux_Listener< T > make(Consumer< T > convertTo)
  {
    return convertTo::accept;
  }
}