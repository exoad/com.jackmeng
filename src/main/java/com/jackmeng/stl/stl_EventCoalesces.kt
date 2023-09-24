// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.Runnable
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import javax.swing.SwingUtilities
import javax.swing.Timer

class stl_EventCoalesces(delay:Int , lambda:Runnable):Runnable
{
	private val timer:Timer
	
	init
	{
		timer=Timer(delay) { e:ActionEvent?->
			timer.stop()
			lambda.run()
		}
	}
	
	override fun run()
	{
		if (!SwingUtilities.isEventDispatchThread()) SwingUtilities.invokeLater { timer.restart() } else timer.restart()
	}
}