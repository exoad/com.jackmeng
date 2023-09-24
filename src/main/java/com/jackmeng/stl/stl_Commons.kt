// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Runnable
import com.jackmeng.stl.stl0
import com.jackmeng.stl.stl_Callback
import java.lang.Void
import com.jackmeng.stl.types.Null_t
import java.util.concurrent.Callable
import com.jackmeng.stl.stl_Str
import com.jackmeng.stl.stl_Commons
import com.jackmeng.stl.stl_ArrItr
import com.jackmeng.stl.stl_Commons.SysArch
import com.jackmeng.stl.stl_Listener
import java.util.function.Consumer

object stl_Commons
{
	fun COMMON_ASCII_TABLE():CharArray
	{
		return charArrayOf(
			'0' ,
			'1' ,
			'2' ,
			'3' ,
			'4' ,
			'5' ,
			'6' ,
			'7' ,
			'8' ,
			'9' ,
			'A' ,
			'B' ,
			'C' ,
			'D' ,
			'E' ,
			'F' ,
			'G' ,
			'H' ,
			'I' ,
			'J' ,
			'K' ,
			'L' ,
			'M' ,
			'N' ,
			'O' ,
			'P' ,
			'Q' ,
			'R' ,
			'S' ,
			'T' ,
			'U' ,
			'V' ,
			'W' ,
			'X' ,
			'Y' ,
			'Z' ,
			'a' ,
			'b' ,
			'c' ,
			'd' ,
			'e' ,
			'f' ,
			'g' ,
			'h' ,
			'i' ,
			'j' ,
			'k' ,
			'l' ,
			'm' ,
			'n' ,
			'o' ,
			'p' ,
			'q' ,
			'r' ,
			's' ,
			't' ,
			'u' ,
			'v' ,
			'w' ,
			'x' ,
			'y' ,
			'z' ,
			'#' ,
			'$' ,
			'%' ,
			'*' ,
			'+' ,
			',' ,
			'-' ,
			'.' ,
			':' ,
			';' ,
			'=' ,
			'?' ,
			'@' ,
			'[' ,
			']' ,
			'^' ,
			'_' ,
			'{' ,
			'|' ,
			'}' ,
			'~'
		)
	}
	
	fun async(task:Runnable?)
	{
		stl0.INTERNAL.EXECS.submit(task)
	}
	
	fun async(task:stl_Callback<Void? , out Null_t?>)
	{
		stl0.INTERNAL.EXECS.submit<Void?> { task.call(null) }
	}
	
	fun <T> array_has(array:Array<T> , key:T):Boolean
	{
		for (r in array) if (r==key) return true
		return false
	}
	
	fun array_dim(type:Class<*>):Int
	{
		return stl_Str.instances(type.canonicalName , "[]")
	}
	
	fun <T , E> if_else(expr:Boolean , if_:T , else_:E):Any
	{
		return (if (expr) if_ else else_)!!
	}
	
	fun <T , E> nilable(_check:T? , else_:E):Any
	{
		return if_else(_check==null , _check , else_)
	}
	
	fun is_array(type:Class<*>):Boolean
	{
		return type.canonicalName.contains("[]")
	}
	
	fun <T> for_each(e:Array<T>?):stl_ArrItr<T>
	{
		return object:stl_ArrItr<T>(e!!)
		{
			override fun remove()
			{
			}
		}
	}
	
	fun sys_bitness():SysArch
	{
		val arch=System.getProperty("os.arch").lowercase()
		return if (arch.contains("64")) SysArch.X64 else if (arch.contains("86")) SysArch.X86 else if (arch.contains("arm")) SysArch.ARM else SysArch.ERR
	}
	
	fun <T> consumer2listener(consume:Consumer<T>):stl_Listener<T>
	{
		return object:stl_Listener<T>
		{
			override fun call(e:T):Void
			{
				consume.accept(e)
				return null
			}
		}
	}
	
	fun tmpdir():String
	{
		return System.getProperty("java.io.tmpdir")
	}
	
	enum class SysArch
	{
		X64 , X86 , ARM , ERR
	}
}