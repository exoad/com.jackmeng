package com.jackmeng.stl;

public class stl_HashFunction
{
    private final int size;
    private final int seed;

    public stl_HashFunction(int size)
    {
        this.size = size;
        this.seed = (int) (Math.random() * Integer.MAX_VALUE);
    }

    public int hash(String value)
    {
        int hash = seed;
        for(int i = 0; i < value.length(); i++)
            hash = (hash * 31 + value.charAt(i)) % size;
        return hash;
    }
}
