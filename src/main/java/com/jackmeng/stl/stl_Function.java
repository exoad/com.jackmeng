package com.jackmeng.stl;

public interface stl_Function< T, R >
{
    R apply(T t);

    default < V > stl_Function< V, R > compose(stl_Function< ? super V, ? extends T > before)
    {
        return (V v) -> apply(before.apply(v));
    }

    default < V > stl_Function< T, V > andThen(stl_Function< ? super R, ? extends V > after)
    {
        return (T t) -> after.apply(apply(t));
    }

    static < T > stl_Function< T, T > identity()
    {
        return t -> t;
    }
}
