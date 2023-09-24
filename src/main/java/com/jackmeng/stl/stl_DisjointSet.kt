// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

class stl_DisjointSet(size:Int)
{
	private val parent:IntArray
	private val rank:IntArray
	
	init
	{
		parent=IntArray(size)
		rank=IntArray(size)
		for (i in 0 until size)
		{
			parent[i]=i
			rank[i]=0
		}
	}
	
	fun find(x:Int):Int
	{
		if (parent[x]!=x) parent[x]=find(parent[x])
		return parent[x]
	}
	
	fun union(x:Int , y:Int)
	{
		val root_x=find(x)
		val root_y=find(y) // ??
		if (root_x==root_y) return
		if (rank[root_x]<rank[root_y]) parent[root_x]=root_y
		else if (rank[root_x]>rank[root_y]) parent[root_y]=root_x
		else
		{
			parent[root_y]=root_x
			rank[root_x]++
		}
	}
}