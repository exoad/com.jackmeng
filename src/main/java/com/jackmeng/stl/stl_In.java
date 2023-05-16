// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class stl_In
{
  private final InputStream io;
  private BufferedReader br;
  private StringTokenizer st;

  public stl_In(InputStream e)
  {
    this.io = e == null ? System.in : e;
    br = new BufferedReader(new InputStreamReader(this.io));
  }

  public synchronized void reader(BufferedReader br)
  {
    this.br = br;
  }

  public BufferedReader reader()
  {
    return this.br;
  }

  public String next()
  {
    return next(x -> {
      x.printStackTrace();
      return (Void) null;
    });
  }

  /**
   * Reads a single token from the input.
   * @param errorCallback
   * @return
   */
  public String next(stl_ErrCall errorCallback)
  {
    try
    {
      while (st == null || !st.hasMoreTokens())
        st = new StringTokenizer(br.readLine());
    } catch (IOException e)
    {
      errorCallback.call(e);
    }
    return st.toString();
  }

  /**
   * Reads an entire line
   * @param errorCallback Provided custom error callback handler
   * @return The String content that was read back
   */
  public String nextln(stl_ErrCall errorCallback)
  {
    String x = "";
    try
    {
      x = br.readLine().trim();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    return x;
  }

  public String nextln()
  {
    return nextln(x -> {
      x.printStackTrace();
      return (Void) null;
    });
  }

}