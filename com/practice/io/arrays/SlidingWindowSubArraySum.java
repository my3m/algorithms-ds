package io.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SlidingWindowSubArraySum {
	public int[] subarraySum(int[] arr, int sum) {
		int i = 0, j = 0, temp = 0;
		for(; i < arr.length; i++) {
			temp += arr[i];
			while(j <= i && temp > sum) {
				temp -= arr[j];
				j++;
			}
			if(temp == sum && i - j > 0) 
				return new int[] { j, i };
		}
		return new int[] { -1, -1 };
	}
	
	@Test
	public void Test1() {
		assertArrayEquals(new int[] { 2, 4 }, subarraySum(new int[] {1, 4, 20, 3, 10, 5 }, 33));
	}
	
	@Test
	public void Test2() {
		assertArrayEquals(new int[] { 4, 5 }, subarraySum(new int[] {1, 4, 20, 3, 10, 5 }, 15));
	}
	
	@Test
	public void Test3() {
		assertArrayEquals(new int[] { 1, 4 }, subarraySum(new int[] {1, 4, 0, 0, 3, 10, 5 }, 7));
	}
	
	@Test
	public void Test4() {
		assertArrayEquals(new int[] { -1, -1 }, subarraySum(new int[] { 1, 4 }, 0));
	}
	
	/**
	 * Does not work for negative values, since @ i = 0, arr[i] = 10 will always be > (-10)
	 * & temp will always become 0.
	 * Use the hashing implementation, to resolve this.
	 */
	@Test
	public void Test5() {
		assertArrayEquals(new int[] { 0, 3 }, subarraySum(new int[] { 10, 2, -2, -20, 10 }, -10));
	}
}
