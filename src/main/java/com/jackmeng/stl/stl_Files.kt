package com.jackmeng.stl

import com.jackmeng.stl.stl_Callback
import java.lang.Void
import java.io.PrintWriter
import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.lang.Exception
import java.net.URI

object stl_Files
{
	/**
	 * Quick write (not speed wise ([java.io.PrintWriter] is used))
	 *
	 * @param content
	 * @param fileName
	 * @param errorHandler
	 */
	fun fwrite_1(fileName:String? , errorHandler:stl_Callback<Void? , Exception?> , content:String?)
	{
		try
		{
			val pw=PrintWriter(fileName)
			pw.print(content)
			pw.flush()
			pw.close()
		} catch (e:Exception)
		{
			errorHandler.call(e)
		}
	}
	
	fun uri(fileName:String?):URI
	{
		return File(fileName).toPath().toUri()
	}
	
	fun erasure_create_file(fileName:String? , errorHandler:stl_Callback<Void? , Exception?>)
	{
		val f=File(fileName)
		if (f.exists()) f.delete()
		try
		{
			f.createNewFile()
		} catch (e:Exception)
		{
			errorHandler.call(e)
		}
	}
	
	fun fappend_1(fileName:String? , errorHandler:stl_Callback<Void? , Exception?> , content:String?)
	{
		try
		{
			val pw=PrintWriter(fileName)
			pw.append(content)
			pw.flush()
			pw.close()
		} catch (e:Exception)
		{
			errorHandler.call(e)
		}
	}
	
	/**
	 * Read files by String (char[]) lines
	 *
	 * @param fileName
	 * @param errorHandler
	 * @param consumer
	 */
	fun fread_bl_1(
			fileName:String? , errorHandler:stl_Callback<Void? , Exception?> , consumer:stl_Callback<Void? , String?>
	)
	{
		try
		{
			BufferedReader(FileReader(fileName)).use { br-> while (br.ready()) consumer.call(br.readLine()) }
		} catch (e:Exception)
		{
			errorHandler.call(e)
		}
	}
	
	/**
	 * Read by byte
	 *
	 * @param fileName
	 * @param errorHandler
	 * @param consumer
	 */
	fun fread_s_1(
			fileName:String? , errorHandler:stl_Callback<Void? , Exception?> , consumer:stl_Callback<Void? , Byte?>
	)
	{
		try
		{
			BufferedReader(FileReader(fileName)).use { br->
				while (br.ready()) consumer.call(
					(br.read().toByte() and 0xFF).toByte()
				)
			}
		} catch (e:Exception)
		{
			errorHandler.call(e)
		}
	}
}