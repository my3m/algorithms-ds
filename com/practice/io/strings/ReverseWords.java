package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseWords {
	@Test
	public void Test1() {
		String[] v1s = "32".split("\\.");
		char[] arr = "12345678".toCharArray();
		String r = ReverseWords.reverse(arr, 0, arr.length - 1);
		assertEquals("87654321", r);
	}
	
	@Test
	public void Test2() {
		char[] arr = "12345678".toCharArray();
		String r = ReverseWords.reverse(arr, 0, 3);
		assertEquals("43215678", r);
	}	
	@Test
	public void Test3() {
		char[] arr = "12345678".toCharArray();
		String r = ReverseWords.reverse(arr, 0, 0);
		assertEquals("12345678", r);
	}	
	@Test
	public void Test4() {
		String r = ReverseWords.run("a simple test          ");
		assertEquals("test simple a", r);
	}
	@Test
	public void Test5() {
		String r = ReverseWords.run("reversed");
		assertEquals("reversed", r);
	}
	@Test
	public void Test6() {
		String r = ReverseWords.run("Eclipse is using jre 7");
		assertEquals("7 jre using is Eclipse", r);
	}
	@Test
	public void Test7() {
		String r = ReverseWords.run("   hello    world");
		System.out.printf("\"%s\"", r);
		assertEquals("world hello", r);
	}
	@Test
	public void Test8() {
		String r = ReverseWords.trimSpaces("   hello    world");
		System.out.printf("\"%s\"", reverse(r.toCharArray(), 0, r.length() - 1));
		assertEquals("      hello world", r);
	}
	@Test
	public void Test9() {
		String r = ReverseWords.trimSpaces("hello world");
		System.out.printf("\"%s\"", reverse(r.toCharArray(), 0, r.length() - 1));
		assertEquals("hello world", r);
	}		
	
	/* Right to left trim */
	static String trimSpaces(String input) {
		if(input.length() == 0)
			return "";
		char[] arr = input.toCharArray();
		int j = input.length();
		int i = j - 1;
		//"hello  world"
		while(i > -1) {
			if(arr[i] != ' ') {
				--j;
				swap(arr, i, j);
				if(i - 1 > -1 && arr[i - 1] == ' ') {
					j--;
				}
			}
			i--;
		}
		return new String(arr);
	}
	
	static void main(String[] args) {
		run("a simple test");
	}
	
	private static String run(String string) {
		/* First we reverse the entire string
		 * Then reverse each word
		 */
		if(string.length() <= 1)
			return string;
		
		String reversed = reverse(string.toCharArray(), 0, string.length() - 1);
		reversed = trimSpaces(reversed);
		char[] arr = reversed.toCharArray();
		
		int i = 0;
		int j = 0;
		while(i < arr.length) {
			if(arr[i] == ' ') {
				reverse(arr, j, i - 1);
				j = i;
				while(j < arr.length && arr[j] == ' ') 
					j++;
				i = j;
			}//reversed:we reached end of string
			else if(i == arr.length - 1) {
				reverse(arr, j, i);
			}
			i++;
		}
		int k = 0;
		while(k < arr.length && arr[k] == ' ')
			k++;
		//"    test"
		char[] answer = new char[arr.length - k];
		for(int z = 0; z < answer.length; z++) {
			answer[z] = arr[k++];
		}
		//System.out.printf("\"%s\"", new String(answer));
		return new String(answer);
	}

	private static String reverse(char[] arr, int i, int j) {
		while(i < j) {
			swap(arr, i++, j--);
		}
		return new String(arr);
	}

	private static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
