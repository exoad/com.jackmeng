// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class stl_OutputCache< I, O >
{

  private final Function< I, O > function;
  private final Map< I, O > cache = new HashMap<>();

  public stl_OutputCache(Function< I, O > function)
  {
    this.function = function;
  }

  public O get(I input)
  {
    O output = cache.get(input);
    if (output == null)
    {
      output = function.apply(input);
      cache.put(input, output);
    }
    return output;
  }

  public void clearCache()
  {
    cache.clear();
  }
}