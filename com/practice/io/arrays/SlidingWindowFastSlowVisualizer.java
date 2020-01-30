package io.arrays;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class SlidingWindowFastSlowVisualizer {

	/* Example: Maxiumum consecutive 1s with one 0 flip
	 * Application: Sliding Window Technique (Slow-Fast ptr)
	 * A fast pointer which increments
	 * A slow pointer which catches up
	 * j starts incrementing when our window becomes invalid
	 * nums[i] == 0, decrement k (we lose one flip)
	 * nums[j] == 0, increnent k (we gain one flip)
	 * The size of the window remains the same, the output is length of window
	 */
	public static void main(String[] args) {
        int k = 1;
        int j = 0;
        int i = 0;
        int[] nums = new int[] { 1,0,1,1,0,1,0,0,0,1,1,1,1,0};
        
        for(; i<nums.length;i++) {
            if(nums[i] == 0) {
                k--;
            }
            if(k < 0) {
                if(nums[j] == 0) {
                    k++;
                }
                j++;
            }  
            char[] spc = new char[3*j];
            System.out.println(Arrays.toString(nums));
            System.out.printf("%s%s\r\n",new String(spc),Arrays.toString(generateSubArray(nums, j, i)));
        }
        System.out.println(i - j);
	}
	
	static int[] generateSubArray(int[] arr, int i, int j) {
		if(i < 0 || i >= arr.length)
			return arr;
		if(j < 0 || j >= arr.length)
			return arr;
		if(j < i)
			return arr;
		int[] result = new int[j - i + 1];
		for(int x = 0; x < result.length; x++) {
			result[x] = arr[x + i];
		}
		return result;
	}

	@Test
	public void Test1() {
		int[] arr = new int[] { 1,0,1,1,0,1,0,0,0 };
		assertArrayEquals(new int[] { 0,1,1,0,1}, SlidingWindowFastSlowVisualizer.generateSubArray(arr, 1, 5));
	}

	@Test
	public void Test2() {
		int[] arr = new int[] { 1,0,1,1,0,1,0,0,0 };
		assertArrayEquals(new int[] { 0,0,0}, SlidingWindowFastSlowVisualizer.generateSubArray(arr, 6, 8));
	}
	
	@Test
	public void Test3() {
		int[] arr = new int[] { 1,0,1,1,0,1,0,0,0 };
		assertArrayEquals(new int[] { 1, 0, 1}, SlidingWindowFastSlowVisualizer.generateSubArray(arr, 0, 2));
	}	
}
