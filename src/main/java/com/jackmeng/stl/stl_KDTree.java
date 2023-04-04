// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.util.Comparator;
import java.util.List;

public class stl_KDTree
{

  public static class KDNode
  {
    double[] point;
    KDNode left;
    KDNode right;

    public KDNode(double[] point)
    {
      this.point = point;
    }
  }

  private final KDNode root;

  public stl_KDTree(List< double[] > points)
  {
    root = buildTree(points, 0);
  }

  private KDNode buildTree(List< double[] > points, int depth)
  {
    if (points.isEmpty())
      return null;

    int k = points.get(0).length;
    int axis = depth % k;

    if (points.size() == 1)
      return new KDNode(points.get(0));

    points.sort(Comparator.comparingDouble(a -> a[axis]));

    int medianIndex = points.size() / 2;
    double[] medianPoint = points.get(medianIndex);
    KDNode node = new KDNode(medianPoint);

    List< double[] > leftPoints = points.subList(0, medianIndex);
    List< double[] > rightPoints = points.subList(medianIndex + 1, points.size());

    node.left = buildTree(leftPoints, depth + 1);
    node.right = buildTree(rightPoints, depth + 1);

    return node;
  }

  public KDNode nearestNeighbor(double[] queryPoint)
  {
    return nearestNeighbor(root, queryPoint, root, 0);
  }

  private KDNode nearestNeighbor(KDNode currentNode, double[] queryPoint, KDNode bestNode, int depth)
  {
    if (currentNode == null)
      return bestNode;

    double bestDistance = distance(queryPoint, bestNode.point);
    double currentDistance = distance(queryPoint, currentNode.point);

    if (currentDistance < bestDistance)
      bestNode = currentNode;

    int k = queryPoint.length;
    int axis = depth % k;

    KDNode goodSide;
    KDNode badSide;

    if (queryPoint[axis] < currentNode.point[axis])
    {
      goodSide = currentNode.left;
      badSide = currentNode.right;
    }
    else
    {
      goodSide = currentNode.right;
      badSide = currentNode.left;
    }

    bestNode = nearestNeighbor(goodSide, queryPoint, bestNode, depth + 1);

    if (distance(queryPoint, bestNode.point) > Math.abs(queryPoint[axis] - currentNode.point[axis]))
      bestNode = nearestNeighbor(badSide, queryPoint, bestNode, depth + 1);

    return bestNode;
  }

  private double distance(double[] a, double[] b)
  {
    double sum = 0;
    for (int i = 0; i < a.length; i++)
      sum += (a[i] - b[i]) * (a[i] - b[i]);
    return Math.sqrt(sum);
  }
}
