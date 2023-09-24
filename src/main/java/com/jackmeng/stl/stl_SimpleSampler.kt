// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Void
import kotlin.Throws
import java.io.IOException
import java.io.BufferedReader
import java.io.FileReader
import java.util.function.Consumer

class stl_SimpleSampler<T>(private val sampleRate:Int , private val callback:stl_Callback<Void , T>):Consumer<T>
{
	private var counter=0
	override fun accept(t:T)
	{
		if (counter==0) callback.call(t)
		counter=(counter+1)%sampleRate
	}
	
	companion object
	{
		@Throws(IOException::class)
		fun sampleFile(filePath:String? , sampleRate:Int , callback:stl_Callback<Void , String>)
		{
			filePath?.let { FileReader(it) }?.let { it->
				BufferedReader(it).use { reader->
					val sampler=stl_SimpleSampler(sampleRate , callback)
					var line:String
					while (reader.readLine().also { line=it }!=null) sampler.accept(line)
				}
			}
		}
	}
}