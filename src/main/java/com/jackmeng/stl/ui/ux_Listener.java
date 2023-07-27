// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.ui;

import com.jackmeng.stl.stl_Callback;

@FunctionalInterface public abstract interface ux_Listener< T >
    extends stl_Callback< Void, T >
{

  void consume(T e);

  default Void call(T e)
  {
    consume(e);
    return (Void) null;
  }
}