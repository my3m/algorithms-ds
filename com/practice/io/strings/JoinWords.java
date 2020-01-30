package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
/* Hard */
/* Knowing last index */
public class JoinWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		justifyText(new String[] {"Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"}, 20);
	}
	
	static void justifyText(String[] strings, int maxSize) {
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> text = new ArrayList<String>();
		for(int i =0; i < strings.length; i++) {
			/* Keep adding words to lineWords until it cannot fit anymore words "Greedy Approach" */
			boolean isFit = canFitWord(lines, strings[i], maxSize);
			if(isFit) {
				lines.add(strings[i]);
			}
			if(!isFit) {
				/* Join list strings, delmited by space rules */
				String line = merge(lines.toArray(new String[lines.size()]), maxSize);
				text.add(line);
				lines = new ArrayList<String>();
			}
			i++;
		}
	}
	
/*	static void justifyText(String[] strings, int maxSize) {
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> text = new ArrayList<String>();
		int i = 0;
		while(i < strings.length) {
			if(lines == null) lines = new ArrayList<String>();			
			 Keep adding words to lineWords until it cannot fit anymore words "Greedy Approach" 
			if(!canFitWord(lines, strings[i], maxSize)) {
				 Join list strings, delmited by space rules 
				String line = merge(lines.toArray(new String[lines.size()]), maxSize);
				text.add(line);
				lines = new ArrayList<String>();
				lines.add(strings[i]);
			} else {
				 Last index wont process "merge"
				lines.add(strings[i]);
				if(i == strings.length - 1) {
					String line = merge(lines.toArray(new String[lines.size()]), maxSize);
					text.add(line);
				}
			}
			i++;
		}
		
		for(String s : text) {
			System.out.println("\"" + s + "\"");
		}
	}*/
	
	private static boolean canFitWord(ArrayList<String> lineWords, String string, int maxSize) {
		//5
		//a b c d
		//the minimum space b/w words has to be at least 1
		int length = string.length();
		for(String s : lineWords) {
			length += s.length();
		}
		int wordCount = lineWords.size() + 1;
		return length + (wordCount - 1) <= maxSize;
	}

	static String merge(String[] strings, int maxSize) {
		if(strings.length == 0)
			return "";
		
		//O(n)
		int availableSize = maxSize - getWordsLength(strings);
		int spaces = strings.length == 1 ? 1 : strings.length - 1;
		//"word      "
		//"word another"
		//"word word word"
		int[] spacing = calculateWordSpacing(spaces, availableSize);
		//O(maxSize)
		ArrayList<Character> chars = new ArrayList<Character>();
		//O(n)
		/* Just one word */
		for(int i = 0; i < strings.length; i++) {
			if(i > 0) {
				addSpacing(chars, i + 1 == strings.length ? spacing[1] : spacing[0]);
			}
			for(int j = 0; j < strings[i].length(); j++) {
				chars.add(strings[i].charAt(j));
			}
		}
		if(strings.length == 1)
			addSpacing(chars, spacing[1]);
		char[] arr = new char[chars.size()];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = chars.get(i);
		}
		return new String(arr);
	}
	
	static void addSpacing(ArrayList<Character> chars, int spaces) {
		for(int z = 0; z < spaces; z++) {
			chars.add(' ');
		}		
	}
	
	/*
	 * Length of words + spaces between words (delmited)
	 * + We need to maximize space to left as much as possible ??
	 */
	static int getWordsLength(String[] strings) {
		int spaces = 0;
		for(String s : strings) {
			spaces += s.length();
		}
		return spaces;
		//word word word
		//alargeword
	}
	
	/*
	 * Edge case= What if spaces == 0?
	 * Only 1 word
	 */
	static int[] calculateWordSpacing(int spaces, int maxSize) {
		int mod = maxSize % spaces;
		if(mod != 0) {
			/* A greed approach is to use the lowest value possible on the right side
			 * even on the lefts
			 * Ex. 20 & 3
			 * Use 1R, 19%3 != 0, use 2R, 18%3 == 0!
			 */
			int rightSpace = 1;
			while((maxSize -rightSpace) % (spaces - 1) != 0) {
				rightSpace++;
			}
			int leftSpace = ((maxSize -rightSpace) / (spaces - 1)); 
			return new int[] { leftSpace, rightSpace };
		}
		int value = maxSize / spaces;
		return new int[] { value, value };
	}
	
	@Test
	public void Test1() {
		String[] words = new String[] 
		{
				"Science"  ,"is"  ,"what" , "we"	
		};
		int maxSize = 20;
		String s = JoinWords.merge(words, maxSize);
		System.out.printf("\"%s\"\r\n",s);
		//Science  is  what  we
		//Scienceiswhatwe
		assertEquals("Science  is  what we", s);
	}
	@Test
	public void Test2() {
		String[] words = new String[] 
		{
				"shall",         "be"
		};
		int maxSize = 16;
		String s = JoinWords.merge(words, maxSize);
		System.out.printf("\"%s\"\r\n",s);
		assertEquals("shall be        ", s);
	}	

}
