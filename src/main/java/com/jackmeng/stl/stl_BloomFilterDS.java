package com.jackmeng.stl;

import java.util.BitSet;

public class stl_BloomFilterDS
{

    private final BitSet bitset;
    private final stl_HashFunction[] hashFxs;

    public stl_BloomFilterDS(int size, int hashes)
    {
        this.bitset = new BitSet(size);
        this.hashFxs = new stl_HashFunction[hashes];
        for(int i = 0; i < hashes; i++)
            hashFxs[i] = new stl_HashFunction(size);
    }

    public void add(String value)
    {
        for (stl_HashFunction hashFunction : hashFxs)
        {
            int hash = hashFunction.hash(value);
            bitset.set(hash);
        }
    }

    public boolean contains(String value)
    {
        for (stl_HashFunction hashFunction : hashFxs)
        {
            int hash = hashFunction.hash(value);
            if (!bitset.get(hash))
                return false;
        }
        return true;
    }

}
