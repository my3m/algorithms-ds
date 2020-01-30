package io.primitives;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseInteger {
	/*
	 * if during reversal, the result overflows -/=, return 0
	 * Time=O(log(x)), base10
	 */
	public int reverseInt(int x) {
		//321 => 123
		//0*10 + 1 => 1
		//1*10 + 2 => 12
		//12*10 + 3 => 123
		//during this operation, check for an overflow
		//overflow result, reverse-calculate != prev result
		int reversed = 0;
		while(x != 0) {
			int tail = x % 10;
			int nResult = reversed * 10 + tail;
			//check for overflow
			if(((nResult-tail)/10) != reversed)
				return 0;
			x/=10;
			reversed = nResult;
		}
		return reversed;
	}
	
	@Test
	public void Test1() {
		assertEquals(123, reverseInt(321));
	}
	@Test
	public void Test2() {
		assertEquals(0, reverseInt(0));
	}
	@Test
	public void Test3() {
		assertEquals(1, reverseInt(100));
	}
	@Test
	public void Test4() {
		assertEquals(290342043, reverseInt(340243092));
	}
	@Test
	public void Test5() {
		assertEquals(0, reverseInt(Integer.MAX_VALUE));
	}
	@Test
	public void Test6() {
		assertEquals(0, reverseInt(Integer.MIN_VALUE));
	}
}
