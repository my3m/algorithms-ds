package io.recursion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegularExpressionMatching2 {
	public boolean isMatch(String s, String p) {
		return isMatchHelper(s, p, 0, 0);
	}
	
	public boolean isMatchHelper(String s, String p, int i, int j) {
		/*
			'?' matches any single char
			'*' matches a sequence of chars including empty string
			
			s='aa', p='*'
				s='a', j=0
					->'a', j=0
					
						-> (reached end of s, curr match false)
						-> j=1, (leave s as is, empty string case, j+1)
						
			s='adceb', p='*a*b'
			
				s='', j=1
					->s='a', j=1
						->'d', j=2
							->'c', j=2
								-> 'e', j=2
									-> 'b', i=4, j=3 (empty string case)
										-
									
				
		 */	
		
		//reach end of s
			//not p
		//reached end of p
			//not s
		
		if(j >= p.length()) {
			if(i < s.length())
				return false;
			else if(i == s.length())
				return true;
		}
		else if(j < p.length()) {
			if(i > s.length())
				return false;
		}
		
		
		boolean currchrmatch = false;
		if(i < s.length() && j < p.length()) {
			currchrmatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '?';
		}
		
		if(p.charAt(j) == '*') {
			return isMatchHelper(s, p, i + 1, j) || isMatchHelper(s, p, i, j + 1);
		} else {
			if(currchrmatch) return isMatchHelper(s, p, i + 1, j + 1);
			else 
				return false;
		}
	}
	
	@Test
	public void Test1() {
		assertEquals(false, isMatch("aa", "a"));
		assertEquals(true, isMatch("aa", "aa"));
		assertEquals(true, isMatch("aa", "*"));
	}
	
	@Test
	public void Test2() {
		assertEquals(false, isMatch("acdcb", "a*c?b"));
	}
	
	@Test
	public void Test3() {
		assertEquals(true, isMatch("acdcb", "?????"));
		assertEquals(true, isMatch("acdcb", "*"));
		assertEquals(true, isMatch("acdcb", "?*"));
		assertEquals(true, isMatch("acdcb", "??*"));
		assertEquals(true, isMatch("acdcb", "???*"));
		assertEquals(true, isMatch("acdcb", "????*"));
		assertEquals(true, isMatch("acdcb", "?????*"));
	}
	
	@Test
	public void Test4() {
		assertEquals(true, isMatch("aa", "aa*"));
	}
	
	//Recursive solution does not work for long string/patterns
	@Test
	public void Test5() {
		assertEquals(true, isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
		"*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
	}
}
