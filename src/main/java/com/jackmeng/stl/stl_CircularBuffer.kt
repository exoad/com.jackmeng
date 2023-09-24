// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.jvm.JvmOverloads
import java.lang.Void
import java.nio.ByteBuffer

class stl_CircularBuffer @JvmOverloads constructor(init_Size:Int=255)
{
	/*----------------------------------------------------------------- /
    / generic implementation of a FIFO Circular or ring buffer strategy /
    /------------------------------------------------------------------*/
	private val buffer:ByteArray
	private var start=0
	private var sz=0
	
	init
	{
		buffer=ByteArray(init_Size)
	}
	
	fun used_sz():Int
	{
		return sz
	}
	
	fun total_sz():Int
	{
		return buffer.size
	}
	
	fun free_sz():Int
	{
		return total_sz()-used_sz()
	}
	
	fun drop(elementsCount:Int)
	{
		val t=Math.min(elementsCount , sz)
		val sz_1=sz-t
		val diff=sz-sz_1
		start=calc_offset(start , diff , buffer.size)
		sz=sz_1
	}
	
	fun push(data:ByteArray? , i:Int , len:Int):Int
	{
		var rem=len
		var off=i
		do
		{
			val off_set=offset()
			val a=free(off_set)
			if (a==0) break
			val copy=rem.coerceAtMost(a)
			if (data!=null)
			{
				System.arraycopy(data , off , buffer , off_set , copy)
			}
			next(copy)
			rem-=copy
			off+=copy
		} while (rem>0)
		return len-rem
	}
	
	fun opush(data:ByteArray? , i:Int , len:Int)
	{
		var rem=len
		var off=i
		if (!overflows(len))
		{
			System.arraycopy(data , i , buffer , offset() , len)
			next(len)
			return
		}
		do
		{
			val off_set=offset()
			val a=free(off_set)
			if (a==0) break
			val copy=rem.coerceAtMost(a)
			if (data!=null)
			{
				System.arraycopy(data , off , buffer , off_set , copy)
			}
			next(copy)
			rem-=copy
			off+=copy
		} while (rem>0)
	}
	
	fun init()
	{/*---------------------------------------------------------------------------------------- /
        / returns the buffer to the original state, and is unnecessary for direct initalialization /
        /-----------------------------------------------------------------------------------------*/
		start=0
		sz=0
	}
	
	fun offset():Int
	{
		return calc_offset(start , sz , buffer.size)
	}
	
	fun peek(e:stl_Callback<Void? , ByteBuffer?>)
	{
		val f1=start
		var f1_sz=buffer.size-start
		if (f1_sz>sz) f1_sz=sz
		val f=ByteBuffer.wrap(buffer , f1 , f1_sz)
		e.call(f)
		if (f1_sz!=sz)
		{
			val f2=0
			val f2_sz=sz-f1_sz
			val er=ByteBuffer.wrap(buffer , f2 , f2_sz)
			e.call(er)
		}
	}
	
	fun peek(data:ByteArray? , i:Int , len:Int):Int
	{
		if (sz==0)
		{
			return 0
		}
		var read=len.coerceAtMost(sz)
		val offset=offset()
		val f1=start
		var f1_sz=buffer.size-start
		if (f1_sz>sz) f1_sz=sz
		if (f1_sz>=read) f1_sz=read
		if (data!=null) System.arraycopy(data , f1 , data , i , f1_sz)
		read-=f1_sz
		if (read==0) return f1_sz
		val f2=if (offset<=start) 0 else sz
		var f2_sz=buffer.size-f1_sz
		if (f2_sz>=read) f2_sz=read
		if (data!=null) System.arraycopy(data , f2 , data , i+f1_sz , f2_sz)
		read-=f2_sz
		return len-read
	}
	
	fun pop(bytes:ByteArray? , i:Int , len:Int):Int
	{
		val read=peek(bytes , i , len)
		drop(read)
		return read
	}
	
	fun overflows(n:Int):Boolean
	{
		return offset()+n>buffer.size
	}
	
	fun next(n:Int)
	{
		val sz_1=sz+n
		var overflow=0
		if (sz_1>buffer.size)
		{
			sz=buffer.size
			overflow=sz_1-buffer.size
		}
		else sz=sz_1
		start=calc_offset(start , overflow , buffer.size)
	}
	
	fun free(n:Int):Int
	{
		return if (start>=n) if (sz>0) n-start else buffer.size-n else buffer.size-n
	}
	
	companion object
	{
		private fun calc_offset(len_0:Int , sz:Int , buff_len:Int):Int
		{
			return (sz+len_0)%buff_len
		}
	}
}