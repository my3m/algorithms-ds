package io.arrays;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class BinarySearchString {

	// a, b, f, h
	// e
	// l=0,r=3, m=1, b < e, so l=2
	// l=1,r=3, m=2, f > e, so r=1
	// l=1,r=1, m=1, b < e, so l=2
	// returns 1
	//can return -(low+1), if(pos < 0) -(pos+1) is ins position
	public int binarySearch(String[] arr, String text) {
		if(arr[0].compareTo(text) > 0)
			return 0;
		if(text.compareTo(arr[arr.length - 1]) > 0)
			return arr.length;
		int left = 0;
		int right = arr.length - 1;
		int mid = left + ((right - left) / 2);
		int insPos = 0;
		while (left <= right) {
			mid = left + ((right - left) / 2);
			if (arr[mid].compareTo(text) == 0) {
				return mid;
			} else if (arr[mid].compareTo(text) < 0) { // b < e
				left = mid + 1;
				insPos = mid + 1;
			} else if (arr[mid].compareTo(text) > 0) {
				right = mid - 1;
			}
		}
		return insPos;
	}

	@Test
	public void Test1() {
		String[] terms = new String[] 
				{
						"mobile","moneypot","monitor","mouse","mousepad"
				};
		assertEquals(1, binarySearch(terms, "moneypit"));
		assertEquals(0, binarySearch(terms, "mo"));
		assertEquals(1, binarySearch(terms, "mobix"));
		assertEquals(5, binarySearch(terms, "zz"));
		assertEquals(0, binarySearch(terms, "aa"));
	}
}
