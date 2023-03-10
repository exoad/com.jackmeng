// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.*;

public class stl_ListenerPool<T>
{
    private String name;
    private final List<stl_Listener<T>> listeners;

    public stl_ListenerPool(String poolName)
    {
        this.name = poolName;
        listeners = new Vector<>(5);
    }

    public String name()
    {
        return name;
    }

    public void name(String name)
    {
        this.name = name;
    }

    public synchronized void add(stl_Listener<T> listener)
    {
        listeners.add(listener);
    }

    public synchronized void rmf(stl_Listener<T> listener)
    {
        listeners.remove(listener);
    }

    public synchronized void kill()
    {
        listeners.clear();
    }

    /**
     * It is highly suggested that you make sure that the payload is not null!
     * @param payload The action to dispatch to all the listeners
     */
    public void dispatch(T payload)
    {
        listeners.forEach(x -> x.call(payload));
    }

    @Override public String toString()
    {
        return name + "\n" + listeners;
    }
}
