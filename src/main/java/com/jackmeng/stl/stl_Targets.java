// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker annotation that can be hooked up to a compiler
 * reader to make sure that the correction function is called for the correct
 * version.
 *
 * @author Jack Meng
 */
@Documented @Retention(RetentionPolicy.RUNTIME) @Target({ ElementType.TYPE, ElementType.PACKAGE,
    ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.MODULE }) public @interface stl_Targets {

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
   * <strong> IT IS HIGHLY RECOMMENDED TO STICK TO THIS PATTERN TO MAINTAIN
   * MAINTAINABILITY
   *
   * @return String[]
   */
  String[] value();
}
