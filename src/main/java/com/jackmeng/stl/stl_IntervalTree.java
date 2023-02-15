package com.jackmeng.stl;

import java.util.ArrayList;
import java.util.List;

public class stl_IntervalTree
{
    public static final class Interval_Node
    {
        stl_Int_Interval interval;
        int max;
        Interval_Node left, right;

        public Interval_Node(stl_Int_Interval interval)
        {
            this.interval = interval;
            this.max = interval.high;
        }
    }

    private Interval_Node root;

    public void insert(stl_Int_Interval interval)
    {
        root = insert(root, interval);
    }

    private Interval_Node insert(Interval_Node node, stl_Int_Interval interval)
    {
        if (node == null)
            return new Interval_Node(interval);
        int low = interval.low;
        if (low < node.interval.low)
            node.left = insert(node.left, interval);
        else
            node.right = insert(node.right, interval);
        if (node.max < interval.high)
            node.max = interval.high;
        return node;
    }

    public List<stl_Int_Interval> search(int point)
    {
        List<stl_Int_Interval> results = new ArrayList<>();
        search(root, point, results);
        return results;
    }

    private void search(Interval_Node node, int point, List<stl_Int_Interval> results)
    {
        if (node == null)
            return;
        if (point >= node.interval.low && point <= node.interval.high)
            results.add(node.interval);

        if (node.left != null && point <= node.left.max)
            search(node.left, point, results);

        if (node.right != null && point <= node.right.max)
            search(node.right, point, results);
    }

}
