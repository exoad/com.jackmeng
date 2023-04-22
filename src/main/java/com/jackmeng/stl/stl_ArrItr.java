// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple transformer class for turning a regular array into an Iteratable
 * type via Iterator.
 *
 * @author Jack Meng
 */
public class stl_ArrItr< T >
        implements
        Iterator< T >
{
    private final T[] arr;
    private int i = 0;

    /**
     * @param arr
     *            The target primitive array.
     */
    public stl_ArrItr(T[] arr)
    {
        this.arr = arr;
    }

    @Override public boolean hasNext()
    {
        return i < arr.length;
    }

    @Override public T next()
    {
        if (hasNext())
            return arr[i++];
        else throw new NoSuchElementException();
    }
}
