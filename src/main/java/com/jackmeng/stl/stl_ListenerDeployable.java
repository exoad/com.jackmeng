// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public interface stl_ListenerDeployable<A>
    extends stl_Callback<Void, Iterable<A>>
{
  default Void call(Iterable<A> e)
  {
    return (Void) null;
  }

  default void empty(int length)
  {

  }
}
