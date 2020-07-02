package io.playground;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
	public static void main(String[] args) {
		/**
			sum=3, carry=1, e= 1
			sum=2, carry=1, e= 0
			sum=1, carry=0, e= 1
			sum=0, carry=0, e= 0
			
			e = sum % 2
			carry = sum / 2;
			
		 */
		int[] arr = new int[] { 3,2,1,5,6,4 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
    static void reverse(char[] arr) {
        int i = 0, j = arr.length - 1;
        while(i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
	
	static void deleteDuplicates(int[] arr) {
		int i = 0, k = 1;
		//2,3,5,7,11,13,
		for(;k < arr.length; k++) {
			if(arr[k] != arr[i]) {
				arr[++i] = arr[k];
			}
		}
		for(int j = arr.length - 1; j > i; j--) {
			arr[j] = 0;
		}
	}
	
	static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	static void sort(int[] arr, int lo, int hi) {
		if(lo >= hi)
			return;
		Random r=  new Random();
		int pvt = lo + r.nextInt(hi-lo);
		pvt = partition(arr, lo, hi, pvt);
		sort(arr, lo, pvt - 1);
		sort(arr, pvt + 1, hi);
	}
	
	static int partition(int[] arr, int lo, int hi, int pvt) {
		int pivot = arr[pvt];
		exch(arr, pvt, hi);
		int k = lo;
		for(int i = lo; i <= hi; i++) {
			if(arr[i] < pivot) {
				exch(arr, k, i);
				k++;
			}
		}
		exch(arr, k, hi);
		return k;
	}
	
	static int partitionx(int[] arr, int lo, int hi) {
		int pivot = arr[lo];
		int i = lo + 1;
		int j = hi;
		
		//we need to find a value greater than or equal to pivot
		//we need to find a value less than or equal to pivot
		//8,5,2,3,5,6,9
		while(i <= j) {
			if(arr[i] > pivot && arr[j] <= pivot) {
				exch(arr, i++, j--);
			}
			else if(arr[j] >= pivot) {
				j--;
			}
			else if(arr[i] <= pivot) {
				i++;
			}
		}
		exch(arr, lo, j);
		return j;
	}
	
	static void exch(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
