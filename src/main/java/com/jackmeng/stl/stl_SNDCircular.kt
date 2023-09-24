// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import kotlin.jvm.JvmOverloads
import javax.sound.sampled.SourceDataLine
import kotlin.Throws
import java.io.IOException
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.UnsupportedAudioFileException
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine

class stl_SNDCircular @JvmOverloads constructor(private val buffer_size:Int=1024 , private val buffer:stl_CircularBuffer=stl_CircularBuffer())
{
	private var line:SourceDataLine?=null
	@Throws(IOException::class , LineUnavailableException::class , UnsupportedAudioFileException::class)
	fun open(file:File?)
	{
		val `in`=AudioSystem.getAudioInputStream(file)
		val format=`in`.format
		val info=DataLine.Info(SourceDataLine::class.java , format)
		line=AudioSystem.getLine(info) as SourceDataLine
		line!!.open(format)
		line!!.start()
		val buf=ByteArray(buffer_size)
		var bytesRead:Int
		while (`in`.read(buf).also { bytesRead=it }!=-1) buffer.push(buf , 0 , bytesRead)
		`in`.close()
	}
	
	fun play()
	{
		Thread {
			val data=ByteArray(buffer_size)
			while (buffer.used_sz()>0)
			{
				val bytesRead=buffer.pop(data , 0 , data.size)
				line!!.write(data , 0 , bytesRead)
			}
		}.start()
	}
	
	fun pause()
	{
		line!!.stop()
	}
	
	fun stop()
	{
		line!!.stop()
		line!!.flush()
		buffer.init()
	}
	
	fun close()
	{
		line!!.close()
		buffer.init()
	}
}