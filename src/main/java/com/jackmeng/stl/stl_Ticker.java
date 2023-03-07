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
    TPS_LATENCY,

  }

  private final Map<ticker_Stats, Double> stats;
  private final stl_ListenerPool<stl_Struct.struct_Pair<String, Double>> listeners;

  public stl_Ticker()
  {
    stats = new HashMap<>();
    listeners = new stl_ListenerPool<>("stl_ticker_obj_" + hashCode());
  }

  @Override public void run()
  {

  }
}
