package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StringJoin {
	@Test
	public void Test1() {
		List<String> words = Arrays.asList(
			"this", "is", "sample", "list", "of", "words"
		);
		assertEquals("this is sample list of words", StringJoin.join(words," "));
	}
	@Test
	public void Test2() {
		List<String> words = Arrays.asList(
			"this", "is", "sample", "list", "of", "words"
		);
		assertEquals("thisissamplelistofwords", StringJoin.join(words,""));
	}	
	@Test
	public void Test3() {
		List<String> words = Arrays.asList(
			"this"
		);
		assertEquals("this", StringJoin.join(words,""));
	}	

	private static String join(List<String> words, String delmiter) {
		if(words.size() == 0)
			return "";
		else if(words.size() == 1)
			return words.get(0);
		ArrayList<Character> chars = new ArrayList<Character>();
		for(int i = 0; i < words.size(); i++) {
			add(chars, i == 0 ? "" : delmiter, words.get(i));
		}
		char[] arr = new char[chars.size()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = chars.get(i);
		}
		return new String(arr);
	}
	
	static void add(List<Character> chars, String delimiter, String word) {
		for(char c : delimiter.toCharArray()) {
			chars.add(c);
		}
		for(char c : word.toCharArray()) {
			chars.add(c);
		}
	}
}
