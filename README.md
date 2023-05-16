<!--
 Copyright 2023 Jack Meng. All rights reserved.
 Use of this source code is governed by a BSD-style
 license that can be found in the LICENSE file.
-->

# com.jackmeng

The ultimate root of any of my Java Projects, containing all kinds of goodies and junk. This library packs a general list of utilities that might be useful for development
in Java. Furthermore, it also provides utility for extending the original functionality of Java itself (compiler and runtime). All of these utilities are avaliable for usage under the permissive license: `BSD-3.0 'Revised'`.

> **Notice** Documentation are only avaliable for certain things in the library and is not avaliable for everything!

> Copyright (C) Jack Meng 2022-2023

**Versioning**<br>
Base Version: 0.0.1a (InDev)<br>
Latest Release: None

# Documentation

You can view the documentation here: https://exoad.github.io/com.jackmeng/

# Using

The `com.jackmeng` library follows a manifest to utilize **_zero_** third-party libraries and only requires the JDK's internal STL and using existing classes from the current library. This helps to reduce dependency conflicts, irrelevant depths, and legal issues (BSD-3). However, certain functionalities that are used as bootstrap for other elements in the library can be finalized (AKA `public final class`) or be completely package ghosts (AKA `class`). These limitations are minimized but aims to avoid end-users getting confused and potentially messing with unsafe code.

You can use JitPack:

> **Step 1.** Add the JitPack repository to your build file
> Add it in your root build.gradle at the end of repositories:
>
> ```
> allprojects {
> 		repositories {
> 			...
> 			maven { url 'https://jitpack.io' }
> 		}
> 	}
> ```
>
> **Step 2.** Add the dependency
>
> ```
> dependencies {
> 	        implementation 'com.github.exoad:com.jackmeng:Tag'
> }
> ```

You can also use via just downloading the JAR file via releases! However, this method requires you to manually resolve dependency issues and marginals.

# Why?

This library isn't meant to replace the entirety of the JavaSDK and it's Standard Library, instead more of an extension to it. It adds functions to simplify the implementation of bootstrap code.
For third parties, I suggest you NOT use this library for any advanced computations and rely more on well known or even proprietary libraries that have much more thoughout functions.

In terms of my projects, this library helps to form the basis of all. Changing one specific parameter changes the functionality in all of my subsequent projects, allowing for zero hassle (where I have to go to each project and update the existing source code).

Read [LICENSE](./LICENSE)

# Building

Run the platform specific Gradle build code for your system.
