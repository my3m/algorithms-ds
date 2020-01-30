package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//greatest number n, such that n < x in array
public class BinarySearchGreatestLesser {
	public int binarySearch(int[] array, int left, int right, int target) { 
		if(left > right)
			return -1;
		if(array[0] >= target)
			return -1;
		if(array[array.length -1] < target)
			return array.length - 1;
		int greatestLesser = -1;
		int mid = left + ((right-left)/2);
		while(left <= right) {
			//0,2,4,6,9,11,13
			mid = left + ((right-left)/2);
			if(array[mid] < target) {
//				if(mid + 1 < array.length && array[mid+1] >= target) {
//					return mid;
//				}
				greatestLesser = mid;
				//0,2,3,4,6,7,8,13
				left = mid + 1;
			}
			else if(array[mid] > target) {
				//0,1,3,5,7,11,15
//				if(mid-1>=0 && array[mid-1] < target) {
//					return mid-1;
//				}
				//0,1,3,5,7,10,11,15
				right = mid - 1;
			}
			else {
//				if(mid-1>=0 && array[mid-1]<target) {
//					return mid-1;
//				}
				right = mid-1;	
			}
		}
		return greatestLesser;
//		return -1;
	}
	@Test
	public void Test1() {
		int[] array = new int[] { 0,1,3,5,8,10};
		assertEquals(3, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test2() {
		int[] array = new int[] { 0,1,3,5,6,7,8,10};
		assertEquals(3, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test3() {
		int[] array = new int[] { 1,2,3,4,5};
		assertEquals(4, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test4() {
		int[] array = new int[] { 11,12,17,20,21,25};
		assertEquals(-1, binarySearch(array, 0, array.length -1, 6));
	}
	@Test
	public void Test5() {
		int[] array = new int[] { 1,4,7,9,11,13,14};
		assertEquals(1, binarySearch(array, 0, array.length -1, 6));
	}	
}
