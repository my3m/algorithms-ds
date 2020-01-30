package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringRepeat {
	public String repeat(String s, int k) {
		if(s.length() == 0)
			return "";
		char[] arr = new char[k*s.length()];
		int z = 0;
		for(int i =0; i < k; i++) {
			for(int j = 0; j < s.length(); j++) {
				arr[z++] = s.charAt(j);
			}
		}
		return new String(arr);
	}
	
	@Test
	public void Test1() {
		assertEquals("aaa", repeat("a", 3));
	}
	@Test
	public void Test2() {
		assertEquals("abcabcabc", repeat("abc", 3));
	}
	@Test
	public void Test3() {
		assertEquals("this is a samplethis is a samplethis is a sample", repeat("this is a sample", 3));
	}
}
