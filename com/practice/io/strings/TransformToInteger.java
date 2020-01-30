package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransformToInteger {
	/*
	 * "123"->123
	 * "0" -> 0
	 * "10482" -> 10482
	 * "9826" => 9*1000=>9000 + 800 + 20 + 6
	 * "1849248202"
	 */
	
	//O(n) time + O(n) space
//	public long transformToInteger(String s) {
//		if(s.length() == 0)
//			return 0;
//		char[] arr = s.toCharArray();
//		int k = arr.length - 1;
//		long result = 0;
//		for(int i = 0; i < arr.length; i++) {
//			if(arr[i] >= '0' && arr[i] <= '9') {
//				result += (arr[i] - '0') * (long)Math.pow(10, k);
//				k--;
//			}
//		}
//		return result;
//	}
	
	//O(n) time + O(1) space
	public long transformToInteger(String s) {
		if(s.length() == 0)
			return 0;
		int k = s.length() - 1;
		long result = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				result += (s.charAt(i) - '0') * (long)Math.pow(10, k);
				k--;
			}
		}
		return result;
	}
	
	@Test
	public void Test1() {
		System.out.println(Integer.MIN_VALUE);
		assertEquals(1849248202, transformToInteger("1849248202"));
	}
	@Test
	public void Test2() {
		assertEquals(0, transformToInteger(""));
	}
	@Test
	public void Test3() {
		assertEquals(820, transformToInteger("820"));
	}
	@Test
	public void Test4() {
		assertEquals(974919, transformToInteger("974919"));
	}
	@Test
	public void Test5() {
		assertEquals(111111111, transformToInteger("111111111"));
	}
	@Test
	public void Test6() {
		assertEquals(1, transformToInteger("1"));
	}
	@Test
	public void Test7() {
		assertEquals(10, transformToInteger("10"));
	}
	@Test
	public void Test8() {
		assertEquals(1000, transformToInteger("1000"));
	}
}
