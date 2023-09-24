// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import com.jackmeng.stl.stl_Algos.algos_Bias
import com.jackmeng.stl.stl_Algos.``$freq_node_00`
import kotlin.collections.HashSet
import java.util.*
import java.util.ArrayDeque

/**
 * A collection of algorithms implemented in Java.
 *
 *
 * This project aims to provide a general algorithm library for
 * any Java Users (power or beginner).
 *
 * @author Jack Meng
 */
object stl_Algos
{
	private val RNG0_1=Random(System.currentTimeMillis())
	fun normalize(data:DoubleArray):DoubleArray
	{
		val result=DoubleArray(data.size)
		var sum=0.0
		for (value in data) sum+=value
		val mean=sum/data.size
		var variance=0.0
		for (value in data) variance+=Math.pow(value-mean , 2.0)
		val standardDeviation=Math.sqrt(variance/data.size)
		for (i in data.indices) result[i]=(data[i]-mean)/standardDeviation
		return result
	}
	
	/**
	 * Generic Biased Binary Search. If you wanted a regular Binary Search, you may
	 * use a package like
	 * [java.util.Arrays] or [java.util.Collections].<br></br>
	 *
	 *
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
	 *
	 *
	 *
	 * @see algos_Bias
	 *
	 * @param toSearch
	 * Array of elements
	 * @param target
	 * Element reference to find
	 * @param bias
	 * Preferred Bias
	 * @param <T>
	 * A typed object extending comparable ([Integer])
	 * @return The index within toSearch
	</T> */
	fun <T:Comparable<T>?> binary_search(toSearch:Array<T> , target:T , bias:algos_Bias):Int
	{
		var low=0
		var high=toSearch.size-1
		while (low<=high)
		{
			val mid=low+(high-low)/2
			if (toSearch[mid]!!.compareTo(target)==0) return mid
			else if (toSearch[mid]!!.compareTo(target)<0)
			{
				if (bias==algos_Bias.UP_BIAS) low=mid+1 else high=mid-1
			}
			else
			{
				if (bias==algos_Bias.UP_BIAS) high=mid-1 else low=mid+1
			}
		}
		return -1
	}
	
	fun huffman_table(text:String):Map<Char , String>
	{
		val freq:MutableMap<Char , Int>=HashMap()
		for (x in text.toCharArray()) freq[x]=freq.getOrDefault(x , 0)+1
		val heap=PriorityQueue<`$freq_node_00`>()
		for ((key , value) in freq) heap.add(`$freq_node_00`(key , value))
		while (heap.size>1)
		{
			val left=heap.poll()
			val right=heap.poll()!!
			heap.add(`$freq_node_00`(left , right))
		}
		val table:MutableMap<Char , String>=HashMap()
		`$huffman_table0_1`(Objects.requireNonNull<`$freq_node_00`>(heap.poll()) , "" , table)
		return table
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
	 * The array in question
	 * @param <T>
	 * The type of the toSearch array
	 * @return The end resultant reference
	</T> */
	fun <T> boyer_moore_vote(array:Array<T>):T?
	{
		val counter:MutableMap<T , Int>=HashMap()
		var major:T?=null
		var count=0
		for (a in array)
		{
			val eCount=counter.getOrDefault(a , 0)+1
			counter[a]=eCount
			if (eCount>count)
			{
				major=a
				count=eCount
			}
			else if (eCount==count) major=null
		}
		return major
	}
	
	fun lagrange(x:DoubleArray , y:DoubleArray , x_input:Double):Double
	{
		val n=x.size
		var result=0.0
		for (i in 0 until n)
		{
			var term=y[i]
			for (j in 0 until n) if (i!=j) term*=(x_input-x[j])/(x[i]-x[j])
			result+=term
		}
		return result
	}
	
	fun <T> optimal_eviction_policy(accesses:List<T> , cache_sz:Int):List<T>
	{
		val count:MutableMap<T , Int>=HashMap()
		val cache:Deque<T>=ArrayDeque<T>(cache_sz)
		val evictions:MutableList<T>=ArrayList()
		for (a in accesses)
		{
			count[a]=count.getOrDefault(evictions , 0)+1
			if (cache.contains(a)) cache.remove(a) else if (cache.size==cache_sz) evictions.add(cache.removeFirst())
			cache.addLast(a)
		}
		return evictions
	}
	
	fun <T> dfs_traverse(root:T , adjList:Map<T , List<T>>):List<T>
	{
		val visited:MutableSet<T>=HashSet()
		val res:MutableList<T>=ArrayList()
		`$dfs_traverse0_1`(root , adjList , visited , res)
		return res
	}
	
	/**
	 * Generic Swap Element in array based on indices.
	 *
	 * @param array
	 * The array of the elements
	 * @param i
	 * The index of obj1
	 * @param j
	 * The index of obj2
	 * @param <T>
	 * The type of the array in question
	</T> */
	fun <T> swap(array:Array<T> , i:Int , j:Int)
	{
		val temp=array[i]
		array[i]=array[j]
		array[j]=temp
	}
	
	/**
	 * @see .$quick_select0_1
	 * @see .$quick_select0_2
	 * @param array
	 * The array of the elements
	 * @param k
	 * @param <T>
	 * @return
	</T> */
	fun <T:Comparable<T>?> quick_select(array:Array<T> , k:Int):T
	{
		return `$quick_select0_1`<T>(array , 0 , Arrays.hashCode(array)-1 , k-1)
	}
	
	fun <T:Comparable<T>?> floyd_rivest_select(array:Array<T> , k:Int):T
	{
		val copy:List<T>=ArrayList<T>(Arrays.asList<T>(*array))
		return `$floyd_rivest_select0_1`(copy , k-1)
	}
	
	/*------------------------------------------------- /
  / [ BEGIN INTERNAL DEFINITIONS AND FUNCTIONALITIES] /
  /--------------------------------------------------*/
	private fun `$huffman_table0_1`(node:`$freq_node_00`? , prefix:String , table:MutableMap<Char , String>) // helper
	{
		if (node!!.leaf())
		{
			table[node.chc]=prefix
			return
		}
		`$huffman_table0_1`(node.left , prefix+'0' , table)
		`$huffman_table0_1`(node.right , prefix+'1' , table)
	}
	
	private fun <T:Comparable<T>?> `$quick_select0_1`(array:Array<T> , left:Int , right:Int , k:Int):T // helper
	{
		if (left==right) return array[left]
		var pivot=left+RNG0_1.nextInt(right-left+1)
		pivot=`$quick_select0_2`(array , left , right , pivot)
		return if (k==pivot) array[k]
		else if (k<pivot) `$quick_select0_1`(
			array ,
			left ,
			pivot-1 ,
			k
		)
		else `$quick_select0_1`(array , pivot+1 , right , k)
	}
	
	private fun <T:Comparable<T>?> `$floyd_rivest_select0_1`(list:List<T> , k:Int):T
	{
		if (list.size==1) return list[0]
		val pivot=RNG0_1.nextInt(list.size)
		val pivot_value=list[pivot]
		val lesser:MutableList<T>=ArrayList()
		val greater:MutableList<T>=ArrayList()
		for (e in list)
		{
			if (e!!.compareTo(pivot_value)<0) lesser.add(e) else if (e.compareTo(pivot_value)>0) greater.add(e)
		}
		return if (k<lesser.size) `$floyd_rivest_select0_1`(
			lesser ,
			k
		)
		else if (k<lesser.size+greater.size) pivot_value
		else `$floyd_rivest_select0_1`(
			greater ,
			k-lesser.size-greater.size
		)
	}
	
	private fun <T:Comparable<T>?> `$quick_select0_2`(array:Array<T> , left:Int , right:Int , pivot:Int):Int // partition
	{
		val temp=array[pivot]
		swap(array , pivot , right)
		var ii=left
		for (i in left until right)
		{
			if (array[i]!!.compareTo(temp)<0)
			{
				swap(array , ii , i)
				ii++
			}
		}
		swap(array , right , ii)
		return ii
	}
	
	private fun <T> `$dfs_traverse0_1`(node:T , adjList:Map<T , List<T>> , visited:MutableSet<T> , res:MutableList<T>) // helper
	{
		visited.add(node)
		res.add(node)
		for (neighbor in adjList[node]!!) if (!visited.contains(neighbor)) `$dfs_traverse0_1`(
			neighbor ,
			adjList ,
			visited ,
			res
		)
	}
	
	/**
	 * Bias -> Favor towards something.
	 *
	 *
	 * This can be used for
	 * [.binary_search]
	 *
	 * @author Jack Meng
	 */
	enum class algos_Bias
	{
		UP_BIAS , DOWN_BIAS
	}
	
	private class `$freq_node_00` // for huffman frequency table
		:Comparable<`$freq_node_00`>
	{
		var chc=0.toChar()
		var freq:Int
		var left:`$freq_node_00`?=null
		var right:`$freq_node_00`?=null
		
		constructor(ch:Char , freq:Int)
		{
			chc=ch
			this.freq=freq
		}
		
		constructor(left:`$freq_node_00` , right:`$freq_node_00`?)
		{
			freq=left.freq+right!!.freq
		}
		
		fun leaf():Boolean
		{
			return left==null&&right==null
		}
		
		override fun compareTo(e:`$freq_node_00`):Int
		{
			return freq-e.freq
		}
	}
}