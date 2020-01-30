package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class StringSearch {

	/*KMP Implementation*/
	private static int run(String string, String pattern) {
		if(pattern.length() > string.length())
			return 0;
		/*
		 * 1. Build our pattern table array
		 */
		int[] patternTable = buildPatternTable(pattern);
		int i = 0;
		int j = 0;
		while(i < string.length()) {
			if(string.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
				if(j == pattern.length()) {
					//this is hello 
					return i - pattern.length();
				}
			}
			else if (j > 0) {
				//reset j to prev pattern
				//aefaedaefaefa
				//0000100123423
				int temp = patternTable[j - 1];
				j = temp == 0 ? 0 : temp + 1;
			}
			else {
				i++;
			}
		}
		return -1;
	}
	
	private static int[] buildPatternTable(String pattern) {		
		int[] patternTable = new int[pattern.length()];
		int i = 1;
		int j = 0;
		while(i < pattern.length()) {
			if(pattern.charAt(i) == pattern.charAt(j)) {
				patternTable[i] = j;
				i++;
				j++;
			}
			else if (j > 0){
				int temp = patternTable[j - 1];
				j = temp == 0 ? 0 : temp + 1;
			}
			else {
				i++;
			}
		}
		return patternTable;
	}

	@Test
	public void Test1() {
		int[] pattern = StringSearch.buildPatternTable("aefaedaefaefa");
		//int f = StringSearch.run("this is a sample search string", "sample");
		System.out.println(Arrays.toString(pattern));
		int[] exp = new int[] { 0,0,0,0,1,0,0,1,2,3,4,2,3};
		assertEquals(Arrays.toString(exp), Arrays.toString(pattern));
	}
	
	@Test
	public void Test2() {
		int f = StringSearch.run("this is a sample search string", "sample");
		assertEquals(10, f);
	}	
	
	@Test
	public void Test3() {
		int f = StringSearch.run("this is a sample search string", "match");
		assertEquals(-1, f);
	}
	
	@Test
	public void Test4() {
		int f = StringSearch.run("this is a sample search string", "this");
		assertEquals(0, f);
	}	
}
