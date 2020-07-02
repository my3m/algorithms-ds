package io.stack;

import static org.junit.Assert.assertArrayEquals;

import java.util.Stack;

import org.junit.Test;

public class m_739_DailyTemperatures {
	public int[] dailyTemperatures(int[] T) {
		/*
		 * 73,74
		 * Using a stack, we can pop element if next element is greater
		 * Keep a track of indexes in stack
		 */
		Stack<Integer> s1 = new Stack<Integer>();
		int[] result = new int[T.length];
		for(int i = 0; i < T.length; i++) {
			while(s1.size() > 0 && T[i] > T[s1.peek()]) {
				//
				result[s1.peek()] = i - s1.pop(); 
			}
			s1.push(i);
			//2,  3,  4,  5,  6
			//75, 71, 69, 72, 76
			//75,71
		}
		while(s1.size() > 0)
			result[s1.pop()] = 0;
		return result;
	}
	
	@Test
	public void Test1() {
		int[] input = new int[] { 73, 74, 75, 71, 69, 72, 76, 73 };
		int[] expected = new int[] { 1, 1, 4, 2, 1, 1, 0, 0 };
		assertArrayEquals(expected, dailyTemperatures(input));
	}
}
