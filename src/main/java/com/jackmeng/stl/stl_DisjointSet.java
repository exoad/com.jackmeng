package com.jackmeng.stl;

public class stl_DisjointSet
{
    private int[] parent, rank;

    public stl_DisjointSet(int size)
    {
        parent = new int[size];
        rank = new int[size];

        for(int i = 0; i < size; i++)
        {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x)
    {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y)
    {
        int root_x = find(x);
        int root_y = find(y);
        if(root_x == root_y) return;

        if(rank[root_x] < rank[root_y]) parent[root_x] = root_y;
        else if (rank[root_x] > rank[root_y]) parent[root_y] = root_x;
        else
        {
            parent[root_y] = root_x;
            rank[root_x]++;
        }
    }
}
