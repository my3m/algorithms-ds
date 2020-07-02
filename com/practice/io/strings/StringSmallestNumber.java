package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Remove leading zero's, Find smallest length strings, & start comparing from
 * left make all strings of equal length, and sort, and return first string
 * 
 * @author owner-pc
 *
 */
public class StringSmallestNumber {
	String findSmallest(String snum1, String snum2) {

		String num1 = snum1;
		String num2 = snum2;

		int i = 0, j = 0;

		// "020", "21"
		while (i < num1.length() && num1.charAt(i) == '0') {
			i++;
			// num1 = num1.substring(1);
		}

		while (j < num2.length() && num2.charAt(j) == '0') {
			j++;
			// num2 = num2.substring(1);
		}

		int num1len = num1.length() - i;
		int num2len = num2.length() - j;

		if (num1len == num2len) {
			while (i < num1.length() && j < num2.length() && num1.charAt(i) == num2.charAt(j)) {
				i++;
				j++;
			}
			if (i < num1.length() && j < num2.length())
				return num1.charAt(i) < num2.charAt(j) ? num1 : num2;
		}
		// "020", "002"
		// 20, 2
		// 2,1
		return num1len < num2len ? num1 : num2;
	}

	@Test
	public void Test1() {
		assertEquals("20", findSmallest("021", "20"));
	}

	@Test
	public void Test2() {
		assertEquals("02", findSmallest("02", "20"));
	}

	@Test
	public void Test3() {
		assertEquals("1", findSmallest("02", "1"));
	}

	@Test
	public void Test4() {
		assertEquals("252", findSmallest("525", "252"));
	}

	@Test
	public void Test5() {
		assertEquals("444", findSmallest("444", "450")); // ddd, de
	}

	@Test
	public void Test6() {
		assertEquals("45", findSmallest("444", "45")); // ddd, de
	}

	@Test
	public void Test7() {
		assertEquals("252", findSmallest("252", "542"));
	}

	@Test
	public void Test8() {
		assertEquals("545", findSmallest("555", "545"));
	}
}
