package io.primitives;

import org.junit.Test;

public class GetDigitsFromInteger {
	public void getDigits(int number) {
		/**
		 
		 123 % 10 => 3
		 123 / 10 => 12
		 
		 12 % 10 => 2
		 12 / 10 = 1
		 
		 1 % 10 => 1
		 1 / 10 => 0
		 
		 */
				
		int sum = 0;
		while(number != 0) {
			int mod = number % 10;
			System.out.println(mod);
			sum+= (mod * mod);
			number = number / 10;
		}
		System.out.println(sum);
	}
	
	@Test
	public void Test1() {
		getDigits(100);
	}
}
