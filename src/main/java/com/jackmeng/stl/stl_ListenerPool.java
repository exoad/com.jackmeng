// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.*;

public class stl_ListenerPool<T>
{
    public static class ListenerPool_Attached<T> extends stl_ListenerPool<T>
    {
        public enum Attached_States
        {
            ATTACHED, ADD_LISTENER, DETACHED, RMF_LISTENER;
        }

        private final List<stl_Listener<stl_Struct.struct_Pair<stl_Listener<T>, Attached_States>>> attachedListeners;
        public ListenerPool_Attached(String poolName) {
            super(poolName);
            attachedListeners = new Vector<>(2);
        }

        public synchronized void attach(stl_Listener<stl_Struct.struct_Pair<stl_Listener<T>, Attached_States>> listener)
        {
            attachedListeners.add(listener);
            listener.call(stl_Struct.make_pair(null, Attached_States.ATTACHED));
        }

        public synchronized void detach(stl_Listener<stl_Struct.struct_Pair<stl_Listener<T>, Attached_States>> listener)
        {
            if(attachedListeners.contains(listener))
            {
                attachedListeners.remove(listener);
                listener.call(stl_Struct.make_pair(null, Attached_States.DETACHED));
            }
        }

        @Override
        public synchronized void add(stl_Listener<T> listener)
        {
            super.add(listener);
            dispatch(stl_Struct.make_pair(listener, Attached_States.ADD_LISTENER));
        }

        @Override
        public synchronized void rmf(stl_Listener<T> listener)
        {
            super.rmf(listener);
            dispatch(stl_Struct.make_pair(listener, Attached_States.RMF_LISTENER));
        }

        private void dispatch(stl_Struct.struct_Pair<stl_Listener<T>, Attached_States> payload)
        {
            attachedListeners.forEach(x -> x.call(payload));
        }

        @Override
        public synchronized void kill()
        {
            super.kill();
            attachedListeners.clear();
        }
    }

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
