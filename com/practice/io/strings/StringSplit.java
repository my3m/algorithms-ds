package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

/*
* Split a string into sub-strings. Each sub-string should be no longer than the given limit.
* The input string contains English letters and spaces only.
* Do not break a word into two sub strings.
* Remove all spaces in the beginning or end of every sub string.
 */
public class StringSplit {
	
	/* Split blindly into n-size chunks*/
	public static String[] split(String string, int limit) {
		int i = 0;
		int j = 0;
		int s = 0;
		char[] arr = new char[limit];
		ArrayList<String> substrings = new ArrayList<String>();
		/* this is a test
		 * this is a test
		 * this is a test
		 * this is a test
		 */
		//O(limit*n)=>O(2*n)
		while(i < string.length()) {
			if(i - s == limit - 1) {
				//sb.append(string.charAt(j));
				arr[j-s] = string.charAt(j);
				j++;
				if(j - s == limit) {
					substrings.add(new String(arr));
					//update startidx
					s = j;
					i++;
				}
			} else {
				i++;
			}
		}
		//s < s.length
		StringBuilder sb = new StringBuilder();
		for(int k = s; k < string.length(); k++) {
			sb.append(string.charAt(k));
		}		
		substrings.add(sb.toString());
		return substrings.toArray(new String[substrings.size()]);
	}
	
	/* Split words into sentance on limit length */
	public static String[] run(String string, int limit) {
		//"The quick brown fox jumps"
		int previousLengthIdx = -1;
		int startIdx = 0;
		int numChars = 0;
		HashMap<Integer, Integer> indexLengths = new HashMap<Integer, Integer>();
		ArrayList<String> substrings = new ArrayList<String>();
		//StringBuilder sb = new StringBuilder();
		//O(n)
		int i = 0;
		for(; i < string.length(); i++) 
		{
			//"The quick brown fox jumps over a lazy dog."
			if(string.charAt(i) == ' ') {
				if(numChars > 0) {
					if(i - startIdx <= limit - 1) {
						indexLengths.put(i - 1, i - startIdx);
						previousLengthIdx = i;
					}
					else {			
/*						int length = previousLengthIdx - startIdx + 1;
						char[] arr = new char[length];
						//O(limit)
						for(int z = 0; z < arr.length; z++) {
							arr[z] = string.charAt(startIdx + z);
						}
						substrings.add(new String(arr));*/
						substrings.add(getTrimmedString(string, startIdx, previousLengthIdx - 1));
						startIdx = previousLengthIdx;
						previousLengthIdx = i;
						numChars = 0;
					}
				}
			} else {
				numChars++;
			}
		}
		//+ O(limit)
		substrings.add(getTrimmedString(string, startIdx, string.length() - 1));
		return substrings.toArray(new String[substrings.size()]);
	}
	
	static String getTrimmedString(String string, int begin, int end) {
		if(string.length() == 0)
			return "";
		
		while(begin < string.length() && string.charAt(begin) == ' ')
			begin++;
		
		while(end >= begin && string.charAt(end) == ' ') 
			end--;
		
		StringBuilder sb = new StringBuilder();
		for(int i = begin; i <= end; i++) {
			if(string.charAt(i) != ' ') {
				sb.append(string.charAt(i));
			} else {
				sb.append(" ");
				int k = i + 1;
				//the    test
				while(i <= end && string.charAt(k) == ' ') 
					k++;
			}
		}
		return sb.toString();
	}
	
	void processNotations(String s, int limit, HashMap<Integer, Integer> lengths) {
		//[17, 5, 20,5
		//300
		//(1 of 30)
		//300/10
		StringBuilder sb = new StringBuilder();
		int previousIdx = 0;
		int startIdx = 0;
		int current = 1;
		for(int i = 0; i < s.length(); i++) {
			
			if(i + 1 < s.length() && s.charAt(i + 1) == ' ') {
				String notation = String.format(" (%d of %d)", current, 100);
				if(lengths.get(i) + notation.length() > limit) {
					//substring startIdx....prevIdx + notation
					startIdx = previousIdx + 1;
					
				}
				else {
					previousIdx = i;
				}
			}
		
		}
		System.out.println(sb.toString());
	}
	
	  List<String> splitString(String input, int limit) {
	        List<String> res = new ArrayList<String>();
	        int l = 0;
	        int r = input.length();
	        int mid = 0;
	        String[] para = input.split(" ");

	        while (l < r) {
	            mid = (l + r) / 2;
	            int guessRes = check(mid, limit, para);
	            if (guessRes > mid) {
	                l = mid + 1;
	            }
	            else {
	                r = mid;
	            }
	        }
	        int curLen = 0;
	        int infoLen = 0;
	        int line = 0;
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < para.length; i++) {
	            infoLen = ("(" + (line + 1) + " of " + r + ")").length();
	            curLen += para[i].length() + 1;
	            if (curLen + infoLen > limit) {
	                res.add(sb.toString() + "(" + (line + 1) + " of " + r + ")");
	                sb = new StringBuilder(para[i] + " ");
	                line++;
	                curLen = para[i].length() + 1;
	            }
	            else {
	                sb.append(para[i]).append(" ");
	            }
	        }
	        res.add(sb.toString() + "(" + (line + 1) + " of " + r + ")");
	        return res;
	    }

	    int check(int guess, int limit, String[] para) {
	        int curLen = 0;
	        int infoLen = 0;
	        int res = 0;
	        for (int i = 0; i < para.length; i++) {
	            infoLen = ("(" + (res + 1) + " of " + guess + ")").length();
	            curLen += para[i].length() + 1;
	            if (curLen + infoLen > limit) {
	                res++;
	                curLen = para[i].length() + 1;
	            }
	        }
	        return res + 1;
	    }	

	@Test
	public void Test1() {
		int limit = 39;
		String input = "1The quick brown fox jumps over a lazy dog."
				+ " 2The quick brown fox jumps over a lazy dog."
				+ " 3The quick brown fox jumps over a lazy dog."
				+ " 4The quick brown fox jumps over a lazy dog."
				+ " 5The quick brown fox jumps over a lazy dog."
				+ " 6The quick brown fox jumps over a lazy dog."
				+ " 7The quick brown fox jumps over a lazy dog."
				+ " 8The quick brown fox jumps over a lazy dog.";
		String[] s = StringSplit.run(input, limit);
		List<String> x = splitString(input, limit);
		assertEquals(input, String.join(" ", s));
	}
	
	@Test
	public void Test2() {
		int limit = 39;
		String input = "1The quick brown fox jumps over a lazy dog."
				+ " 2The quick brown fox jumps over a lazy dog."
				+ " 3The quick brown fox jumps over a lazy dog."
				+ " 4The quick brown fox jumps over a lazy dog."
				+ " 5The quick brown fox jumps over a lazy dog."
				+ " 6The quick brown fox jumps over a lazy dog."
				+ " 7The quick brown fox jumps over a lazy dog."
				+ " 8The quick brown fox jumps over a lazy dog. 9End line.";
		String[] s = StringSplit.split(input, limit);
		assertEquals(input, s);
	}
	
}
