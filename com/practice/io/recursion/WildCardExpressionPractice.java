package io.recursion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WildCardExpressionPractice {
	/**
	 		? means skip character,
	 		* means matching any sequence of chars (including empty)
		
			s="aaabc"			s="abcxfe"
			p="a*c				p="abc?fe"
			
			s="abcdebc"			s="abc"
			p="*bc"				p="abc"
			
	 */
	public boolean isMatch(String s, String p) {
		/**
		 	Conceptual Overview:
		 		? or char match: s.charAt(s_idx) == p.charAt(p_idx) || p.charAt(p_idx) == '?'
		 	
		 		Asterik(*): matches any sequence of chars (including empty)
		 			Store index of previous asterik(*) * perform all possibilities
		 			0,1,2,3,4,5,6,7,8
		 			0 means increment p_idx by 1
		 			1 means increment p_idx by 2
		 		
		 */
		
		/**
		 	s="", p="*"
		 	s="aa", p="a*"
		 	s="abc", p="abc*?"
		 */
		int s_idx = 0, p_idx = 0, s_tmp_idx = 0, p_star_idx = -1;
		while(s_idx < s.length()) {
			char chr = s.charAt(s_idx);
			
			if(p_idx < p.length() && (chr == p.charAt(p_idx) || p.charAt(p_idx) == '?'))
			{
				s_idx++;
				p_idx++;
			}
			else if(p_idx < p.length() && p.charAt(p_idx) == '*') {
				p_star_idx = p_idx;
				p_idx++; //first case, 0 seq, advance p_idx by 1
				s_tmp_idx = s_idx;
			}
			else if(p_star_idx != -1) {
				//We didn't match, now lets try seq 1,2,3,4,5,6
				p_idx = p_star_idx + 1;
				s_idx = s_tmp_idx + 1;
				s_tmp_idx++;
			}
			else {
				return false;
			}
		}
		for(int i = p_idx; i < p.length(); i++, p_idx++)
			if(p.charAt(i) != '*')
				return false;
		return s_idx == s.length() && p_idx == p.length();
	}
	
	@Test
	public void Test1() {
		assertEquals(true, isMatch("aa", "*"));
	}
	
	@Test
	public void Test2() {
		assertEquals(false, isMatch("aa", "*b"));
	}
	
	@Test
	public void Test3() {
		assertEquals(true, isMatch("fujeabcrxy", "f*abcr*x?"));
	}
	
	@Test
	public void Test4() {
		assertEquals(true, isMatch("adcbdk", "*a*b?k"));
	}
	
	/**
			Caused TLE, case where we don't have a match, and no prev_star_idx
	 */
	@Test
	public void Test5() {
		assertEquals(false, isMatch("cb", "?a"));
	}
	
	/**
	 	Broke test case
	 */
	@Test
	public void Test6() {
		assertEquals(false, isMatch("acdcb", "a*c?b"));
	}
	
	/**
 	Broke test case
	*/
	@Test
	public void Test7() {
		assertEquals(false, isMatch("aa", "a"));
	}
	
	@Test
	public void Test8() {
		//m??*ss*?i*pi
		//mississippi
		assertEquals(false, isMatch("mississippi", "m??*ss*?i*pi"));
	}
	
	@Test
	public void Test9() {
		assertEquals(true, isMatch("", "*"));
	}
}
