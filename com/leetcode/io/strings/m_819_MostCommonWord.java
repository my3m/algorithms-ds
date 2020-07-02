package io.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class m_819_MostCommonWord {
	
    public String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for(String s : banned) {
            set.add(s);
        }
        String key = "";
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(char chr : paragraph.toCharArray()) {
            if(Character.isLetter(chr)) {
                sb.append(Character.toLowerCase(chr));
            }
            else { //space or punctuation
                String word = sb.toString();
                if(word.length() > 0) {
                    if(!set.contains(word)) {
                        map.put(word, map.getOrDefault(word, 0) + 1);                    
                        if(key.length() == 0) key = word;
                        else if(map.get(word) > map.get(key)) {
                            key = word;
                        }
                    }
                    sb.setLength(0);
                }
            }
        }
        if(sb.length() > 0) {
            String word = sb.toString();
            if(!set.contains(sb.toString()))
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);      
            if(key.length() == 0) key = word;
            else if(map.get(word) > map.get(key)) {
                key = word;
            }            
        }
        return key;
    }	
	
    public String mostCommonWord(String paragraph, String[] banned) {       
        int currIdx = 0, beginIdx = 0, max = 0;
        Map<String, Integer> frq = new HashMap<String, Integer>();
        while(currIdx < paragraph.length() && paragraph.charAt(currIdx) == ' ')
            currIdx++;
        
        beginIdx = currIdx;
        while(currIdx < paragraph.length()) {
            char chr = paragraph.charAt(currIdx);
            if(chr == ' ' || isPunctuation(chr)) {
                String word = paragraph.substring(beginIdx, currIdx).toLowerCase();
                frq.put(word, frq.getOrDefault(word, 0) + 1);
                while(currIdx < paragraph.length() && 
                      (paragraph.charAt(currIdx) == ' ' || isPunctuation(paragraph.charAt(currIdx)))
                     ) {
                    currIdx++;
                }
                beginIdx = currIdx;
                currIdx--;
            }
            currIdx++;
        }
               
        if(beginIdx != paragraph.length()) {
            String word = paragraph.substring(beginIdx, paragraph.length()).toLowerCase();
            frq.put(word, frq.getOrDefault(word, 0) + 1);            
        }
        
        for(String s : banned) {
            if(frq.containsKey(s))
                frq.put(s, 0);
        }
        
        String commonWord = "";
        for(String s : frq.keySet()) {
            if(frq.get(s) > max) {
                max = frq.get(s);
                commonWord = s;
            }
        }
        return commonWord;
    }
                       
    boolean isPunctuation(char chr) {
        return chr == ',' || chr == '.' || chr == '?' || chr == '!' || chr == ';' || chr == '\'';
    }   
}
