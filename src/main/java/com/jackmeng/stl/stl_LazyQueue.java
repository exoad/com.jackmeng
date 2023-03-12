// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.LinkedList;
import java.util.Queue;

public class stl_LazyQueue< T >
{
  private Queue< T > queue = new LinkedList<>();
  private Queue< T > lazyQueue = new LinkedList<>();

  public void enqueue(T item)
  {
    lazyQueue.add(item);
  }

  public T dequeue()
  {
    if (queue.isEmpty())
      drainLazyQueue();
    return queue.poll();
  }

  public T peek()
  {
    if (queue.isEmpty())
      drainLazyQueue();
    return queue.peek();
  }

  public boolean isEmpty()
  {
    return queue.isEmpty() && lazyQueue.isEmpty();
  }

  public int size()
  {
    return queue.size() + lazyQueue.size();
  }

  private void drainLazyQueue()
  {
    while (!lazyQueue.isEmpty())
      queue.add(lazyQueue.poll());
  }
}
