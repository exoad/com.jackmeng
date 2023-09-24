package com.jackmeng.stl

import java.awt.*
import java.util.*

/**
 * Basic Direction Interface with certain attributes of the user's system hardwares.
 *
 * The primary target is the Screen
 *
 * @author Jack Meng
 */
object stl_Ware
{
	var `$00_rng`=Random()
	fun desktop():Optional<Desktop>
	{
		return if (Desktop.isDesktopSupported()) Optional.of(Desktop.getDesktop()) else Optional.empty()
	}
	
	fun screen_cursorTo(x:Int , y:Int)
	{
		var r:Robot?=null
		try
		{
			r=Robot()
		} catch (e:AWTException)
		{
			e.printStackTrace()
		}
		r?.mouseMove(x , y)
	}
	
	@JvmStatic
    fun screen_Size():Dimension
	{
		return Toolkit.getDefaultToolkit().screenSize
	}
	
	fun screen_cursorToRnd()
	{
		desktop().ifPresent { x:Desktop?->
			screen_cursorTo(
				`$00_rng`.nextInt(screen_Size().width) , `$00_rng`.nextInt(
					screen_Size().height
				)
			)
		}
	}
	
	/**
	 * Guranteed to be 99% of the time returning a result with value unless the engine
	 * is in Headless mode
	 * @param x Screen X
	 * @param y Screen Y
	 * @return
	 */
	fun screen_colorAt(x:Int , y:Int):Optional<Color>
	{
		var r:Robot?=null
		try
		{
			r=Robot()
		} catch (e:AWTException)
		{
			e.printStackTrace()
		}
		var color:Optional<Color> =Optional.empty()
		if (r!=null) color =Optional.of(r.getPixelColor(x , y))
		return color
	}
	
	fun screen_colorAt_Rnd():Optional<Color>
	{
		return screen_colorAt(`$00_rng`.nextInt(screen_Size().width) , `$00_rng`.nextInt(screen_Size().height))
	}
}