// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

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
