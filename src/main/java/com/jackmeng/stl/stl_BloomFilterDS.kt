// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.
package com.jackmeng.stl

import java.util.BitSet

class stl_BloomFilterDS(size:Int , hashes:Int)
{
	private val bitset:BitSet
	private val hashFxs:Array<stl_HashFunction?>
	
	init
	{
		bitset=BitSet(size)
		hashFxs=arrayOfNulls(hashes)
		for (i in 0 until hashes) hashFxs[i]=stl_HashFunction(size)
	}
	
	fun add(value:String?)
	{
		for (hashFunction in hashFxs)
		{
			val hash=hashFunction!!.hash(value)
			bitset.set(hash)
		}
	}
	
	operator fun contains(value:String?):Boolean
	{
		for (hashFunction in hashFxs)
		{
			val hash=hashFunction!!.hash(value)
			if (!bitset[hash]) return false
		}
		return true
	}
}