// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.jvm.JvmOverloads
import com.jackmeng.stl.stl_Chrono
import java.text.SimpleDateFormat
import java.util.*

/**
 * A standard utility class for manipulating time
 *
 * @author Jack Meng
 */
object stl_Chrono
{
	@JvmOverloads
	fun format_millis(format:String?="MM_dd_YYYY__HH_mm_ss"):String
	{
		return format_time(
			format , System.currentTimeMillis()
		)
	}
	
	fun format_time(format:String? , t:Long):String
	{
		val e=Date(t)
		return SimpleDateFormat(format).format(e)
	}
}