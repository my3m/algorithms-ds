package io.sorting;

import java.util.Arrays;

import org.junit.Test;

public class MergeSort {
	public void mergeSort(int[] nums) {
		int[] arr = mergeSortHelper(nums, 0, nums.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	
	int[] mergeSortHelper(int[] nums, int l, int r) {
		if(l == r) {
			return new int[] { nums[l] };
		}
		int mid = l + (r - l)/2;
		//8,5,2,9
		int[] left = mergeSortHelper(nums, l, mid);
		int[] right = mergeSortHelper(nums, mid + 1, r);
		
		int i = 0, j = 0, k = 0;
		int[] newArray = new int[left.length + right.length];
		/**
		 	[8,5], [2,4]
		 	2,4,
		 */
		/** I really struggled at implementing two ptr sorting idea in code
		 * 	specifically, when one ptr reaches end of array */
		while(i < left.length && j < right.length) {
			if(left[i] <= right[j]) {
				newArray[k] = left[i];
				i++;
			}
			else if(right[j] < left[i]) {
				newArray[k] = right[j];
				j++;
			}
			k++;
		}
		while(i != left.length) {
			newArray[k++] = left[i++];
		}
		while(j != right.length) {
			newArray[k++] = right[j++];
		}		
		return newArray;
	}
	
	@Test
	public void Test1() {
		int[] nums = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		mergeSort(nums);
		//System.out.println(Arrays.toString(nums));
	}
}
