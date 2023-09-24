// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.ArrayList

class stl_ConsumableList<T>:ArrayList<T>()
{
	override fun get(index:Int):T
	{
		val e=super.get(index)
		removeAt(index)
		return e
	}
}