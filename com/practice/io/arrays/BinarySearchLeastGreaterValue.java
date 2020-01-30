package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchLeastGreaterValue {
	public int binarySearch(int[] array, int left, int right, int target) {
		if(left > right)
			return -1;
		if(array[0] > target)
			return 0;
		if(array[array.length - 1] <= target)
			return -1;
		int leastGreaterValue = -1;
		int mid = left + ((right-left)/2);
		//0,1,3,5,8,10
		//7,8,9,10,11
		//1,2,3,4,5
		while(left<=right) {
			mid = left + ((right-left)/2);
			if(array[mid] > target) {
				//.....5...7
				//...8..9
//				if(mid - 1 >= 0 && array[mid-1] <= target) {
//					return mid;
//				}
				leastGreaterValue = mid;
				right = mid-1;
			}
			else if(array[mid] < target) {
				left = mid + 1;
			}
			else {
				left = mid + 1;
			}
		}
		return leastGreaterValue;
//		return -1;
	}
	
	@Test
	public void Test1() {
		int[] array = new int[] { 0,1,3,5,8,10};
		assertEquals(4, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test2() {
		int[] array = new int[] { 0,1,3,5,6,7,8,10};
		assertEquals(5, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test3() {
		int[] array = new int[] { 1,2,3,4,5};
		assertEquals(-1, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test4() {
		int[] array = new int[] { 11,12,17,20,21,25};
		assertEquals(0, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test5() {
		int[] array = new int[] { 1,4,7,9,11,13,14};
		assertEquals(2, binarySearch(array, 0, array.length -1, 6));
	}
}
