// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl0.Companion.isGeneric
import com.jackmeng.stl.stl_Struct.struct_Pair
import com.jackmeng.stl.stl_Struct.struct_Triple
import com.jackmeng.stl.stl_Struct.struct_Quad
import com.jackmeng.stl.stl_Struct.struct_NamedPair
import com.jackmeng.stl.stl0

object stl_Struct
{
	fun <F , L> make_pair(first:F , last:L):struct_Pair<F , L>
	{
		return struct_Pair.make(first , last)
	}
	
	fun <F , M , L> make_triple(first:F , middle:M , last:L):struct_Triple<F , M , L>
	{
		return struct_Triple(first , middle , last)
	}
	
	fun <F , M1 , M2 , L> make_quad(
			first:F , middle1:M1 , middle2:M2 , last:L
	):struct_Quad<F , M1 , M2 , L>
	{
		return struct_Quad(first , middle1 , middle2 , last)
	}
	
	fun <F , L , N> make_namedpair(
			first:F , last:L , firstname:N , secondname:N
	):struct_NamedPair<F , L , N>
	{
		return struct_NamedPair(first , last , firstname , secondname)
	}
	
	class struct_NamedPair<A , B , T>(first:A , second:B , var first_:T , var second_:T):
			struct_Pair<A , B>(first , second)
	{
		override fun toString():String
		{
			return super.toString()+first_+"+"+second_
		}
	}
	
	open class struct_Pair<A , B>(var first:A , var second:B)
	{
		override fun toString():String
		{
			return (this.hashCode()
				.toString()+"_PAIR_"+first.javaClass.getSimpleName()+" + "+second.javaClass.getSimpleName()+":["+first+","+second+"]")
		}
		
		override fun equals(cum:Any?):Boolean
		{
			return if (cum !is struct_Pair<* , *>) false else cum.first==first&&cum.second==second
		}
		
		override fun hashCode():Int
		{
			return (((1 shl 1)-1 and first.hashCode() xor ((1 shl Integer.BYTES*8+1)-1 and first.hashCode() shr (Integer.BYTES*8/2))) shl (Integer.BYTES*8)/2 or ((1 shl 1)-1 and second.hashCode() xor ((1 shl Integer.BYTES*8+1)-1 and second.hashCode() shr (Integer.BYTES*8/2))))
		}
		
		companion object
		{
			fun <F , L> make(first:F , last:L):struct_Pair<F , L>
			{
				return struct_Pair(first , last)
			}
			
			fun make(e:Array<Any?>):struct_Pair<* , *>
			{
				return make(e[0] , e[1])
			}
		}
	}
	
	class struct_Triple<A , B , C>(var first:A , var second:B , var third:C)
	{
		fun to_array():Array<Any>?
		{
			return if (isGeneric(first.javaClass)||isGeneric(second.javaClass)||isGeneric(third.javaClass)) null
			else arrayOf(
				first ,
				second ,
				third
			)
		}
	}
	
	class struct_Quad<A , B , C , D>(var first:A , var second:B , var third:C , var fourth:D)
}