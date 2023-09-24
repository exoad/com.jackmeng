// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.FunctionalInterface
import com.jackmeng.stl.stl_Callback
import java.lang.Void

@FunctionalInterface
interface stl_Listener<E>:stl_Callback<Void? , E>