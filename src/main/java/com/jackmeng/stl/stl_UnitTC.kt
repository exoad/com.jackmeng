// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.math.abs
import java.lang.Runnable
import java.lang.Exception

class stl_UnitTC(private val name:String , private val action:stl_Callback<* , *>):Runnable
{
	private var expects:Any?=null
	
	constructor(name:String , action:stl_Callback<Any? , *> , expect:Any?):this(name , action)
	{
		expects=expect
	}
	
	override fun run()
	{
		println(
			"""
	Testcase: $name
	${action.hashCode()}
	""".trimIndent()
		)
		val s=(Runtime.getRuntime().maxMemory()-((Runtime.getRuntime().totalMemory()-Runtime.getRuntime()
			.freeMemory())/1024/1024))
		val l=System.currentTimeMillis()
		val returns:Any=try
		{
			action.call(null)
		} catch (e:Exception)
		{
			println(
				stl_AnsiMake(
					arrayOf(
						stl_AnsiColors.GREEN_BG , stl_AnsiColors.BLACK_TXT
					) , arrayOf("FAILED ON EXCEPTION")
				)
			)
			e.printStackTrace()
			return
		}
		val t=System.currentTimeMillis()-l
		println(
			"""
	Finished in: ${t}ms
	Memory used: 
	""".trimIndent()+abs(
				(Runtime.getRuntime().maxMemory()-((Runtime.getRuntime().totalMemory().toDouble()-Runtime.getRuntime()
					.freeMemory())/1024/1024))-s
			)+" Mb"+if (expects==null) "\nCase Status: "+stl_AnsiMake(
				arrayOf(
					stl_AnsiColors.BLUE_BG , stl_AnsiColors.BLACK_TXT
				) , arrayOf("NONE")
			)
			else "\nCase status: "+((if (returns==expects) stl_AnsiMake(
				arrayOf(
					stl_AnsiColors.GREEN_BG , stl_AnsiColors.BLACK_TXT
				) , arrayOf("PASSED")
			)
			else stl_AnsiMake(
				arrayOf(
					stl_AnsiColors.RED_BG , stl_AnsiColors.BLACK_TXT
				) , arrayOf("FAILED")
			)).toString()+"\nExpected: "+expects+"\nReturned: "+returns)
		)
	}
}