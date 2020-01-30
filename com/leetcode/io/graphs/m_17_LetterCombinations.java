package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class m_17_LetterCombinations {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> lettermap = new HashMap<Character, String>();
        lettermap.put('0', "");
        lettermap.put('1', "");
        lettermap.put('2', "abc");
        lettermap.put('3', "def");
        lettermap.put('4', "ghi");
        lettermap.put('5', "jkl");
        lettermap.put('6', "mno");
        lettermap.put('7', "pqrs");
        lettermap.put('8', "tuv");
        lettermap.put('9', "wxyz");
        
        List<String> combinations = new ArrayList<String>();
        dfs(combinations, lettermap, digits, 0, "");
        return combinations;
    }
    
    void dfs(List<String> combinations, Map<Character, String> lettermap, String digits, int index, String current) {
        if(current.length() == digits.length()) {
            combinations.add(current);
            return;
        }
        
//        for(int i = index; i < digits.length(); i++) {
            String map = lettermap.get(digits.charAt(index));
            for(int j = 0; j < map.length(); j++) {
                dfs(combinations, lettermap, digits, index + 1, current + map.charAt(j));
            }
//        }
    }
    
    @Test
    public void Test1() {
    	List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
    	assertEquals(expected, letterCombinations("23"));
    	
    }
}
