// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public interface stl_Loggable
    extends stl_Callback<Void, String> // void: default, String: caller string (aka save location)
{
    void add(String content); // String: content String
    void remove(String content); // String: content string
    void remove(int i); // int : index

    void set_dispatch_tempo(long s); // long: the time in ms
    long get_dispatch_tempo(); // ret_long: the time in ms
}
