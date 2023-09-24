// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * A marker annotation that can be hooked up to a compiler
 * reader to make sure that the correction function is called for the correct
 * version.
 *
 * @author Jack Meng
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(
	AnnotationTarget.ANNOTATION_CLASS ,
	AnnotationTarget.CLASS ,
	AnnotationTarget.FILE ,
	AnnotationTarget.ANNOTATION_CLASS ,
	AnnotationTarget.FUNCTION ,
	AnnotationTarget.PROPERTY_GETTER ,
	AnnotationTarget.PROPERTY_SETTER ,
	ElementType.MODULE
)
annotation class stl_Targets(
		/**
		 * Should be represented in the standard formats:
		 * "J_XXXXX(-/+)"
		 *
		 * For example:
		 * "J_11+"
		 *
		 * would target only 11 or above (>=).
		 *
		 * If the +/- are not used, only that specific version can be used.
		 *
		 * ** IT IS HIGHLY RECOMMENDED TO STICK TO THIS PATTERN TO MAINTAIN
		 * MAINTAINABILITY**
		 *
		 * @return String[]
		 */
		vararg val value:String
)