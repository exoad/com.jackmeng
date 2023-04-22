// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

import java.util.Iterator;

public class stl_RentalList< T >
    implements $RentaList< T >
{
  public enum RentalList_Style {
    TIMED, OPERATION, NONE;
  }

  private stl_LooseList< stl_Struct.struct_NamedPair< Long, T, String > > lot = new stl_LooseList<>();

}