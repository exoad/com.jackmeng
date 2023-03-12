// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

@FunctionalInterface public interface stl_Callback< T, E >
{
    T call(E arg);
}
