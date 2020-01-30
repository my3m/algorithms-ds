package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
	1) Contains (True or False)
	2) Index of first occurrence of a key
	3) Index of last occurrence of a key
	4) Index of least element greater than key
	5) Index of greatest element less than key
 */
public class BinarySearchClosestValue {
	public int binarySearchClosestValue(int[] array, int left, int right, int target) {
		if(array.length == 0)
			return -1;
		if(left > right)
			return -1;
		//1,3,6,7 => t=4
		//1,3,3,4,7 => t=4
		int mid = left + ((right-left)/2);
		int min = array[0];
		while(left <= right) {
			mid = left + ((right-left)/2);
			if(Math.abs(target - array[mid]) < Math.abs(target - min)) {
				min = array[mid];
			}
			if(array[mid] > target) {
				right = mid - 1;
			}
			else if(array[mid] < target) {
				left = mid + 1;
			}
			else {
				return array[mid];
			}
		}
		return min;
	}
	
	@Test
	public void Test1() {
		int[] arr = new int[] { 1,3,6,7};
		assertEquals(3,  binarySearchClosestValue(arr, 0, arr.length - 1, 4));
	}
	@Test
	public void Test2() {
		int[] arr = new int[] { 1,3,3,3,4,6,7};
		assertEquals(4,  binarySearchClosestValue(arr, 0, arr.length - 1, 4));
	}
	@Test
	public void Test3() {
		int[] arr = new int[] { 1,1,1,1,2,2,2,2,5,5,8,9,12,15};
		assertEquals(5,  binarySearchClosestValue(arr, 0, arr.length - 1, 4));
	}
	@Test
	public void Test4() {
		int[] arr = new int[] { 1,1,1,1,2,2,2,2,5,5,8,9,12,15};
		assertEquals(5,  binarySearchClosestValue(arr, 0, arr.length - 1, 6));
	}
}
