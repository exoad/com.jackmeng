// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public interface stl_Monad< T >
{
    < R > stl_Monad< R > bind(stl_Function< ? super T, stl_Monad< R > > f);

    static < T > stl_Monad< T > unit(T value)
    {
        return new stl_Monad<>() {
            @Override public < R > stl_Monad< R > bind(stl_Function< ? super T, stl_Monad< R > > f)
            {
                return f.apply(value);
            }
        };
    }
}
