package io.arrays;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

public class RemoveKConsecutiveCharacters {
	public String removeKConsecutiveCharacters(String s, int k) {
		LinkedList<Integer[]> s1 = new LinkedList<>();
		for(int i = 0; i < s.length(); i++) {
			if(s1.size() == 0 || s1.peek()[0] != s.charAt(i)) {
				s1.push(new Integer[] { 0 + s.charAt(i), 1 });
			}
			else {
				s1.peek()[1] = s1.peek()[1] + 1;
				while(s1.size() > 0 && s1.peek()[1] >= k) {
					s1.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(Integer[] arr : s1) {
			for(int i = 0; i < arr[1]; i++) {
				sb.append((char)arr[0].intValue());
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	@Test
	public void Test1() {
		assertEquals("C", removeKConsecutiveCharacters("CBAAABB", 3));
	}
}
