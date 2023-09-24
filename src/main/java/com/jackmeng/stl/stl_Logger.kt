// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl0.Companion.dirm
import com.jackmeng.stl.stl_Chrono.format_millis
import com.jackmeng.stl.stl_Commons.tmpdir
import java.lang.Runnable
import java.io.File
import java.util.Stack
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import kotlin.jvm.Synchronized
import java.io.IOException
import java.io.Serializable
import java.util.TimerTask
import java.lang.StringBuilder
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.ArrayList

// import java.lang.ref.Reference;
// import java.lang.ref.SoftReference;
/**
 * @author Jack Meng
 */
class stl_Logger(private val name:String , private val saveLocation:String , private val try_save_ms_periodic:Long):
		Runnable , Serializable
{
	enum class Logger_DisableBehavior
	{
		DROP_ALL , KEEP_ALL , CACHE_OLD
	}
	
	private val file:File=File(saveLocation+dirm()+name+"_"+format_millis()+".log")
	private val logs=Stack<String>()
	private var running=false
	private var enabled=true
	private val saving=AtomicBoolean(false)
	
	@Transient
	private val afterRoutine:MutableList<Runnable>
	
	@Transient
	private var COLLECTED=0L
	private val CYCLE=AtomicLong(0L)
	private var log_char_per_line=70
	
	//private final Reference<Stack<String>> logs_cache = new SoftReference<>(new Stack<>());
	//private Logger_DisableBehavior disableBehavior = Logger_DisableBehavior.KEEP_ALL;
	init
	{
		afterRoutine=ArrayList(10)
	}
	
	constructor(loggerName:String , save_time:Long):this(loggerName , tmpdir() , save_time)
	
	// public Logger_DisableBehavior disableBehavior()
	// {
	//     return disableBehavior;
	// }
	// public synchronized Logger_DisableBehavior disableBehavior(Logger_DisableBehavior e)
	// {
	//     Logger_DisableBehavior old = this.disableBehavior;
	//     this.disableBehavior = e;
	//     return old;
	// }
	fun enable(e:Boolean)
	{
		enabled=e
	}
	
	fun enabled():Boolean
	{
		return enabled
	}
	
	fun char_per_line(i:Int)
	{
		log_char_per_line=i
	}
	
	fun char_per_line():Int
	{
		return log_char_per_line
	}
	
	constructor(loggerName:String , saveLocation:String):this(loggerName , saveLocation , 4000L)
	
	@Synchronized
	fun push(contents:Any)
	{
		val placeholder=String.format("%09d" , COLLECTED+1)+" | "+String.format(
			"%09d" ,
			CYCLE.get()
		)+"   |   "+format_millis("HH:mm:ss MM/dd/YYYY")+"    |    "
		if (saving.get()) afterRoutine.add(Runnable {
			logs.push(
				placeholder+stl_Str.insert_nl(
					contents.toString() , char_per_line() , """
 	
 	${stl_Str.n_copies(placeholder.length-5 , " ")}|    
 	""".trimIndent()
				)
			)
		})
		else logs.push(
			placeholder+stl_Str.insert_nl(
				contents.toString() , char_per_line() , """
 	
 	${stl_Str.n_copies(placeholder.length-5 , " ")}|    
 	""".trimIndent()
			)
		)
		COLLECTED++
	}
	
	@Synchronized
	fun log(vararg contents:Any)
	{
		for (e in contents) push(e)
	}
	
	@Synchronized
	fun kill()
	{
		afterRoutine.clear()
		logs.clear()
		stl0.STL_TIMER0.cancel()
		Thread.currentThread().interrupt()
	}
	
	fun name():String
	{
		return name
	}
	
	fun saveLocation():String
	{
		return saveLocation
	}
	
	@Synchronized
	override fun run()
	{
		if (!running&&enabled)
		{
			if (file.exists()) file.delete()
			try
			{
				file.createNewFile()
			} catch (e:IOException)
			{
				e.printStackTrace()
			}
			stl0.STL_TIMER0.schedule(object:TimerTask()
			{
				override fun run()
				{
					if (enabled)
					{
						if (logs.size>0)
						{
							saving.set(true)
							synchronized(logs) {
								val sb=StringBuilder()
								while (!logs.isEmpty()) sb.append(logs.pop()).append('\n')
								try
								{
									Files.write(
										file.toPath() ,
										sb.toString().toByteArray() ,
										StandardOpenOption.WRITE ,
										StandardOpenOption.APPEND
									)
								} catch (e:IOException)
								{
									e.printStackTrace()
								}
								if (afterRoutine.size>0) for (i in afterRoutine.indices) afterRoutine.removeAt(i).run()
							}
							saving.set(false)
							CYCLE.set(CYCLE.get()+1L)
						}
					}
				}
			} , 500L , try_save_ms_periodic)
			running=true
		}
	}
}