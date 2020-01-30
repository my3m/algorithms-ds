package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchFirstOccurence {
	public int binarySearch(int[] array, int left, int right, int target) {
		if(left > right)
			return -1;
		if(array[0] == target)
			return 0;
		if(array[array.length - 1] < target)
			return -1;
		int mid = left + ((right-left)/2);
		int firstOccurence = -1;
		while(left<=right) {
			mid = left + ((right-left)/2);
			if(array[mid] < target) {
				left = mid + 1;
			}
			else if(array[mid] > target) {
				right = mid - 1;
			}
			else {
				//1,2,3,6,8,9
				//1,2,3,6,6,6,6,6,6,6,6,6,6,8,9
//				if(mid-1>=0 && array[mid-1] < target) {
//					return mid;
//				}
				firstOccurence = mid;
				right = mid - 1;
			}
		}
		return -1;
	}
	@Test
	public void Test1() {
		int[] array = new int[] { 0,1,3,5,8,10};
		assertEquals(2, binarySearch(array, 0, array.length -1, 3));
	}
	@Test
	public void Test2() {
		int[] array = new int[] { 0,1,3,3,3,3,3,3,3,6,8,10};
		assertEquals(2, binarySearch(array, 0, array.length -1, 3));
	}
	@Test
	public void Test3() {
		int[] array = new int[] { 1,2,3,4,5};
		assertEquals(4, binarySearch(array, 0, array.length -1, 5));
	}
	@Test
	public void Test4() {
		int[] array = new int[] { 1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5};
		assertEquals(10, binarySearch(array, 0, array.length -1, 5));
	}
	@Test
	public void Test5() {
		int[] array = new int[] { 1,4,7,9,11,13,14};
		assertEquals(-1, binarySearch(array, 0, array.length -1, 15));
	}
	@Test
	public void Test6() {
		int[] array = new int[] { 1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5};
		assertEquals(0, binarySearch(array, 0, array.length -1, 1));
	}
	@Test
	public void Test7() {
		int[] array = new int[] { 1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5,6};
		assertEquals(array.length -1, binarySearch(array, 0, array.length -1, 6));
	}
}
