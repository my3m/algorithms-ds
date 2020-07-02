package io.recursion;

/**
	Given two strings A and B we need to convert the string B to the string A. We can perform the following operation. We can move any character from B and move it to the front of B.
	Input:
	A = ‘abcd’
	B = ‘bdac’
	
	Output:
	3
	because
	bdac -> cbda -> bcda -> abcd (3 ops) 	
 */
public class MinimumOperations {
	public int findMinimum(String str1, String str2) {
		int c_idx = str1.length() - 1;
		char current = str1.charAt(c_idx);
		
		//find out how many chars come after current in str2
		return 0;
	}
}
