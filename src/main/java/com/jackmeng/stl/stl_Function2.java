// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public interface stl_Function2< T1, T2, R >
{
    R apply(T1 t1, T2 t2);

    default stl_Function< T1, stl_Function< T2, R > > curry()
    {
        return t1 -> t2 -> apply(t1, t2);
    }
}
