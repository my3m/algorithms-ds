package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FindLexicographicalSmallest {
    String findsmallestLexicographicalOrder(List<String> strings) {
        if(strings.size() == 0)
            return null;
        if(strings.size() == 1)
            return strings.get(1);
        String smallest = strings.get(0);
        for(int i = 1; i < strings.size(); i++) {
            int n = 0;
            int m = 0;
            String comparing = strings.get(i);
            int len = Math.min(smallest.length(), comparing.length());
            //abcd, abc
            //abc, def
            //SFXO, SFX
            for(int j = 0; j < len; j++) {
                if(smallest.charAt(n) == comparing.charAt(m)) {
                    n++;
                    m++;
                }
            } 
            if(n < smallest.length() && m < comparing.length()) {
                smallest =  smallest.charAt(n) < comparing.charAt(m) ? smallest : comparing;
            } else {
                smallest =  smallest.length() < comparing.length() ? smallest : comparing;
            }
        }
        return smallest;
    }
    
    @Test
    public void Test1() {
    	List<String> words = Arrays.asList("ABC", "ABCD");
    	assertEquals("ABC", findsmallestLexicographicalOrder(words));
    }
    
    @Test
    public void Test2() {
    	List<String> words = Arrays.asList("SFO", "SFX");
    	assertEquals("SFO", findsmallestLexicographicalOrder(words));
    }
    
    @Test
    public void Test3() {
    	List<String> words = Arrays.asList("SFXD", "SFX");
    	assertEquals("SFX", findsmallestLexicographicalOrder(words));
    }
}
