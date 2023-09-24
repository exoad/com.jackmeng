// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl.types

import com.jackmeng.stl.types.Null_t

object Null_t
{
	const val STRING_VALUE="\u0000b0"
	const val INT_VALUE=0
	const val DOUBLE_VALUE=0.0
	const val SHORT_VALUE=INT_VALUE.toShort()
	const val FLOAT_VALUE=DOUBLE_VALUE.toFloat()
	const val LONG_VALUE=INT_VALUE.toLong()
	const val BYTE_VALUE:Byte=0
}