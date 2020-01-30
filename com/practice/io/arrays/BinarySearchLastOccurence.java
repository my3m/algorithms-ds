package io.arrays;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class BinarySearchLastOccurence {
	public int binarySearch(int[] array, int left, int right, int target) {
		if(left > right)
			return -1;
		if(array[array.length - 1] == target) 
			return array.length - 1;
		if(array[array.length - 1] < target)
			return -1;
		if(array[0] > target)
			return -1;
		int mid = left + ((right-left)/2);
		int lastOccurence = -1;
		while(left<=right) {
			mid = left + ((right-left)/2);
			if(target > array[mid]) {
				left = mid + 1;
			}
			else if(target < array[mid]) {
				right = mid - 1;
			}
			else {
				lastOccurence = mid;
				left = mid + 1;
			}
		}
		return lastOccurence;
	}
	@Test
	public void Test1() {
		int[] arr = new int[] { 0,1,2,3,4,4,4,4,4,4,7,8,9,10,10};
		assertEquals(3, binarySearch(arr, 0, arr.length - 1, 3));
	}
	@Test
	public void Test2() {
		int[] arr = new int[] { 0,1,2,3,4,4,4,4,4,4,7,8,9,10,10};
		assertEquals(0, binarySearch(arr, 0, arr.length - 1, 0));
	}
	@Test
	public void Test3() {
		int[] arr = new int[] { 0,1,2,3,4,4,4,4,4,4,7,8,9,10,10};
		assertEquals(arr.length - 1, binarySearch(arr, 0, arr.length - 1, 10));
	}
	@Test
	public void Test4() {
		int[] arr = new int[] { 0,1,2,3,4,4,4,4,4,4,7,8,9,10,10};
		assertEquals(9, binarySearch(arr, 0, arr.length - 1, 4));
	}
	@Test
	public void Test5() {
		int[] arr = new int[] { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2};
		assertEquals(arr.length-2, binarySearch(arr, 0, arr.length - 1, 1));
	}
}
