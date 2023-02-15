package com.jackmeng.stl;

import java.lang.ref.SoftReference;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;

public class stl_ExecutionList
    implements Runnable
{
    private volatile SoftReference<Vector<stl_Struct.struct_Pair<CharSequence, Runnable>>> tasks;

    public stl_ExecutionList()
    {
        tasks = new SoftReference<>(new Stack<>());
    }

    public synchronized void add_task(CharSequence name, Runnable task)
    {
            Objects.requireNonNull(tasks.get()).add(stl_Struct.struct_Pair.make(name, task));
    }

    @Override public void run()
    {
        if(tasks.get() != null)
        {
            Objects.requireNonNull(tasks.get()).forEach(x ->
            {

            });
        }
    }
}
