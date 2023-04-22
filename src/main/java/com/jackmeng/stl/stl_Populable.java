// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

public abstract interface stl_Populable< T >
{
  void add_1(T e);

  T add_2(T e);

  void add_3(T e, int i);

  T add_4(T e, int i);

  void remove_1(T e);

  int remove_2(T e);

  void remove_3(int i);

  int remove_4(int i);

  default int len()
  {
    return 0;
  }

  default void remove_all()
  {
    int x = len();
    while(x -- > 0) remove_3(x);
  }
}