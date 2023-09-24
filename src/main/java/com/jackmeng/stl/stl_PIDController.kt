// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

class stl_PIDController(private val P:Double , private val I:Double , private val D:Double)
{
	private var setpoint=0.0
	private var errorSum=0.0
	private var lastError=0.0
	private var maxErrorSum:Double
	private var maxOutput:Double
	private var minOutput:Double
	private var lastTime:Double
	private var dt:Double
	private var deadband:Double
	private var firstRun:Boolean
	var isOnTarget:Boolean
		private set
	private var onTargetError:Double
	private var onTargetCount:Int
	private var onTargetCountMax:Int
	
	init
	{
		maxErrorSum=Double.MAX_VALUE
		maxOutput=Double.MAX_VALUE
		minOutput=-Double.MAX_VALUE
		lastTime=System.nanoTime().toDouble()
		dt=0.01
		deadband=0.0
		firstRun=true
		isOnTarget=false
		onTargetError=0.1
		onTargetCount=0
		onTargetCountMax=10
	}
	
	fun setSetpoint(setpoint:Double)
	{
		this.setpoint=setpoint
	}
	
	fun setMaxErrorSum(maxErrorSum:Double)
	{
		this.maxErrorSum=maxErrorSum
	}
	
	fun setMaxOutput(maxOutput:Double)
	{
		this.maxOutput=maxOutput
	}
	
	fun setMinOutput(minOutput:Double)
	{
		this.minOutput=minOutput
	}
	
	fun setDt(dt:Double)
	{
		this.dt=dt
	}
	
	fun setDeadband(deadband:Double)
	{
		this.deadband=deadband
	}
	
	fun setOnTargetError(onTargetError:Double)
	{
		this.onTargetError=onTargetError
	}
	
	fun setOnTargetCountMax(onTargetCountMax:Int)
	{
		this.onTargetCountMax=onTargetCountMax
	}
	
	fun calculate(processVariable:Double):Double
	{
		val time=System.nanoTime().toDouble()
		if (firstRun)
		{
			dt=(time-lastTime)/1e9
			firstRun=false
		}
		var error=setpoint-processVariable
		if (Math.abs(error)<deadband) error=0.0
		errorSum+=error*dt
		errorSum=Math.min(errorSum , maxErrorSum)
		val dError=(error-lastError)/dt
		var output=P*error+I*errorSum+D*dError
		output=Math.max(Math.min(output , maxOutput) , minOutput)
		lastError=error
		lastTime=time
		checkOnTarget(error)
		return output
	}
	
	private fun checkOnTarget(error:Double)
	{
		if (Math.abs(error)<onTargetError)
		{
			onTargetCount++
			if (onTargetCount>=onTargetCountMax) isOnTarget=true
		}
		else
		{
			isOnTarget=false
			onTargetCount=0
		}
	}
	
	fun reset()
	{
		errorSum=0.0
		lastError=0.0
		isOnTarget=false
		onTargetCount=0
	}
}