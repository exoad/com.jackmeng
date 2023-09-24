// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import javax.swing.JComponent
import java.util.concurrent.Flow.Subscriber
import java.lang.Runnable
import java.util.concurrent.SubmissionPublisher
import com.jackmeng.stl.stl_LazyJComponent.SubscriptionImpl
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.swing.SwingUtilities
import java.lang.InterruptedException
import java.lang.reflect.InvocationTargetException
import java.awt.Graphics
import java.awt.Dimension
import kotlin.jvm.Volatile
import java.util.concurrent.Flow

class stl_LazyJComponent:JComponent() , Subscriber<Runnable?>
{
	@Transient
	private val publisher=SubmissionPublisher<Runnable>()
	
	@Transient
	private val subscription:Flow.Subscription=SubscriptionImpl()
	
	@Transient
	private val executorService=Executors.newSingleThreadExecutor()
	
	init
	{
		publisher.subscribe(this)
	}
	
	override fun onSubscribe(subscription:Flow.Subscription)
	{
		subscription.request(1)
	}
	
	override fun onNext(task:Runnable?)
	{
		try
		{
			SwingUtilities.invokeAndWait(task)
		} catch (e:InterruptedException)
		{
			Thread.currentThread().interrupt()
			e.printStackTrace()
		} catch (e:InvocationTargetException)
		{
			Thread.currentThread().interrupt()
			e.printStackTrace()
		}
		repaint()
		subscription.request(1)
	}
	
	override fun onError(throwable:Throwable)
	{
		throwable.printStackTrace()
	}
	
	override fun onComplete()
	{ // Nothing to do here
	}
	
	fun queueTask(task:Runnable)
	{
		publisher.submit(task)
	}
	
	fun stop()
	{
		executorService.shutdown()
	}
	
	public override fun paintComponent(g:Graphics)
	{
		super.paintComponent(g)
	}
	
	override fun getPreferredSize():Dimension
	{
		return Dimension(400 , 400)
	}
	
	private class SubscriptionImpl:Flow.Subscription
	{
		@Volatile
		var isCanceled=false
			private set
		
		override fun request(n:Long)
		{ // Nothing to do here
		}
		
		override fun cancel()
		{
			isCanceled=true
		}
	}
}