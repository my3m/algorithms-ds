package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class m_44_WildCardMatching {
	
	/***
	 * 
		!!!IDEA
		if we reach an *, we can advance index i @ s by one
			* also means zero or more, which means we can stay at i @ move index j @ p
		
			s="aa" (reach s.length()
			p="*"
			
			s="aa" (reached p.length() but not s.length()
			p="a"
			s="aaa"	(reach s.length() but < p.length();
			p="aaaaaa"
	 */
	public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length() + 1][p.length()+1];
        for(int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], -1);
		return isMatchHelper(s, p, 0, 0, memo);
	}
	
	
	public boolean isMatchHelper(String s, String p, int i, int j, int[][] memo) {
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
		
		if(i > s.length())
        {
            //memo[i][j] = 0;
            return false;
        }
		
		if(j == p.length()) {
			if(i == s.length()) {
                memo[i][j] = 1;
				return true;
            }
			else {
                memo[i][j] = 0;
				return false;
            }
		}
        
        if(memo[i][j] != -1)
            return memo[i][j] == 1 ? true : false;
		
		boolean currchrmatch = false;
		if(i < s.length() && j < p.length()) {
			currchrmatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '?';
		}
		
		if(p.charAt(j) == '*') {
			return isMatchHelper(s, p, i + 1, j, memo) || isMatchHelper(s, p, i, j + 1, memo);
		} else {
			if(currchrmatch) return isMatchHelper(s, p, i + 1, j + 1, memo);
			else { 
                memo[i][j] = 0;
				return false;
            }
		}
	}
	
	@Test
	public void Test1() {
		assertEquals(true, isMatch("aa", "*"));
	}
}
