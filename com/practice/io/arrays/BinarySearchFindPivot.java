package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchFindPivot {
	public int findPivot(int arr[]) {
		int left = 0;
		int right = arr.length - 1;
//		if(arr[left] < arr[right])
//			return 0;
		while(left <= right) {
			int mid = left + (right - left)/2;
			/**
			 * Pivot, k + 1, such that 4,5,6,7,8,9,0,1,2
			 * arr[k] > arr[k+1]
			 * 	
			 */
			if(mid + 1 < arr.length && arr[mid] > arr[mid+1])
				return mid + 1;
			if(mid - 1 >= 0 && arr[mid-1] > arr[mid])
				return mid;
			if(arr[left] < arr[mid]) {
				//If the left-side is sorted, the pivot must exist on the right
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return 0;
	}
	
	
	//l=0, r=1, m=0
	@Test
	public void Test1() {
		int[] arr = new int[] { 1, 3 };
		assertEquals(0, findPivot(arr));
	}
	@Test
	public void Test2() {
		int[] arr = new int[] { 3, 1 };
		assertEquals(1, findPivot(arr));
	}
	@Test
	public void Test3() {
		int[] arr = new int[] { 4,5,6,7,8,9,0,1,2,3 };
		assertEquals(6, findPivot(arr));
	}
	@Test
	public void Test4() {
		int[] arr = new int[] { 4,5,6,7,8,9,0,1,2,3 };
		assertEquals(6, findPivot(arr));
	}
	@Test
	public void Test5() {
		int[] arr = new int[] { 0 };
		assertEquals(0, findPivot(arr));
	}
	@Test
	public void Test6() {
		int[] arr = new int[] { 1,2,3,4,5,6,7,0 };
		assertEquals(arr.length - 1, findPivot(arr));
	}
	@Test
	public void Test8() {
		int[] arr = new int[100000000];
		for(int i = 1; i < arr.length; i++) {
			arr[i-1] = i;
		}
		arr[arr.length - 1] = 0;
		assertEquals(arr.length - 1, findPivot(arr));
	}
	
}
