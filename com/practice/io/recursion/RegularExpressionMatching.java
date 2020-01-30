package io.recursion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 Case1: (a-z), s[i] == p[j], i + 1, j + 1
 Case2: p[j] = '.', i+1, j+1
 Case2: p[j..j+1] = '.*', 'a*', s="", p="c*" ,
 								s="aaab", p="a*b",
 								s="baaaa", p="ba*",
 								s="b", p="a*b",
 								s="rndmb", p=".*b"
 								s="aaaaaaaaaaaaaa", p="a*"
 		1. Use 1 char, i + 1, j does not move
 		2. Use 0 char, i, j + 2
 
 
 
 */
public class RegularExpressionMatching {

	public boolean isMatch(String s, String p) {
		return isMatchHelper(s, p, 0, 0);
	}

	public boolean isMatchHelper(String s, String p, int i, int j) {
		
		if (j == p.length()) {
			if (i == s.length())
				return true;
			else
				return false;
		}

		//s='aa', p='a*'
		/*	s='aa', p='a*', i=0, j=2 (skip 0 chars *)
		 	
		 	s='aa', p='a*', i=0, j=0, (keep incrementing i, *)
		 	s='aa', p='a*', i=1, j=0,
		 	s='aa', p='a*', i=2, j=0
		 	
		 	s='abcd', p='abcd*'
		 		a
		 			->b
		 				->c
		 					->	(dont use d*, reached end of p)
		 					->d	(use d*, reached end of s, end of p)
		 							-> (dont use, end of p)
		 	Problem of adding i < s.length && j < p.length
		 	Say you are s='aa', p='a*', 
		 				s='', i=0, (end of p), j=2
		 				  'a', i=1, j=0
		 				  	-> 'aa', i=2, j=0   
		 				
		 					
		 */
		
		//i=2
		boolean chrmatch = false;
		if (i < s.length() && j < p.length()) {
			if(p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
				chrmatch = true;
			}
		}
				
		if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			// Case 1 ignore matching first char + use first char, move i + 1
			return isMatchHelper(s, p, i, j + 2) 
					||
					chrmatch && isMatchHelper(s, p, i + 1, j);
		} else {
			//s='ab[v]a', p='ab[v]g', s[i] == p[j], move ptr by 1
			if(chrmatch) return isMatchHelper(s, p, i + 1, j + 1);
			else return false;
		}
	}

/*	public boolean isMatch(String s, String p) {
		boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
		return isMatchHelper(s, p, 0, 0, memo);
	}

	public boolean isMatchHelper(String s, String p, int i, int j, boolean[][] memo) {

		if (i == s.length())
			return false;
		if (j == p.length())
			return false;

		if (j == p.length()) {
			if (i == s.length())
				return true;
			else
				return false;
		}

		if (memo[i][j])
			return true;

		// s='aa', p='a*' //s="aa", p="a" //s="ab", p=".*c"

		// Case 0: Matching first character // s.charAt(i) == p.charAt(j) || p.charAt(j)
		// == '.')
		boolean currmatch = false;
		if (i < s.length() && j < p.length()) {
			if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
				currmatch = true;
			}
		}
		// return isMatch(s, p, i + 1, j + 1); } }

		// Case 1: a*, .* (Use char 0 or more times) 0 chars: 1 or more chars:

		// ab // .*c

		if (j + 1 < p.length() && p.charAt(j + 1) == '*') { 
			// use 0 times (.*)
			memo[i][j] = isMatchHelper(s, p, i, j + 2, memo) || 
					// use more than 1 chars
					currmatch && isMatchHelper(s, p, i + 1, j, memo);
			return memo[i][j];
		} else {
			// s="abc", p="abx"
			memo[i][j] = currmatch && isMatchHelper(s, p, i + 1, j + 1, memo);
			return memo[i][j];
		}
	}*/

	@Test
	public void Test1() {
		assertEquals(false, isMatch("ab", "a"));
	}

	@Test
	public void Test2() {
		assertEquals(false, isMatch("ab", "ab.*c"));
	}

	@Test
	public void Test3() {
		assertEquals(false, isMatch("bsc", "ab.*c"));
	}

	@Test
	public void Test4() {
		assertEquals(true, isMatch("abyyyynpppuuuc", "ab.*n.*c"));
	}

	@Test
	public void Test5() {
		assertEquals(true, isMatch("abnc", "ab.*n.*c"));
	}

	@Test
	public void Test6() {
		assertEquals(false, isMatch("abncy", "ab.*n.*c"));
	}

	@Test
	public void Test7() {
		assertEquals(true, isMatch("abncy", "ab..."));
	}

	@Test
	public void Test8() {
		assertEquals(true, isMatch("abn", "ab.*n.*c*"));
	}

	@Test
	public void Test9() {
		assertEquals(true, isMatch("abnccccccccccccccc", "ab.*n.*c*"));
	}

	@Test
	public void Test10() {
		assertEquals(true, isMatch("aa", "a*"));
	}

	@Test
	public void Test11() {
		assertEquals(true, isMatch("", "a*"));
	}
}
