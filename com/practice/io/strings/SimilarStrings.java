package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
	Similar strings ("face", "eacf") returns true if only 2 positions
 	in the strings are swapped. Here 'f' and 'e' are swapped in the example.
 */
public class SimilarStrings {
	public boolean areSimilarStrings(String a, String b) {
		//You can't have similar strings if the lengths are not equal
		if(a.length() != b.length())
			return false;
		int len = a.length();
		int diffIdx = 0;
		int[] diffIndices = new int[2];
		for(int i = 0; i < len; i++) {
			if(a.charAt(i) != b.charAt(i)) {
				if(diffIdx > 1)
					return false;
				diffIndices[diffIdx++] = i;
			}
		}
		return diffIdx == 0 || a.charAt(diffIndices[0]) == b.charAt(diffIndices[1]) &&
				a.charAt(diffIndices[1]) == b.charAt(diffIndices[0]);
	}
	
	@Test
	//case when string differs by one mismatch
	public void Test1() {
		assertEquals(true, areSimilarStrings("face", "eacf"));
		assertEquals(true, areSimilarStrings("face", "fcae"));
	}
	@Test
	//case when string differs by two mismatch
	public void Test2() {
		assertEquals(false, areSimilarStrings("face", "ecaf"));
	}
	@Test
	//case when string differs by two mismatch
	public void Test3() {
		assertEquals(false, areSimilarStrings("face", "faces"));
	}
	
	@Test
	//Case when strings are equal
	public void Test4() {
		assertEquals(true, areSimilarStrings("face", "face"));
	}
}
