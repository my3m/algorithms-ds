package io.playground;

import org.junit.Test;

public class BinarySearchPlayground {
	public int findMaxInBitonicArray(int[] arr) {
		int l = 0, r = arr.length - 1;
		while (l < r) {
			int m = l + (r-l)/2;
			if(arr[m+1] > arr[m]) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}
		return arr[l];
	}
	
	@Test
	public void findMaxInBitonicArray_Tests() {
	    System.out.println(findMaxInBitonicArray(new int[] { 1, 3, 8, 12, 4, 2 }));
	    System.out.println(findMaxInBitonicArray(new int[] { 3, 8, 3, 1 }));
	    System.out.println(findMaxInBitonicArray(new int[] { 1, 3, 8, 12 })); //max end of array
	    System.out.println(findMaxInBitonicArray(new int[] { 10, 9, 8 }));	// max beginning of array
	}
}
