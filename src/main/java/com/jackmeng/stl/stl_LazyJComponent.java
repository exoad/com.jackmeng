// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

import javax.swing.*;

public class stl_LazyJComponent
    extends JComponent
    implements
    Flow.Subscriber< Runnable >
{
  private final transient SubmissionPublisher< Runnable > publisher = new SubmissionPublisher<>();
  private final transient Flow.Subscription subscription = new SubscriptionImpl();
  private final transient ExecutorService executorService = Executors.newSingleThreadExecutor();

  public stl_LazyJComponent()
  {
    publisher.subscribe(this);
  }

  @Override public void onSubscribe(Flow.Subscription subscription)
  {
    subscription.request(1);
  }

  @Override public void onNext(Runnable task)
  {
    try
    {
      SwingUtilities.invokeAndWait(task);
    } catch (InterruptedException | InvocationTargetException e)
    {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    repaint();
    subscription.request(1);
  }

  @Override public void onError(Throwable throwable)
  {
    throwable.printStackTrace();
  }

  @Override public void onComplete()
  {
    // Nothing to do here
  }

  public void queueTask(Runnable task)
  {
    publisher.submit(task);
  }

  public void stop()
  {
    executorService.shutdown();
  }

  @Override public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
  }

  @Override public Dimension getPreferredSize()
  {
    return new Dimension(400, 400);
  }

  private static class SubscriptionImpl implements Flow.Subscription
  {
    private volatile boolean isCanceled = false;

    @Override public void request(long n)
    {
      // Nothing to do here
    }

    @Override public void cancel()
    {
      isCanceled = true;
    }

    public boolean isCanceled()
    {
      return isCanceled;
    }
  }
}