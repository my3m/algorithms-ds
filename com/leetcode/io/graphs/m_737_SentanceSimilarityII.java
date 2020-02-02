package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class m_737_SentanceSimilarityII {
	/*
	 	This is my first implementation, ran around 105ms
	 */
	public boolean areSentencesSimilarTwo1(String[] words1, String[] words2, List<List<String>> pairs) {
		/*
		 * 1. We know if two sentances are similar, if there exists a word transition
		 * from one pair to another. 
		 * 
		 * 2. A word.equals(word) is always similar 3. Same
		 * number of words
		 * 
		 * "Connected components" great->["fine"] fine->["great"] drama->["acting"]
		 * acting->["drama"] skills->["talent"] talent->["skills"]
		 * 
		 */
		if (words1.length != words2.length)
			return false;
		int n = words1.length;
		HashMap<String, HashSet<String>> wordMap = new HashMap<String, HashSet<String>>();
		if (pairs.size() > 0) {
			for (List<String> s : pairs) {
				if (!wordMap.containsKey(s.get(0))) {
					wordMap.put(s.get(0), new HashSet<String>());
				}
				if (!wordMap.containsKey(s.get(1))) {
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

		for (int i = 0; i < n; i++) {
			if (words1[i].equals(words2[i])) {
				similarWords++;
			} else {
				/*
				 * See if there exists a transition b/w words[i] -> words2[i] using the mappings
				 * defined above
				 */
				HashSet<String> usedKeys = new HashSet<String>();
				Queue<String> queue = new LinkedList<String>();
				queue.add(words1[i]);
				usedKeys.add(words1[i]);
				while (queue.size() > 0) {
					String current = queue.poll();
					if (wordMap.containsKey(current)) {
						HashSet<String> similar = wordMap.get(current);
						if (similar.contains(words2[i])) {
							similarWords++;
							break;
						} else {
							for (String s : similar) {
								if (!usedKeys.contains(s)) {
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
	
	   public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
	        //transitive a->b, b->c, a->c
	        //"great" -> "good"
	        //"fine" -> "good"
	        
	        if(words1.length != words2.length)
	            return false;
	        
	        Map<String, Set<String>> g = buildGraph(pairs);
	        Set<String> words = g.keySet();
	        // for(String key : words) {
	        //     Set<String> transitive = new HashSet<String>();
	        //     buildTransitive(g, key, new HashSet<String>(), transitive);
	        //     for(String add : transitive) {
	        //         if(key.equals(add)) 
	        //             continue;
	        //         g.get(key).add(add);
	        //     }
	        // }
	        int len = words1.length;
	        
	        for(int i = 0; i < len; i++) {
	            if(!words1[i].equals(words2[i])){
	                if(!g.containsKey(words1[i])) {
	                    return false;
	                }
	                if(!dfs(g, new HashSet<String>(), words1[i], words2[i])) {
	                    return false;
	                }
	            }
	        }
	        
	        return true;
	    }
	    
	    //A=>[B,C,D], D=[G,H,I], H=[X,Y,Z]
	    /*
	        Instead of finding exact A.equals(Z), we just need to know if
	        a connecting node has a neighbour 'Z'
	    */
	    boolean dfs(Map<String, Set<String>> g, Set<String> visited, String source, String dest) {
	    	//Naive approach, check node by node
	        // if(source.equals(dest))
	        //     return true;
	        /*
	            This improvement lowers time from 75ms to 47ms,
	            It looks at neighbouring nodes for "dest", & cuts recursive calls
	        */
	        if(g.get(source).contains(dest))
	            return true;
	       
	        Set<String> neighbours = g.get(source);
	        if(neighbours != null) {
	            for(String neighbour : neighbours) {
	                //optimization
	                // if(visited.contains(neighbour))
	                //     continue;
	                if(visited.add(neighbour)) {
	                    if(dfs(g, visited, neighbour, dest)){
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	    }
	    
	    /*
	     * This failed. Investigate later
	     */
	    //a->b, b->c, c->d
	    void buildTransitive(Map<String, Set<String>> g, String key, 
	                         Set<String> visited, Set<String> transitive) {
	        Set<String> neighbours = g.get(key);
	        for(String neighbour : neighbours) {
	            if(visited.contains(neighbour)) 
	                continue;
	            visited.add(neighbour);
	            buildTransitive(g, neighbour, visited, transitive);
	        }
	        //There are no more nodes for this node
	        transitive.add(key);
	    }
	    
	    /*
	        Can we pre-process transitive states in the graph?
	    */
	    Map<String, Set<String>> buildGraph(List<List<String>> edges) {
	        //["great", "good"]
	        
	        Map<String, Set<String>> g = new HashMap<String, Set<String>>();
	        for(List<String> edge : edges) {
	            for(String s : edge) {
	                g.putIfAbsent(s, new HashSet<String>());
	            }
	            g.get(edge.get(0)).add(edge.get(1));
	            g.get(edge.get(1)).add(edge.get(0));
	        }
	        return g;
	    }
	

	@Test
	public void Test1() {
		List<List<String>> pairs = new ArrayList<List<String>>();
		pairs.add(Arrays.asList("great", "good"));
		pairs.add(Arrays.asList("fine", "good"));
		pairs.add(Arrays.asList("drama", "acting"));
		pairs.add(Arrays.asList("skills", "talent"));
		boolean s = areSentencesSimilarTwo(new String[] { "great", "acting", "skills" },
				new String[] { "fine", "drama", "talent" }, pairs);
		assertEquals(true, s);
	}
}
