// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_ListenerPool
import com.jackmeng.stl.stl_Listener
import com.jackmeng.stl.stl_Struct.struct_Pair
import com.jackmeng.stl.stl_ListenerPool.ListenerPool_Attached.Attached_States
import kotlin.jvm.Synchronized
import com.jackmeng.stl.stl_Struct
import java.util.*
import java.util.function.Consumer

open class stl_ListenerPool<T>(private var name:String)
{
	class ListenerPool_Attached<T>(poolName:String):stl_ListenerPool<T>(poolName)
	{
		enum class Attached_States
		{
			ATTACHED , ADD_LISTENER , DETACHED , RMF_LISTENER
		}
		
		private val attachedListeners:MutableList<stl_Listener<struct_Pair<stl_Listener<T> , Attached_States>?>>
		
		init
		{
			attachedListeners=Vector(2)
		}
		
		@Synchronized
		fun attach(listener:stl_Listener<struct_Pair<stl_Listener<T> , Attached_States>?>)
		{
			attachedListeners.add(listener)
			listener.call(stl_Struct.make_pair(null , Attached_States.ATTACHED))
		}
		
		@Synchronized
		fun detach(listener:stl_Listener<struct_Pair<stl_Listener<T> , Attached_States>?>)
		{
			if (attachedListeners.contains(listener))
			{
				attachedListeners.remove(listener)
				listener.call(stl_Struct.make_pair(null , Attached_States.DETACHED))
			}
		}
		
		@Synchronized
		override fun add(listener:stl_Listener<T>)
		{
			super.add(listener)
			dispatch(stl_Struct.make_pair(listener , Attached_States.ADD_LISTENER))
		}
		
		@Synchronized
		override fun rmf(listener:stl_Listener<T>)
		{
			super.rmf(listener)
			dispatch(stl_Struct.make_pair(listener , Attached_States.RMF_LISTENER))
		}
		
		private fun dispatch(payload:struct_Pair<stl_Listener<T> , Attached_States>)
		{
			attachedListeners.forEach(Consumer { x:stl_Listener<struct_Pair<stl_Listener<T> , Attached_States>?>->
				x.call(
					payload
				)
			})
		}
		
		@Synchronized
		override fun kill()
		{
			super.kill()
			attachedListeners.clear()
		}
	}
	
	private val listeners:MutableList<stl_Listener<T?>>
	
	init
	{
		listeners=Vector(5)
	}
	
	fun name():String
	{
		return name
	}
	
	fun name(name:String)
	{
		this.name=name
	}
	
	@Synchronized
	open fun add(listener:stl_Listener<T?>)
	{
		listeners.add(listener)
	}
	
	@Synchronized
	open fun rmf(listener:stl_Listener<T?>)
	{
		listeners.remove(listener)
	}
	
	@Synchronized
	open fun kill()
	{
		listeners.clear()
	}
	
	/**
	 * It is highly suggested that you make sure that the payload is not null!
	 * @param payload The action to dispatch to all the listeners
	 */
	fun dispatch(payload:T)
	{
		listeners.forEach(Consumer { x:stl_Listener<T?>-> x.call(payload) })
	}
	
	override fun toString():String
	{
		return """
	    	$name
	    	$listeners
	    	""".trimIndent()
	}
}