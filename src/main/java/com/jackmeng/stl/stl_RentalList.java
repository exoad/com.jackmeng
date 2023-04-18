// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

import java.util.Iterator;

public class stl_RentalList< T >
    implements Iterable< T >
{
  private stl_LooseList< stl_Struct.struct_Trio<> > list = new stl_LooseList<>();

  public stl_RentalList(T[] initials)
  {

  }

  @Override public Iterator< T > iterator()
  {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }

}