package io.arrays;

import java.util.Arrays;

import org.junit.Test;

public class PutNumbersFirst {
	public int rearrangeLast(int[] arr, int x) {
		int i = arr.length - 1, j = 0;
		while(i >= j) {
			if(arr[i] != x && arr[j] == x) {
				swap(arr, i--, j++);
			}
			else if(arr[i] == x) {
				i--;
			}
			else {
				j++;
			}
		}
		return i;
	}
	
	void swap(int[] arr, int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public int rearrangeFirst(int[] arr, int x) {
		int i = 0, j = arr.length - 1;
		while (i <= j) {
			if(arr[i] != x && arr[j] == x) {
				swap(arr, i++, j--);
			}
			else if(arr[i] == x) {
				i++;
			}
			else {
				j--;
			}
		}
		return i;
	}
	
	@Test
	public void Test1() {
		int[] arr = new int[]
				{1, 0, 2, 0, 2, 1, 2, 1, 2, 0, 0, 0, 1, 0, 2, 1, 0, 2, 0, 1, 0, 2, 1, 0, 2, 1, 2, 0, 2, 1, 1, 2, 2, 0, 1, 1, 0, 1, 1, 1, 2, 1, 0, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 0, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 0, 2, 0, 1, 2, 1, 1, 1, 0, 2, 0, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0, 1, 2, 2, 1, 2, 2, 1, 1, 2, 0, 1, 0, 1, 2, 0, 2, 1, 2, 1, 1, 1, 2, 2, 2, 0, 0, 0, 1, 2, 0, 0, 0, 0};
		rearrangeLast(arr, 1);
		rearrangeFirst(arr, 0);
		//putNumbersFirst(arr,y, 0);
		System.out.println(Arrays.toString(arr));
	}
}
