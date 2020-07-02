package io.primitives;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckIfPowerOf2 {
	public boolean isPowerOf2(int num) {
		return isPowerOf2Bit(num);
	}
	
	public boolean isPowerOf2Log(int num) {
		//1,2,4,6,8,16,32,64,128,256,512,1024
		if(num == 1)
			return true;
		//2^k=n
		//logn=klog2//calculate log2Num indirectly
		System.out.println(Math.ceil(7.00));
		int power = (int)Math.ceil(Math.log(num)/Math.log(2));
		System.out.println(Math.pow(2, power));
		return num == Math.pow(2, power);
		//double x = Math.log(num)/Math.log(2);
		//return x % 1 == 0;		
	}
	
	public boolean isPowerOf2Div(int num) {
		//1,2,4,6,8,16,32,64,128,256,512,1024
		if(num == 1)
			return true;
		//till 2^0 == 1
		while(num != 1) {
			if(num%2 != 0)//129/2=>64
				return false;
			num/=2;
		}
		return num == 1;
	}
	
	//01
	//10
	//0010000
	//0001111
	public boolean isPowerOf2Bit(int num) {
//		System.out.println(num);
//		System.out.println(Integer.toBinaryString(2));
//		System.out.println(Integer.toBinaryString(1));
//		System.out.println(Integer.toBinaryString(num - 1));
//		System.out.println(Integer.toBinaryString(num ^ (num - 1)));
//		System.out.println((num ^ (num - 1)) == num + num - 1);
		return (num & (num -1)) == 0 && num != 0;
		//return (num ^ (num - 1)) == num + num - 1;
	}
	
	@Test
	public void Test1() {
		assertEquals(false, isPowerOf2(0));
		assertEquals(true, isPowerOf2(1));
		assertEquals(true, isPowerOf2(2));
		assertEquals(true, isPowerOf2(4));
		assertEquals(true, isPowerOf2(8));
		assertEquals(true, isPowerOf2(16));
		assertEquals(true, isPowerOf2(32));
		assertEquals(true, isPowerOf2(64));
		assertEquals(true, isPowerOf2(128));
		assertEquals(false, isPowerOf2(1064));
		assertEquals(true, isPowerOf2(2048));
		assertEquals(false, isPowerOf2(1600));
	}
	
	@Test
	public void Test2() {
		assertEquals(false, isPowerOf2(129));
	}
}
