// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Callback
import java.lang.Exception
import java.lang.Void

/**
 * A macro interface to represent an error accepting callback, which should be
 * used for function inputs for when
 * a certain operation goes wrong.
 *
 * It is canonically the same as initializing as
 * `stl_Callback<Void, Exception> variable`
 *
 * @author Jack Meng
 */
interface stl_ErrCall:stl_Callback<Void? , Exception?>