// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

interface $Checker< T >
{
  boolean is_valid(T type);

  default boolean is_all_valid(T[] types)
  {
    boolean e = false;
    for (T x : types)
      e = is_valid(x);
    return e;
  }
}