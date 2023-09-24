// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
package com.jackmeng.stl

import java.awt.Component

abstract class stl_IComponent<T:Component?>
{
	abstract fun make():T
}