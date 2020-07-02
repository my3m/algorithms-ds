package io.patterns;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SlidingWindow {
		public static void main(String[] args) {
//		List<Integer> result = findWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
//		System.out.println(result.toString());
//
//		List<Integer> result2 = findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" });
//		System.out.println(result2.toString());
//		
//		List<Integer> result3 = findWordConcatenation("barfoothefoobarman", new String[] { "foo", "bar" });
//		System.out.println(result3.toString());
			
		String s = LongestSubstringWithoutKContigiousOccurenceLetter("zzyyxxuuuuapveee", 4);
		System.out.println(s);
		
		//Arrays
		//String s1 = LongestSubstringWithoutKContigiousOccurenceLetter("zzzz", 4);
		//System.out.println(s1);
	}
		
	public static String LongestSubstringWithoutKContigiousOccurenceLetter(String s, int k) {
		//zzyyxxuu
		//"zzyyxxuuuapveeee" -> "zzyyxxuuuapveee", k = 4
		//int[] counts = new int[26];//buffer last k chars
		//int[] counts = new int[k];
		Map<Character, Integer> map = new HashMap<>();
		int j = 0, i = 0, q = 0, l = -1, h = 0;
		for(;i< s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
			//update rolling window of previous k chars
			if(i - q + 1 > k) {
				char chr = s.charAt(q);
				map.put(chr, map.get(chr) - 1);
				if(map.get(chr) == 0)
					map.remove(chr);
				q++;
			}
			//q....i (k window chars)
			
			//validate state
			//s="xxxx", k = 4
			if(i >= k - 1 && q <= i && map.size() == 1) {
				char chr = map.keySet().iterator().next();
				while(i >= k - 1 && q <= i && map.containsKey(chr) && map.get(chr) >= k) {
					map.put(s.charAt(q), map.get(s.charAt(q)) - 1);
					if(map.get(s.charAt(q)) == 0)
						map.remove(s.charAt(q));
					q++;
				}
				j = q;
			}
			
			int len = i - j + 1;
			if(l == -1 || (len > h - l + 1)) {
				h = i;
				l = j;
			}
		}
		if(l == -1)
			return "";
		String substr = s.substring(l, ++h);
		System.out.println(substr);
		return substr;
	}
	
	public String LongestSubstringWithout3ContigiousOccurenceLetter(String s) {
		/**
			Example 1:
			
			Input: "aabbaaaaabb"
			Output: "aabbaa"
			Example 2:
			
			Input: "aabbaabbaabbaa"
			Output: "aabbaabbaabbaa"
			
			in a sliding window, how do we keep track of last three chars???
		 */	
		int[] counts = new int[26];
		int i = 0, j = 0, k = 0, l = 0, h = -1;
		for(; i < s.length(); i++) {
//			int slen = i - k + 1;
//			counts[s.charAt(i)-'a']++;
//			while(i - k + 1 > 3) {
//				counts[s.charAt(k)-'a']--;
//				k++;
//			}
//			if(slen >= 3 && lastCharsSame(counts)) {
//				//s="aaab"
//				j = k;
//				while(j <= i && lastCharsSame(counts)) {
//					counts[s.charAt(j)-'a']--;
//					j++;
//				}
//				k = j;
//			}
			
			int slen = i - j + 1;
			if(slen > 2) {
				if(s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i - 2)) {
					j = i - 1;
				}
			}
			
			int len = i - j + 1;
			if(h == -1 || len > (h-l+1)) {
				l = j;
				h = i;
			}
		}
		if(h ==-1)
			return "";
		System.out.println(s.substring(l, ++h));
		return s.substring(l, h);
	}
	
	public boolean lastCharsSame(int[] counts) {
		for(int i = 0; i < counts.length; i++) {
			if(counts[i] >= 3)
				return true;
		}
		return false;
	}
	
	public String LongestSubstringWithout3ContigiousOccurenceLetter1(String s) {
		/**
				Example 1:
				
				Input: "aabbaaaaabb"
				Output: "aabbaa"
				Example 2:
				
				Input: "aabbaabbaabbaa"
				Output: "aabbaabbaabbaa"
		 */
		
		//how to store last 3 chars in map???
		//S="aabx"
		//[a,a,b]
		
		//a,a,b,b,a,a,a
//		int[] counts = new int[26];
//		int i = 0, j = 0, l = 0, h = -1;
//		for(; i < s.length(); i++) {
//			counts[s.charAt(i)-'a']++;
//			while(j <= i && checkContigious(counts)) {
//				counts[s.charAt(j)-'a']--;
//				j++;
//			}
//			int len = i - j + 1;
//			if(h == -1 || len > (h-l+1)) {
//				l = j;
//				h = i;
//			}
//		}
//		if(h == -1)
//			return "";
//		System.out.println(s.substring(l, h));
//		return s.substring(l, h);
		
		LinkedList<Integer[]> chars = new LinkedList<>();
		String substr = "";
		for(char chr : s.toCharArray()) {
			if(chars.size() == 0 || chars.peek()[0] != (int)chr) {
				chars.push(new Integer[] { (int)chr, 1 });
			}
			else {
				//abbaabbaaabbaaa
				chars.peek()[1] += 1;
				if(chars.peek()[1] > 2) {
					//this line is important as it makes "aaa" -> "aa"
					chars.peek()[1] -= 1;
					String temp = getStackString(chars);
					if(substr == "" || temp.length() > substr.length()) {
						substr = temp;
					}
					chars.push(new Integer[] { (int)chr, 2 });
				}
			}
		}
		
		if(chars.size() > 0) {
			String temp = getStackString(chars);	
			if(substr == "" || temp.length() > substr.length()) {
				substr = temp;
			}
		}
		return substr;
	}
	
	public String getStackString(LinkedList<Integer[]> chars) {
		StringBuilder sb = new StringBuilder();
		while(chars.size() > 0) {
			Integer[] temp = chars.pop();
			for(int i = 0; i < temp[1]; i++) {
				sb.append((char)temp[0].intValue());
			}
		}
		String temp = sb.reverse().toString();
		return temp;
	}
	
	@Test
	public void LongestSubstringWithout3ContigiousOccurenceLetter_Tests() {
		assertEquals("aabbaa", LongestSubstringWithout3ContigiousOccurenceLetter("aabbaaaaabb"));
		assertEquals("aabbaabbaabbaa", LongestSubstringWithout3ContigiousOccurenceLetter("aabbaabbaabbaa"));
		assertEquals("abbaabbaa", LongestSubstringWithout3ContigiousOccurenceLetter("abbaabbaaabbaaa"));
		assertEquals("aa", LongestSubstringWithout3ContigiousOccurenceLetter("aaa"));
		assertEquals("aabba", LongestSubstringWithout3ContigiousOccurenceLetter("aaabba"));
		assertEquals("zzyyxxuu", LongestSubstringWithout3ContigiousOccurenceLetter("zzyyxxuuu"));
	}
	
	@Test
	public void LongestSubstringWithout3ContigiousOccurenceLetter1_Tests() {
		assertEquals("aabbaa", LongestSubstringWithout3ContigiousOccurenceLetter1("aabbaaaaabb"));
		assertEquals("aabbaabbaabbaa", LongestSubstringWithout3ContigiousOccurenceLetter1("aabbaabbaabbaa"));
		assertEquals("abbaabbaa", LongestSubstringWithout3ContigiousOccurenceLetter1("abbaabbaaabbaaa"));
		assertEquals("aa", LongestSubstringWithout3ContigiousOccurenceLetter1("aaa"));
		assertEquals("aabba", LongestSubstringWithout3ContigiousOccurenceLetter1("aaabba"));
	}
	
	public int SmallestSubarrayWithGivenSum(int[] arr, int k) {
		System.out.println("Test: " + Arrays.toString(arr) + ", k=" + k);
		int j = 0, sum = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			// once we meet the constraints, shrink the window as small as possible
			while (j <= i && sum >= k) {
				if (i - j + 1 < min) {
					min = Math.min(min, i - j + 1);
					System.out.println("\tFound smaller window");
					System.out.println("\t" + Arrays.toString(Arrays.copyOfRange(arr, j, i + 1)));
				}
				sum -= arr[j++];
			}
		}
		System.out.println("answer: " + min);
		return min == Integer.MAX_VALUE ? 0 : min;
	}

	@Test
	public void SmallestSubarrayWithGivenSum_Tests() {

		int[] arr1 = new int[] { 2, 1, 5, 2, 3, 2 };
		assertEquals(2, SmallestSubarrayWithGivenSum(arr1, 7));

		int[] arr2 = new int[] { 2, 1, 5, 2, 8 };
		assertEquals(1, SmallestSubarrayWithGivenSum(arr2, 7));

		int[] arr3 = new int[] { 3, 4, 1, 1, 6 };
		assertEquals(3, SmallestSubarrayWithGivenSum(arr3, 8));
	}

	public int LongestSubstringWithKDistinctCharacters(String s, int k) {
		System.out.println("Test: " + s + ", k=" + k);

		// how to optimally calculate number of distinct chars in a string
		int[] counts1 = new int[26];
		int j = 0, charsFormed = 0, l = 0, h = Integer.MAX_VALUE, max = 0;
		// s="araaci"
		for (int i = 0; i < s.length(); i++) {
			if (counts1[s.charAt(i) - 'a']++ == 0)
				charsFormed++;
			if (charsFormed <= k) {
				int len = i - j + 1;
				if (max == 0 || len > max) {
					max = len;
					l = j;
					h = i;
					System.out.println("\t s=" + s.substring(l, h + 1));
				}
			}
			while (charsFormed > k) {
				System.out.println("\t" + charsFormed + "> k distinch chats, s=" + s.substring(l, h + 1));
				if (counts1[s.charAt(j++) - 'a']-- == 1)
					charsFormed--;
			}
		}
		if (h == Integer.MAX_VALUE)
			return 0;
		System.out.println("Longest substring = " + s.substring(l, ++h));
		return max;
	}



	public static List<Integer> findWordConcatenation(String str, String[] words) {
		Map<String, Integer> wordFreqencyCounts = new HashMap<>();
		for(String word : words) {
			wordFreqencyCounts.put(word, wordFreqencyCounts.getOrDefault(word, 0) + 1);
		}
		
		//wordFrequency={"cat":1, "fox":1}
		//"aaabbb"

		
		int wordlen = words[0].length();
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i <= str.length() - wordlen*words.length; i++) {
			Map<String, Integer> wordsSeen = new HashMap<>();
			for(int j = 0; j < words.length; j++) {
				//len
				String potentialWord = str.substring(i + j*wordlen, i + j*wordlen + wordlen);
				//s="catfox"
				if(!wordFreqencyCounts.containsKey(potentialWord))
					break;
				wordsSeen.put(potentialWord, wordsSeen.getOrDefault(potentialWord, 0) + 1);
				//wordsSeen.get(potentialWord) <= wordFrequencyCounts.get(potentialWord)
				//valid state
				
				//wordsSeen="{"aaa":2}" wordsFrequency={"aaa":3}
			}
			boolean isValidIndex = true;
			if(wordsSeen.size() == wordFreqencyCounts.size()) {
				for(String s : wordFreqencyCounts.keySet()) {
					if(!wordsSeen.containsKey(s) || wordsSeen.get(s) < wordFreqencyCounts.get(s)) {
						isValidIndex = false;
						break;
					}
				}
				if(isValidIndex)
					indices.add(i);
			}
		}
		return indices;
	}

	@Test
	public void LongestSubstringWithKDistinctCharacters_Tests() {
		assertEquals(4, LongestSubstringWithKDistinctCharacters("araaci", 2));
		assertEquals(2, LongestSubstringWithKDistinctCharacters("araaci", 1));
		assertEquals(5, LongestSubstringWithKDistinctCharacters("cbbebi", 3));
	}
}
