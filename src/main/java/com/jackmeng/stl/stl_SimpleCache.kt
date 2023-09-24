// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.*

class stl_SimpleCache<K , V>(timeoutMillis:Long , toleranceMillis:Long)
{
	private val cache:MutableMap<K , CacheEntry<V>>
	private val timeoutMillis:Long
	private val toleranceMillis:Long
	private val cleanupTimer:Timer
	
	init
	{
		cache=HashMap<K , CacheEntry<V>>()
		this.timeoutMillis=timeoutMillis
		this.toleranceMillis=toleranceMillis
		cleanupTimer=Timer(true)
		cleanupTimer.schedule(CleanupTask() , timeoutMillis , timeoutMillis)
	}
	
	fun put(key:K , value:V)
	{
		synchronized(cache) { cache.put(key , CacheEntry<V>(value)) }
	}
	
	operator fun get(key:K):V?
	{
		synchronized(cache) {
			val entry:CacheEntry<V>?=cache[key]
			return if (entry!=null)
			{
				entry.lastAccessTimeMillis=System.currentTimeMillis()
				entry.value
			}
			else null
		}
	}
	
	private inner class CacheEntry<T> internal constructor(val value:T)
	{
		var lastAccessTimeMillis:Long=System.currentTimeMillis()
		
	}
	
	private inner class CleanupTask:TimerTask()
	{
		override fun run()
		{
			val nowMillis=System.currentTimeMillis()
			synchronized(cache) {
				cache.entries.removeIf { (_ , value):Map.Entry<K , CacheEntry<V>>->
					val lastAccessTimeMillis:Long=value.lastAccessTimeMillis
					val ageMillis=nowMillis-lastAccessTimeMillis
					ageMillis>timeoutMillis+toleranceMillis
				}
			}
		}
	}
}