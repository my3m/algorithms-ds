package io.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

public class m_140_WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        /**
        
        enumerate i, until we get a match, then we have the option of adding a space 
        w/o space + branch, could be another match later on. 
        
        How do we know if adding a space, the next char is a potential word?
        
        c,ca,cat (at this point, we have a match, therefore we cna add a space)
        
        -> (cat),sandd
        -> (cat,sand)
        -> cats,
        (s)=> sanddog
        
        s,sa,san,sand (m)   (cat, sand)
        (s)=> dog
        
        d,do,dog (m)    (cat, sand, dog)
        
        */
        Set<String> dict = new HashSet<String>();
        for(String w : wordDict) 
            dict.add(w);
        List<String> ss = dfs(0, s, dict);
        for(String sn : ss) {
        	System.out.println(sn);
        }
        return ss;
    }
    

    List<String> dfs(int start, String str, Set<String> dict) {
        List<String> res = new ArrayList<>();
    	if(start == str.length()) {
    		res.add("");
        }
        
        for(int i = start; i <= str.length(); i++) {
            //is substr a prefix of str????
            String substr = str.substring(start, i);
            if(dict.contains(substr)) {
                List<String> list = dfs(i, str, dict);
                for(String s : list) {
                	res.add(substr +  (s == "" ? "" : " ") + s);
                }
            }
        }
        return res;
    }
	
	@Test
	public void Test1() {
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		
		List<String> expected = new ArrayList<>();
		expected.add("cat sand dog");
		expected.add("cats and dog");
		List<String> actual = wordBreak(s, wordDict);
		assertTrue(actual.size() == expected.size());
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}
}
