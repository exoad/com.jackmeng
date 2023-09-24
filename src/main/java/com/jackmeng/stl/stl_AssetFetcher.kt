// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_AssetFetcher.assetfetcher_FetcherStyle
import javax.swing.ImageIcon
import java.lang.NullPointerException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import java.io.IOException
import kotlin.Throws
import java.lang.Exception
import java.net.URL
import java.util.*

class stl_AssetFetcher(style:assetfetcher_FetcherStyle)
{
	enum class assetfetcher_FetcherStyle
	{
		WEAK , STRONG
	}
	
	private val lazyResource_cache:MutableMap<String , Any>
	
	init
	{
		lazyResource_cache=if (style==assetfetcher_FetcherStyle.WEAK) WeakHashMap() else HashMap()
	}
	
	/**
	 * @param path
	 * @return ImageIcon
	 */
	fun image_icon(path:String):ImageIcon?
	{
		if (lazyResource_cache.containsKey(path)&&lazyResource_cache[path] is ImageIcon) return lazyResource_cache[path] as ImageIcon?
		val i:ImageIcon
		i=try
		{
			ImageIcon(
				Objects.requireNonNull(javaClass.getResource(path))
			)
		} catch (e:NullPointerException)
		{
			return ImageIcon(path)
		}
		lazyResource_cache[path]=i
		return i
	}
	
	fun image(path:String):BufferedImage?
	{
		if (lazyResource_cache.containsKey(path)&&lazyResource_cache[path] is BufferedImage) return lazyResource_cache[path] as BufferedImage?
		val i:BufferedImage
		i=try
		{
			ImageIO.read(Objects.requireNonNull(javaClass.getResource(path)))
		} catch (e:Exception)
		{
			try
			{
				ImageIO.read(File(path))
			} catch (e1:IOException)
			{
				return null
			}
		}
		lazyResource_cache[path]=i
		return i
	}
	
	/**
	 * @param path
	 * @return File
	 */
	fun file(path:String):File?
	{
		if (lazyResource_cache.containsKey(path)) return lazyResource_cache[path] as File?
		val i:File
		i=try
		{
			File(
				Objects.requireNonNull(javaClass.getResource(path)).file
			)
		} catch (e:NullPointerException)
		{
			File(path)
		}
		lazyResource_cache[path]=i
		return i
	}
	
	@Throws(IOException::class)
	fun url(url:String):ByteArray?
	{
		if (lazyResource_cache.containsKey(url)&&lazyResource_cache[url] is ByteArray) return lazyResource_cache[url] as ByteArray?
		val resourceUrl=URL(url)
		resourceUrl.openStream().use { inputStream->
			val resourceBytes=inputStream.readAllBytes()
			lazyResource_cache[url]=resourceBytes
			return resourceBytes
		}
	}
}