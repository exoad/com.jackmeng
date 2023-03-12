// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public interface stl_Curve
{
    stl_Pt[] points(int segments);
    void controls(stl_Pt[] pts);
    stl_Pt[] controls();
    void tension(float tension);
    float tension();
    void continuity(float continuity);
    float continuity();
    void bias(float bias);
    float bias();
}
