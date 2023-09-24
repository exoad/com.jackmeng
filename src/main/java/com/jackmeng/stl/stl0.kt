// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.jackmeng.stl.stl0
import kotlin.Throws
import java.lang.ClassNotFoundException
import java.util.*

/**
 * Internal Library functions
 */
internal class stl0 private constructor()
{
	val EXECS=Executors.newFixedThreadPool(8)
	
	companion object
	{
		val INTERNAL=stl0()
		val STL_TIMER0=Timer("STL_TIMER00_com.jackmeng")
		@Throws(ClassNotFoundException::class)
		fun isGeneric(str:String?):Boolean
		{
			return Class.forName(str).typeParameters.size>0
		}
		
		fun dirm():String
		{
			return System.getProperty("file.separator")
		}
		
		fun isGeneric(e:Class<*>):Boolean
		{
			return e.typeParameters.size>0
		}
	}
}