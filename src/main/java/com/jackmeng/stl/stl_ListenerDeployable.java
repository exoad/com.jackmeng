package com.jackmeng.stl;

public interface stl_ListenerDeployable<A>
    extends stl_Callback<Void, Iterable<A>>
{
  default Void call(Iterable<A> e)
  {
    return (Void) null;
  }

  default void empty(int length)
  {

  }
}
