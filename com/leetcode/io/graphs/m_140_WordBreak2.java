package io.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

public class m_140_WordBreak2 {
	public List<String> wordBreak(String s, List<String> wordDict) {
		/**
		 * 
		 * enumerate i, until we get a match, then we have the option of adding a space
		 * w/o space + branch, could be another match later on.
		 * 
		 * How do we know if adding a space, the next char is a potential word?
		 * 
		 * c,ca,cat (at this point, we have a match, therefore we cna add a space)
		 * 
		 * [cat], sanddog
		 * cats
		 * 
		 * -> cat, (s)=> sanddog
		 * 
		 * s,sa,san,sand (m) (cat, sand) (s)=> dog
		 * 
		 * d,do,dog (m) (cat, sand, dog)
		 * 
		 */
		Set<String> dict = new HashSet<String>();
		for (String w : wordDict)
			dict.add(w);

		List<String> sentances = new ArrayList<>();
		dfs(0, s, dict, new ArrayList<>(), sentances);
		for (String sent : sentances) {
			System.out.println(sent);
		}
		return null;
	}

	void dfs(int start, String str, Set<String> dict, List<String> breaks, List<String> sentances) {
		if(start >= str.length())
			return;
		//cat,sand,ddddd
		for (int i = start; i <= str.length(); i++) {
			// if my substring from start....i is contained in dict, i
			String substr = str.substring(start, i);
			if (dict.contains(substr)) {
				breaks.add(substr);
				dfs(i, str, dict, new ArrayList<String>(breaks), sentances);
			}
		}

		if (breaks.size() > 0) {
			sentances.add(String.join(" ", breaks));
		}
	}
	
	@Test
	public void Test1() {
		System.out.println(1 << 1);
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		
		List<String> expected = new ArrayList<>();
		expected.add("cats and dog");
		expected.add("cat sand dog");
		List<String> actual = wordBreak(s, wordDict);
		assertTrue(actual.size() == expected.size());
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}
}
