// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public final class stl_Native {
  public static stl_Native NATIVE = null;

  public static void i_loadLibrary(String name)
  {
    System.loadLibrary(name);
  }

  public static void i_load(String name)
  {
    System.load(name);
  }

  public stl_Native getInstance()
  {
    if(NATIVE == null)
      NATIVE = new stl_Native();
    return NATIVE;
  }

  public native Object objFromHash(int hashcode);

}