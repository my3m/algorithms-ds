package com.interviewcake.greedy;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

import static org.junit.Assert.*;

public class HighestProductOf3Numbers {

	public static int highestProductOf3(int[] intArray) {

		// calculate the highest product of three numbers
		// 1, 2, 3, 4

		// 6, 1, 3, 5, 7, 8, 2
		//
		if (intArray.length < 3)
			throw new IllegalArgumentException("");

		// return recursive(intArray, 0, 1, 3);
		return greedy(intArray);
	}

	// -5, 4, 8, 2, 3
	static int greedy(int[] nums) {
//         int k = 3;
//         int max = numbers[0], min = numbers[0];
//         for(int i = 1; i < numbers.length; i++) {
//             //2,1,-2,-6
//             int prev = max;
// //            max = Math.max(Math.max(max, numbers[i]), Math.max(min * numbers[i], max * numbers[i]));
//             max = Math.max(max, Math.max(min * numbers[i], max * numbers[i]));
//             if(max > prev) {
//                 k--;
//                 if(k == 0)
//                     break;
//             }
//             // min = Math.min(Math.min(min, numbers[i]), min * numbers[i]);
//             min = Math.min(min, min * numbers[i]);
//         }

		// [v1,v2,v3]
		// [m1,m2,m3]
		int[] max = new int[3], min = new int[3];
		Arrays.fill(max, Integer.MIN_VALUE);
		Arrays.fill(min, Integer.MAX_VALUE);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max[0]) {
				max[2] = max[1];
				max[1] = max[0];
				max[0] = nums[i];
			} else if (nums[i] > max[1]) {
				max[2] = max[1];
				max[1] = nums[i];
			} else if (nums[i] > max[2]) {
				max[2] = nums[i];
			}
			if (nums[i] < min[0]) {
				min[2] = min[1];
				min[1] = min[0];
				min[0] = nums[i];
			} else if (nums[i] < min[1]) {
				min[2] = min[1];
				min[1] = nums[i];
			} else if (nums[i] < min[2]) {
				min[2] = nums[i];
			}
		}
		return Math.max(min[0] * min[1] * max[0], max[2] * max[1] * max[0]);
	}

	static int sort(int[] numbers) {
		Arrays.sort(numbers);
		// all neg //-1,-3,-4,-1,-1,-,1-,1,-10
		// 2 neg //-10,-2,4,56,2,5,2,6,
		// 1 neg //-3,10,2,1,5,2
		// all pos // 1,2,3,4,5,6,7

		// -10,-9,-8,-7,-3
		int len = numbers.length;
		return Math.max(numbers[len - 1] * numbers[len - 2] * numbers[len - 3],
				numbers[0] * numbers[1] * numbers[len - 1]);
	}

	// edge case: negative numbers

	// -5, -1, -3, -2

	// ans is -6
	static int recursive(int[] numbers, int index, int product, int k) {
		if (k <= 0)
			return product;
		else if (index >= numbers.length)
			return Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = index; i < numbers.length; i++) {
			max = Math.max(max, recursive(numbers, i + 1, product * numbers[i], k - 1));
		}
		max = Math.max(max, recursive(numbers, index + 1, product, k));
		return max;
	}

	// tests

	@Test
	public void shortArrayTest() {
		final int actual = highestProductOf3(new int[] { 1, 2, 3, 4 });
		final int expected = 24;
		assertEquals(expected, actual);
	}

	@Test
	public void longerArrayTest() {
		final int actual = highestProductOf3(new int[] { 6, 1, 3, 5, 7, 8, 2 });
		final int expected = 336;
		assertEquals(expected, actual);
	}

	@Test
	public void arrayHasOneNegativeTest() {
		final int actual = highestProductOf3(new int[] { -5, 4, 8, 2, 3 });
		final int expected = 96;
		assertEquals(expected, actual);
	}

	@Test
	public void arrayHasTwoNegativesTest() {
		final int actual = highestProductOf3(new int[] { -10, 1, 3, 2, -10 });
		final int expected = 300;
		assertEquals(expected, actual);
	}

	@Test
	public void arrayIsAllNegativesTest() {
		final int actual = highestProductOf3(new int[] { -5, -1, -3, -2 });
		final int expected = -6;
		assertEquals(expected, actual);
	}

	@Test(expected = Exception.class)
	public void exceptionWithEmptyArrayTest() {
		highestProductOf3(new int[] {});
	}

	@Test(expected = Exception.class)
	public void exceptionWithOneNumberTest() {
		highestProductOf3(new int[] { 1 });
	}

	@Test(expected = Exception.class)
	public void exceptionWithTwoNumbersTest() {
		highestProductOf3(new int[] { 1, 1 });
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(HighestProductOf3Numbers.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}
	}
}