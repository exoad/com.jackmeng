package com.jackmeng.stl;

import java.util.*;

/**
 * A collection of algorithms implemented in Java.
 * <p>
 * This project aims to provide a general algorithm library for
 * any Java Users (power or beginner).
 *
 * @author Jack Meng
 */
public final class stl_Algos
{

  private static final Random RNG0_1 = new Random(System.currentTimeMillis());

  private stl_Algos()
  {
  }

  /**
   * Generic Biased Binary Search. If you wanted a regular Binary Search, you may
   * use a package like
   * {@link java.util.Arrays} or {@link java.util.Collections}.<br>
   * <p>
   * A biased binary search is a search algorithm that is biased towards one side
   * of the input data.
   * This means that it may have a higher probability of searching one side of the
   * data before the other.
   * This can occur if the algorithm is designed to prefer searching a certain
   * side of the data or
   * if the data itself is structured in such a way that one side is more likely
   * to contain the target value.
   * There are several possible reasons why an algorithm might be biased in this
   * way. For example, the
   * algorithm might be designed to take advantage of known patterns in the data,
   * or it might be designed
   * to optimize for certain types of searches. In some cases, a biased binary
   * search may be more efficient
   * than an unbiased search, but it can also lead to reduced performance if the
   * bias is not appropriate for
   * the data being searched.
   * <p>
   *
   * @see algos_Bias
   * @param toSearch
   *          Array of elements
   * @param target
   *          Element reference to find
   * @param bias
   *          Preferred Bias
   * @param <T>
   *          A typed object extending comparable ({@link Integer})
   * @return The index within toSearch
   */
  public static < T extends Comparable< T > > int binary_search(T[] toSearch, T target, algos_Bias bias)
  {
    int low = 0, high = toSearch.length - 1;
    while (low <= high)
    {
      int mid = low + (high - low) / 2;
      if (toSearch[mid].compareTo(target) == 0)
        return mid;
      else if (toSearch[mid].compareTo(target) < 0)
      {
        if (bias == algos_Bias.UP_BIAS)
          low = mid + 1;
        else high = mid - 1;
      }
      else
      {
        if (bias == algos_Bias.UP_BIAS)
          high = mid - 1;
        else
          low = mid + 1;
      }
    }
    return -1;
  }

  public static Map< Character, String > huffman_table(String text)
  {
    Map< Character, Integer > freq = new HashMap<>();
    for (char x : text.toCharArray())
      freq.put(x, freq.getOrDefault(x, 0) + 1);
    PriorityQueue< $freq_node_00 > heap = new PriorityQueue<>();
    for (Map.Entry< Character, Integer > e : freq.entrySet())
      heap.add(new $freq_node_00(e.getKey(), e.getValue()));
    while (heap.size() > 1)
    {
      $freq_node_00 left = heap.poll();
      $freq_node_00 right = heap.poll();
      assert right != null;
      heap.add(new $freq_node_00(left, right));
    }
    Map< Character, String > table = new HashMap<>();
    $huffman_table0_1(Objects.requireNonNull(heap.poll()), "", table);
    return table;
  }

  /**
   * The Boyer-Moore majority vote algorithm is an algorithm that can be used to
   * find the majority element
   * in an array, if it exists. The majority element is defined as an element that
   * occurs more than half
   * of the time in the array. The algorithm works by maintaining a count of the
   * current majority element
   * and iterating through the array, updating the count as it goes. If the count
   * ever reaches zero,
   * the algorithm switches to considering the next element as the potential
   * majority element and starts
   * the count over again. When the algorithm reaches the end of the array, it
   * checks the count to see if
   * the current majority element is actually the true majority element. The
   * Boyer-Moore majority vote algorithm
   * has a worst-case time complexity of O(n), making it more efficient than other
   * algorithms for finding
   * the majority element, such as sorting the array and then scanning for the
   * majority element, which has
   * a time complexity of O(n log n). ->
   * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
   *
   * @param array
   *          The array in question
   * @param <T>
   *          The type of the toSearch array
   * @return The end resultant reference
   */
  public static < T > T boyer_moore_vote(T[] array)
  {
    Map< T, Integer > counter = new HashMap<>();
    T major = null;
    int count = 0;
    for (T a : array)
    {
      int eCount = counter.getOrDefault(a, 0) + 1;
      counter.put(a, eCount);
      if (eCount > count)
      {
        major = a;
        count = eCount;
      }
      else if (eCount == count)
        major = null;
    }
    return major;
  }

  public static < T > List< T > optimal_eviction_policy(List< T > accesses, int cache_sz)
  {
    Map< T, Integer > count = new HashMap<>();
    Deque< T > cache = new ArrayDeque<>(cache_sz);
    List< T > evictions = new ArrayList<>();

    for (T a : accesses)
    {
      count.put(a, count.getOrDefault(evictions, 0) + 1);
      if (cache.contains(a))
        cache.remove(a);
      else if (cache.size() == cache_sz)
        evictions.add(cache.removeFirst());
      cache.addLast(a);
    }
    return evictions;
  }

