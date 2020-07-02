package com.interviewcake.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindRotationPoint {
	public int findRotationPoint(String[] words) {
		int l = 0, r = words.length -1;
		while(l <= r) {
			int m = l + (r-l)/2;
			if(m > 0 && words[m-1].compareTo(words[m]) > 0) {
				return m; 
			}
			else {
				//idea go to the unsorted site
				if(words[l].compareTo(words[m]) > 0) {
					//left side unsorted
					r = m - 1;
				}
				else if(words[m].compareTo(words[r]) > 0) {
					//right side unsorted
					l = m + 1;
				}
				else {
					//all sorted, just move right to left
					r = m - 1;
				}
			}
		}
		return l;
	}

	@Test
	public void HappyTestCase() {
		String[] words = new String[] { "mango", "passion fruit", "pear", "tango", "apple", "banana", "grape",
				"honeymelon" };
		assertEquals(4, findRotationPoint(words));
	}
	
	@Test
	public void AnotherHappyTestCase() {
		String[] words = new String[] { "honeymelon", "kiwi", "mango", "melon", "passion fruit", "pear", "tango", "apple", "banana", "grape", "grapefruit",
				"honeymelon" };
		assertEquals(7, findRotationPoint(words));
	}
	
	@Test
	public void OffByOne() {
		String[] words = new String[] { "pear", "apple", "banana", "cherry", "mango", "orange", };
		assertEquals(1, findRotationPoint(words));
	}
	
	@Test
	public void OneWord() {
		String[] words = new String[] { "pear" };
		assertEquals(0, findRotationPoint(words));
	}
	
	@Test
	public void TwoWord() {
		String[] words = new String[] { "pear", "apple" };
		assertEquals(1, findRotationPoint(words));
	}
	
	
	@Test
	public void AlreadySortedTestCase() {
		String[] words = new String[] { "apple", "banana", "cherry", "mango", "orange", "pear" };
		assertEquals(0, findRotationPoint(words));
	}
	
	@Test
	public void DuplicateTestCase() {
		String[] words = new String[] { "apple", "apple", "apple", "apple", "apple", "apple" };
		assertEquals(0, findRotationPoint(words));
	}
}
