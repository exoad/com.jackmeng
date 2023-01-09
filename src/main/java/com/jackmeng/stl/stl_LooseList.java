package com.jackmeng.stl;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class stl_LooseList< T >
        implements
        Iterable< T >
{
    private static class looselist_Iterator< T >
            implements Iterator< T >
    {
        private final List< SoftReference< T > > queue;
        private int i;

        public looselist_Iterator(List< SoftReference< T > > l)
        {
            this.queue = l;
            this.i = 0;
        }

        @Override public boolean hasNext()
        {
            return i < queue.size();
        }

        @Override public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            T e = queue.get(i).get();
            i++;
            return e;
        }

        @Override public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    private final List< SoftReference< T > > list;

    public stl_LooseList()
    {
        this.list = new ArrayList<>();
    }

    public void add(T element)
    {
        list.add(new SoftReference<>(element));
    }

    public T get(int index)
    {
        return list.get(index).get();
    }

    public void remove(int index)
    {
        list.remove(index);
    }

    public int size()
    {
        return list.size();
    }

    @Override public Iterator< T > iterator()
    {
        return new looselist_Iterator<>(list);
    }
}