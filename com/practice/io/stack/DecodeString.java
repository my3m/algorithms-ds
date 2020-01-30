package io.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecodeString {
	public String decodeString(String s) {
		return helper(s, 0, new int[1]);
	}
	
	/*
	 * 3[a] => aaa
	 * 12[a] => 0*10 + 1=> 1
	 * 				1*10 + 2
	 */
	public String helper(String s, int ptr, int[] eidx) {
		StringBuilder sb = new StringBuilder();
		int k = 0;
		while(ptr >= 0 && ptr < s.length()) {
			if(s.charAt(ptr) >= '0' && s.charAt(ptr) <= '9') {
				k = k*10 + (s.charAt(ptr)-'0');
			}
			else if(s.charAt(ptr) == '[') {
				//We start recursing here
				String inner = helper(s, ptr + 1, eidx);
				ptr = eidx[0]; //note ptr already increments in loop, @ ']'
				for(int i = 0; i < k; i++) {
					sb.append(inner);
				}
				k = 0;
				/*
				 * 3[a], once we see '[', we need to move ptr to ']'
				 */
			}
			else if(s.charAt(ptr) == ']') {
				eidx[0] = ptr;
				return sb.toString();
			}
			else {
				sb.append(s.charAt(ptr));
			}
			ptr++;
		}
		return sb.toString();
	}
	
	@Test
	public void Test1() {
		assertEquals("aaa", decodeString("3[a]"));
	}
	@Test
	public void Test2() {
		assertEquals("aaabb", decodeString("3[a]2[b]"));
	}
	@Test
	public void Test3() {
		assertEquals("abbccabbccabbccbb", decodeString("3[a2[b]2[c]]2[b]"));
	}
}
