// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
package com.jackmeng.stl

interface stl_Populable<T>
{
	fun add_1(e:T)
	fun add_2(e:T):T
	fun add_3(e:T , i:Int)
	fun add_4(e:T , i:Int):T
	fun remove_1(e:T)
	fun remove_2(e:T):Int
	fun remove_3(i:Int)
	fun remove_4(i:Int):Int
	fun len():Int
	{
		return 0
	}
	
	fun remove_all()
	{
		var x=len()
		while (x-->0) remove_3(x)
	}
}