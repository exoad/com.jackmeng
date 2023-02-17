package com.jackmeng.stl;

import java.util.HashMap;
import java.util.Map;

public class stl_Ticker
    extends Thread
{
  public enum ticker_Stats
  {
    TPS,
    TPS_ACTIVATION_ERROR,

  }

  private Map<String, Double> stats;
  private stl_ListenerPool<Map.Entry<String, Double[]>> listeners;

  public stl_Ticker()
  {
    stats = new HashMap<>();
    listeners = new stl_ListenerPool<>("")
  }

  @Override public void run()
  {

  }
}
