package com.jackmeng.stl;

@FunctionalInterface public interface stl_Callback< T, E >
{
    T call(E arg);
}
