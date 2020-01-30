package io.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearch {
	public int binarySearch(int[] arr, int left, int right, int target) {
		int mid = left + ((right - left)/2);
		while(left <= right) {
			mid = left + ((right - left)/2);
			if(target < arr[mid]) {
				right = mid - 1;
			} else if(target > arr[mid]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return mid;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3, 4, 6, 8, 9, 11, 13, 16, 18, 19, 22, 24, 27, 30 };
		BinarySearch binarySearch = new BinarySearch();
		int i = binarySearch.binarySearch(arr, 0, arr.length - 1, 20);
		System.out.println(i);
	}
	
	@Test
	public void Test1() {
		int[] arr = new int[] { 1, 3, 4, 6, 8, 9, 11, 13, 16, 18, 19, 22, 24, 27, 30 };
		assertEquals(5, binarySearch(arr, 0, arr.length - 1, 9));
	}
	
	@Test
	public void Test2() {
		int[] arr = new int[] { 1, 3, 4, 6, 8, 9, 11, 13, 16, 18, 19, 22, 24, 27, 30 };
		assertEquals(14, binarySearch(arr, 0, arr.length - 1, 30));
	}
	
	@Test
	public void Test3() {
		int[] arr = new int[] { 1, 3, 4, 6, 8, 9, 11, 13, 16, 18, 19, 22, 24, 27, 30 };
		assertEquals(0, binarySearch(arr, 0, arr.length - 1, 1));
	}
}
