package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

/*
 * Didn't need to create a HashSet<String> for the mapping, just use ArrayList<String>
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

	For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
	
	Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
	
	Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
	
	Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
	
	Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
	
	Note:
	
	The length of words1 and words2 will not exceed 1000.
	The length of pairs will not exceed 2000.
	The length of each pairs[i] will be 2.
	The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 * 
 * 
 */
public class m_737_SentanceSimilarityII {
	    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
	        /*
	            1. We know if two sentances are similar, if there exists
	            a word transition from one pair to another.
	            2. A word.equals(word) is always similar
	            3. Same number of words

				"Connected components"
		        great->["fine"]
		        fine->["great"]
		        drama->["acting"]
		        acting->["drama"]
		        skills->["talent"]
		        talent->["skills"]	            
	            
	        */
	        if(words1.length != words2.length)
	            return false;
	        int n = words1.length;
	        HashMap<String, HashSet<String>> wordMap = new HashMap<String, HashSet<String>>();
	        if(pairs.size() > 0) {
	            for(List<String> s : pairs) {
	                if(!wordMap.containsKey(s.get(0))) {
	                    wordMap.put(s.get(0), new HashSet<String>());
	                }
	                if(!wordMap.containsKey(s.get(1))) {
	                    wordMap.put(s.get(1), new HashSet<String>());
	                }     
	                
	                HashSet<String> p1 = wordMap.get(s.get(0));
	                p1.add(s.get(1));
	                wordMap.put(s.get(0), p1);
	                
	                HashSet<String> p2 = wordMap.get(s.get(1));
	                p2.add(s.get(0));                
	                wordMap.put(s.get(1), p2);
	            }
	        }
	        
	        int similarWords = 0;
	        
	        for(int i =0; i <n; i++) {
	            if(words1[i].equals(words2[i])) {
	                similarWords++;
	            } else {
	                /* See if there exists a transition b/w words[i] -> words2[i] 
	                    using the mappings defined above
	                */
	                HashSet<String> usedKeys = new HashSet<String>();
	                Queue<String> queue = new LinkedList<String>();
	                queue.add(words1[i]);
	                usedKeys.add(words1[i]);
	                while(queue.size() > 0) {
	                    String current = queue.poll();
	                    if(wordMap.containsKey(current)) {
	                        HashSet<String> similar = wordMap.get(current);
	                        if(similar.contains(words2[i])) {
	                            similarWords++;
	                            break;
	                        } else {
	                            for(String s : similar) {
	                                if(!usedKeys.contains(s)) {
	                                    queue.add(s);   
	                                    usedKeys.add(s);
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        return similarWords == n;	        
	    }
	   @Test
	   public void Test1() {
		   List<List<String>> pairs = new ArrayList<List<String>>();
		   pairs.add(Arrays.asList("great","good"));
		   pairs.add(Arrays.asList("fine","good"));
		   pairs.add(Arrays.asList("drama","acting"));
		   pairs.add(Arrays.asList("skills","talent"));
		   boolean s = areSentencesSimilarTwo(
				   new String[] {"great","acting","skills"},
				   new String[] {"fine","drama","talent"},
				   pairs);			   
		   assertEquals(true, s);
	   }
}
