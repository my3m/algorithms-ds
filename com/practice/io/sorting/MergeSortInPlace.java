package io.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class MergeSortInPlace {
	public void mergeSort(int[] array) {
		int[] auxiliary = Arrays.copyOf(array, array.length);
		mergeSortHelper(array, auxiliary, 0, array.length - 1, 1);
	}
	
	public void mergeSortHelper(int[] array, int[] auxiliary, int l, int r, int index) {
		if(l == r) {
			return;
		}
		int mid = l + (r - l)/2;
		
		mergeSortHelper(auxiliary, array, l, mid, index + 1);
		mergeSortHelper(auxiliary, array, mid + 1, r, index + 1);

		//[8], [5]		
		//[8,5|2,9]
		//[8|5]
		//[5,8], [9,2]
		merge(array, auxiliary, l, r, mid);
	}
	
	public void merge(int[] array, int[] auxiliary, int l, int r, int mid) {
		int i =  l, j = mid + 1, k = l;
		while(i < mid + 1 && j <= r) {
			if(auxiliary[i] <= auxiliary[j]) {
				array[k++] = auxiliary[i++];
			}
			else {
				array[k++] = auxiliary[j++];
			}
		}
		while(i < mid + 1) {
			array[k++] = auxiliary[i++];
		}
		while(j <= r) {
			array[k++] = auxiliary[j++];
		}
	}
	
	@Test
	public void Test1() {
		int[] nums = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		mergeSort(nums);
		System.out.println(Arrays.toString(nums));
		assertArrayEquals(new int[] { 2,3,5,5,6,8,9}, nums);
	}	
}
