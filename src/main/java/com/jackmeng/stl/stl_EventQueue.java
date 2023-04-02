// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.LinkedList;

public class stl_EventQueue
    implements
    Runnable
{

  private final LinkedList< Runnable > eventQueue;
  private boolean isProcessingEvents;

  public stl_EventQueue()
  {
    eventQueue = new LinkedList<>();
    isProcessingEvents = false;
  }

  public synchronized void add(Runnable event)
  {
    eventQueue.add(event);
    if (!isProcessingEvents)
      run();
  }

  @Override public synchronized void run()
  {
    isProcessingEvents = true;
    while (!eventQueue.isEmpty())
    {
      Runnable event = eventQueue.remove();
      event.run();
    }
    isProcessingEvents = false;
  }
}
