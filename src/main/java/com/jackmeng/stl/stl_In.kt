// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
package com.jackmeng.stl

import java.io.BufferedReader
import java.util.StringTokenizer
import kotlin.jvm.Synchronized
import kotlin.jvm.JvmOverloads
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

class stl_In(e:InputStream?)
{
	private val io:InputStream
	private var br:BufferedReader
	private var st:StringTokenizer?=null
	
	init
	{
		io=e ?: System.`in`
		br=BufferedReader(InputStreamReader(io))
	}
	
	@Synchronized
	fun reader(br:BufferedReader)
	{
		this.br=br
	}
	
	fun reader():BufferedReader
	{
		return br
	}
	
	/**
	 * Reads a single token from the input.
	 * @param errorCallback
	 * @return
	 */
	@JvmOverloads
	fun next(
			errorCallback:stl_ErrCall=stl_ErrCall { x:Exception->
				x.printStackTrace()
				null
			}
	):String
	{
		try
		{
			while (st==null||!st!!.hasMoreTokens()) st=StringTokenizer(br.readLine())
		} catch (e:IOException)
		{
			errorCallback.call(e)
		}
		return st.toString()
	}
	
	/**
	 * Reads an entire line
	 * @param errorCallback Provided custom error callback handler
	 * @return The String content that was read back
	 */
	@JvmOverloads
	fun nextln():String
	{
		var x=""
		try
		{
			x=br.readLine().trim { it<=' ' }
		} catch (e:IOException)
		{
			e.printStackTrace()
		}
		return x
	}
}