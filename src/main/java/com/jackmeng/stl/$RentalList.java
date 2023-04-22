// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

import com.jackmeng.stl.stl_RentalList.RentalList_Style;

abstract interface $RentaList< T >
    extends Iterable< T >
{

  /**
   * Set the running style of the rentalist. This operation can be done at any
   * time, but it is preferable to set the operation immediately right
   * after initialization.
   *
   * @param style
   *          See stl_RentalList.RentaList_Style
   * @param argument
   *          If the provided style requires a time argument, this is the place to
   *          put that. If you choose the None style, preferably you want to set
   *          the value to "-1", but any value would do.
   */
  void style(RentalList_Style style, int argument);

  RentalList_Style style();

  void style_argument(int argument);

  int style_argument();

}