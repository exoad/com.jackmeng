package com.jackmeng.stl;

import java.util.ArrayList;

public class stl_ConsumableList< T > extends ArrayList< T >
{

  @Override public T get(int index)
  {
    T e = super.get(index);
    remove(index);
    return e;
  }
}
