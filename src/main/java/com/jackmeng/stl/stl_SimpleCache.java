// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class stl_SimpleCache< K, V >
{
  private final Map< K, CacheEntry< V > > cache;
  private final long timeoutMillis;
  private final long toleranceMillis;
  private final Timer cleanupTimer;

  public stl_SimpleCache(long timeoutMillis, long toleranceMillis)
  {
    this.cache = new HashMap<>();
    this.timeoutMillis = timeoutMillis;
    this.toleranceMillis = toleranceMillis;
    this.cleanupTimer = new Timer(true);
    this.cleanupTimer.schedule(new CleanupTask(), timeoutMillis, timeoutMillis);
  }

  public void put(K key, V value)
  {
    synchronized (cache)
    {
      cache.put(key, new CacheEntry<>(value));
    }
  }

  public V get(K key)
  {
    synchronized (cache)
    {
      CacheEntry< V > entry = cache.get(key);
      if (entry != null)
      {
        entry.lastAccessTimeMillis = System.currentTimeMillis();
        return entry.value;
      }
      else
        return null;
    }
  }

  private class CacheEntry< T >
  {
    final T value;
    long lastAccessTimeMillis;

    CacheEntry(T value)
    {
      this.value = value;
      this.lastAccessTimeMillis = System.currentTimeMillis();
    }
  }

  private class CleanupTask extends TimerTask
  {
    @Override public void run()
    {
      long nowMillis = System.currentTimeMillis();
      synchronized (cache)
      {
        cache.entrySet().removeIf(entry -> {
          long lastAccessTimeMillis = entry.getValue().lastAccessTimeMillis;
          long ageMillis = nowMillis - lastAccessTimeMillis;
          return ageMillis > (timeoutMillis + toleranceMillis);
        });
      }
    }
  }
}