// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.HashMap
import java.util.zip.ZipInputStream
import java.util.zip.ZipEntry
import java.io.ByteArrayInputStream
import java.io.InputStream

class stl_ZipFileReader(inputStream:InputStream?)
{
	private val cache:MutableMap<String , ByteArray>
	
	init
	{
		cache=HashMap()
		if (inputStream!=null)
		{
			ZipInputStream(inputStream).use { zipInputStream->
				var entry:ZipEntry
				while (zipInputStream.nextEntry.also { entry=it }!=null)
				{
					val content=zipInputStream.readAllBytes()
					cache[entry.name]=content
				}
			}
		}
	}
	
	fun getStream(entryName:String):InputStream?
	{
		val content=cache[entryName]
		return content?.let { ByteArrayInputStream(it) }
	}
}