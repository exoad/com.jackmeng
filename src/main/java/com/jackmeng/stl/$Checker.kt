// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
package com.jackmeng.stl

internal interface `$Checker`<T>
{
	fun is_valid(type:T):Boolean
	fun is_all_valid(types:Array<T>):Boolean
	{
		var e=false
		for (x in types) e=is_valid(x)
		return e
	}
}