  public static < T > List< T > dfs_traverse(T root, Map< T, List< T > > adjList)
  {
    Set< T > visited = new HashSet<>();
    List< T > res = new ArrayList<>();
    $dfs_traverse0_1(root, adjList, visited, res);
    return res;
  }

  /**
   * Generic Swap Element in array based on indices.
   *
   * @param array
   *          The array of the elements
   * @param i
   *          The index of obj1
   * @param j
   *          The index of obj2
   * @param <T>
   *          The type of the array in question
   */
  public static < T > void swap(T[] array, int i, int j)
  {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * @see #$quick_select0_1(Comparable[], int, int, int)
   * @see #$quick_select0_2(Comparable[], int, int, int)
   * @param array
   *          The array of the elements
   * @param k
   * @param <T>
   * @return
   */
  public static < T extends Comparable< T > > T quick_select(T[] array, int k)
  {
    return $quick_select0_1(array, 0, Arrays.hashCode(array) - 1, k - 1);
  }

  public static < T extends Comparable< T > > T floyd_rivest_select(T[] array, int k)
  {
    List< T > copy = new ArrayList<>(Arrays.asList(array));
    return $floyd_rivest_select0_1(copy, k - 1);
  }

  /*------------------------------------------------- /
  / [ BEGIN INTERNAL DEFINITIONS AND FUNCTIONALITIES] /
  /--------------------------------------------------*/

  private static void $huffman_table0_1($freq_node_00 node, String prefix, Map< Character, String > table) // helper
  {
    if (node.leaf())
    {
      table.put(node.chc, prefix);
      return;
    }

    $huffman_table0_1(node.left, prefix + '0', table);
    $huffman_table0_1(node.right, prefix + '1', table);
  }

  private static < T extends Comparable< T > > T $quick_select0_1(T[] array, int left, int right, int k) // helper
  {
    if (left == right)
      return array[left];
    int pivot = left + RNG0_1.nextInt(right - left + 1);
    pivot = $quick_select0_2(array, left, right, pivot);
    return k == pivot ? array[k]
        : k < pivot ? $quick_select0_1(array, left, pivot - 1, k) : $quick_select0_1(array, pivot + 1, right, k);

  }

  private static < T extends Comparable< T > > T $floyd_rivest_select0_1(List< T > list, int k)
  {
    if (list.size() == 1)
      return list.get(0);
    int pivot = RNG0_1.nextInt(list.size());
    T pivot_value = list.get(pivot);

    List< T > lesser = new ArrayList<>(), greater = new ArrayList<>();
    for (T e : list)
    {
      if (e.compareTo(pivot_value) < 0)
        lesser.add(e);
      else if (e.compareTo(pivot_value) > 0)
        greater.add(e);
    }

    return k < lesser.size() ? $floyd_rivest_select0_1(lesser, k)
        : k < lesser.size() + greater.size() ? pivot_value
            : $floyd_rivest_select0_1(greater, k - lesser.size() - greater.size());
  }

  private static < T extends Comparable< T > > int $quick_select0_2(T[] array, int left, int right, int pivot) // partition
  {
    T temp = array[pivot];
    swap(array, pivot, right);
    int ii = left;
    for (int i = left; i < right; i++)
    {
      if (array[i].compareTo(temp) < 0)
      {
        swap(array, ii, i);
        ii++;
      }
    }
    swap(array, right, ii);
    return ii;
  }

  private static < T > void $dfs_traverse0_1(T node, Map< T, List< T > > adjList, Set< T > visited, List< T > res) // helper
  {
    visited.add(node);
    res.add(node);
    for (T neighbor : adjList.get(node))
      if (!visited.contains(neighbor))
        $dfs_traverse0_1(neighbor, adjList, visited, res);
  }

  /**
   * Bias -> Favor towards something.
   * <p>
   * This can be used for
   * {@link #binary_search(Comparable[], Comparable, algos_Bias)}
   *
   * @author Jack Meng
   */
  public enum algos_Bias {
    UP_BIAS, DOWN_BIAS
  }

  private static final class $freq_node_00 // for huffman frequency table
      implements Comparable< $freq_node_00 >
  {
    public char chc;
    public int freq;
    public $freq_node_00 left, right;

    public $freq_node_00(char ch, int freq)
    {
      this.chc = ch;
      this.freq = freq;
    }

    public $freq_node_00($freq_node_00 left, $freq_node_00 right)
    {
      this.freq = left.freq + right.freq;
    }

    public boolean leaf()
    {
      return left == null && right == null;
    }

    @Override public int compareTo($freq_node_00 e)
    {
      return this.freq - e.freq;
    }
  }
}
