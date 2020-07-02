package io.playground;

import java.util.ArrayList;
import java.util.List;

public class Strings {
	public static void main(String[] args) {
		// System.out.println(longestSubstringWithoutRepeatingCharacters("abcabcbb"));
		// System.out.println(backspaceCompare("y#fo##f"));
		// System.out.println(backspaceCompare2("y#fo##f", "y#f#o##f"));
		// System.out.println(backspaceCompare2("nzp#o#g", "b#nzp#o#g"));
		// System.out.println(backspaceCompare2("bxj##tw", "bxj###tw"));
		generatePermutations("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".toCharArray(), new StringBuilder());
		// addPermutations("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "");
		System.out.println(permuations.toString());

		// abef
		// aebf
		// afbe
	}

	// ['a','a','a','b','e','f']
	//
	static List<String> permuations = new ArrayList<>();

	//duplicate search paths
	static void addPermutations(String suffix, String prefix) {
		if (suffix.length() == 0) {
			// System.out.println(prefix);
			permuations.add(prefix);
		}
		for (int i = 0; i < suffix.length(); i++) {
			String nextSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
			addPermutations(nextSuffix, prefix + suffix.charAt(i));
		}
	}

	static void generatePermutations(char[] array, StringBuilder sb) {
		if (sb.length() == array.length) {
			permuations.add(sb.toString());
			return;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '-')
				continue;
			if (i > 0 && array[i] == array[i - 1])
				continue;
			char chr = array[i];
			array[i] = '-';
			generatePermutations(array, sb.append(chr));
			sb.deleteCharAt(sb.length() - 1);
			array[i] = chr;
		}
	}

	/** Good for mind, flow practice */
	static boolean backspaceCompare2(String S, String T) {
		int i = S.length() - 1, j = T.length() - 1;
		int skipsI = 0, skipsJ = 0;
		while (i > -1 || j > -1) {
			// Equal chars
			while (i > -1 && S.charAt(i) == '#') {
				while (i >= 0 && S.charAt(i) == '#') {
					i--;
					skipsI++;
				}
				while (i >= 0 && skipsI > 0 && S.charAt(i) != '#') {
					skipsI--;
					i--;
				}
				// "y#fo##f"
				// "y#f#o##f"

				// "ABC#"
			}

			while (j > -1 && T.charAt(j) == '#') {
				while (j > -1 && T.charAt(j) == '#') {
					j--;
					skipsJ++;
				}
				while (j > -1 && skipsJ > 0 && T.charAt(j) != '#') {
					skipsJ--;
					j--;
				}
			}

			if (i > -1 && j > -1 && S.charAt(i) != '#' && T.charAt(j) != '#') {
				if (S.charAt(i) != T.charAt(j))
					return false;
				i--;
				j--;
			}
		}
//        while(i > -1 && j > -1) {
//        	if(S.charAt(i) != T.charAt(j))
//        		return false;
//        	i--;
//        	j--;
//        }
		return i == j;
		// return i <=0 && j <= 0;
	}

	static String backspaceCompare(String S) {
		char[] arr = S.toCharArray();
		StringBuilder sb = new StringBuilder();
		int i = arr.length - 1;
		while (i > -1) {
			int skips = 0;
			while (arr[i] == '#') {
				while (arr[i] == '#') {
					skips++;
					i--;
				}
				while (skips-- > 0 && arr[i] != '#')
					i--;
			}
			// ab#d
			//

			if (i >= 0)
				sb.append(arr[i--]);
		}
		String x = sb.reverse().toString();
		System.out.println(x);
		return x;
	}

	/** O(n^3) **/
	static String longestSubstringWithoutRepeatingCharacters(String s) {
		String max = "";
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				String substring = s.substring(i, j);
				int[] chars = new int[26];
				boolean isRepeating = false;
				for (char c : substring.toCharArray()) {
					chars[c - 'a']++;
					if (chars[c - 'a'] > 1) {
						isRepeating = true;
						break;
					}
				}
				if (!isRepeating) {
					if (substring.length() > max.length()) {
						max = substring;
					}
				}
			}
		}
		return max;
	}
}
