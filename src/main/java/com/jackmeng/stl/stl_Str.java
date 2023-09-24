// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.StringBuilder
import java.util.Locale

object stl_Str
{
	fun from_char_arr(e:CharArray):String
	{
		val sb=StringBuilder()
		for (x in e) sb.append(x)
		return sb.toString()
	}
	
	fun n_copies(n:Int , sequence:String?):String
	{
		var n=n
		val sb=StringBuilder()
		while (n-->0) sb.append(sequence)
		return sb.toString()
	}
	
	fun is_empty(e:CharSequence?):Boolean
	{
		if (e==null||e.length==0||e.isEmpty()) return true
		if (e is String&&e.isBlank()) return true
		assert(e is String)
		return (e as String).matches("\\s+")
	}
	
	fun interpolate0(keyStart:String , keyEnd:String , template:String , vararg payloads:String):String
	{
		var template=template
		var i=0
		while (i<payloads.size)
		{
			template=template.replace(keyStart+payloads[i]+keyEnd , payloads[i+1])
			i+=2
		}
		return template
	}
	
	fun interpolate1_1(keyStart:String , keyEnd:String , template:String , vars:Map<String , String?>):String
	{
		var template=template
		for ((key , value) in vars) template=template.replace(keyStart+key+keyEnd , value!!)
		return template
	}
	
	fun interpolate2(keyStart:String , keyEnd:String , template:String , vararg payloads:String?):String
	{
		var template=template
		for (i in payloads.indices) template=template.replace(keyStart+i+keyEnd , payloads[i]!!)
		return template
	}
	
	fun interpolate3(keyStart:String , keyEnd:String , template:String , payloads:Array<Array<String>>):String /*
     * Payload formatting:
     * {
     * {
     * key,
     * value
     * }
     * }
     */
	{
		var template=template
		for (a in payloads) template=template.replace(keyStart+a[0]+keyEnd , a[1])
		return template
	}
	
	fun instances(payload:String , toFind:String):Int
	{
		var last=0
		var i=0
		while (payload.indexOf(toFind , last).also { last=it }!=-1)
		{
			i++
			last+=toFind.length-1
		}
		return i
	}
	
	fun is_one_type_commaed(input:String):Boolean
	{
		val parts=input.split(",".toRegex()).toTypedArray()
		var clazz:Class<*>?=null
		var isValid=true
		for (part in parts)
		{
			if (part.isEmpty())
			{
				isValid=false
				break
			}
			val obj:Any=if (part.length==1) part[0] else part
			if (clazz==null)
			{
				clazz=obj.javaClass
			}
			else
			{
				if (obj.javaClass!=clazz)
				{
					isValid=false
					break
				}
			}
		}
		return isValid
	}
	
	fun parse_bool(content:String):Boolean
	{
		var content=content
		content=content.lowercase(Locale.getDefault())
		return content=="1"||content=="on"||content=="true"||content=="positive"||content=="in"
	}
	
	fun insert_nl(input:String , maxChars:Int , optionalPad:String?):String
	{
		val pad=optionalPad ?: "\n"
		val sb=StringBuilder()
		val w=input.split(" ".toRegex()).toTypedArray()
		var i=0
		for (r in w)
		{
			if (i+r.length>maxChars)
			{
				sb.append(pad)
				i=0
			}
			if (r.length>maxChars)
			{
				var j=0
				while (j<r.length)
				{
					sb.append(if (j+maxChars<r.length) r.substring(j , j+maxChars) else r.substring(j))
					j+=maxChars
					sb.append(pad)
				}
			}
			else
			{
				sb.append(r).append(" ")
				i+=r.length+1
			}
		}
		return sb.toString().trim { it<=' ' }
	}
}