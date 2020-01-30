package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

/* Application=Topological Sort
 * Not adding all nodes in array,
 * Assuming zb, ca that z -> c (correct). b->a (WRONG!!!), we only know
 * from first char
 */
public class m_269_AlienDictionary {
	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> graph = buildGraph(words);
		List<Character> topSort = getTopologicalSort(graph);
		if(topSort == null || topSort.size() == 0)
			return "";
		char[] arr = new char[topSort.size()];
		for(int i = 0; i < topSort.size(); i++) {
			arr[i] = topSort.get(i);
		}
		return new String(arr);
	}

	/*
	 * How do you build graph for the problem??
	 */
	Map<Character, Set<Character>> buildGraph(String[] words) {
		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		
        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(!graph.containsKey(words[i].charAt(j)))
                    graph.put(words[i].charAt(j), new HashSet<Character>());
            }
        }
		
		for (int i = 1; i < words.length; i++) {
			String first = words[i - 1];
			String second = words[i];
			int len = Math.min(words[i].length(), words[i - 1].length());
			for (int j = 0; j < len; j++) {
				if (first.charAt(j) != second.charAt(j)) {
					// Add an edge from first[j] -> second[j];
					graph.get(first.charAt(j)).add(second.charAt(j));
					break;
				}
			}
		}
		return graph;
	}
	
	List<Character> getTopologicalSort(Map<Character, Set<Character>> graph) {
		/*
		 * dfs-topological sort,
		 * explore node, add nodes with no children to stack
		 */
		List<Character> topologicalSort = new ArrayList<Character>();
		Set<Character> visiting = new HashSet<Character>();
		Set<Character> expanded = new HashSet<Character>();
		Stack<Character> stack = new Stack<Character>();
		for(Character v : graph.keySet()) {
			if(!dfs(graph, v, visiting, expanded, stack))
				return null;
		}
		
		while(stack.size() > 0)
			topologicalSort.add(stack.pop());
		return topologicalSort;
	}
	
	boolean dfs(Map<Character, Set<Character>> graph, Character vertex,
			Set<Character> visiting, Set<Character> expanded, Stack<Character> stack) {
		if(visiting.contains(vertex)) {
			if(expanded.contains(vertex)) {
				return true;
			}
			return false;
		}
		
		visiting.add(vertex);
		for(Character v : graph.get(vertex)) {
			if(!dfs(graph, v, visiting, expanded, stack))
				return false;
		}
		
		stack.push(vertex);
		expanded.add(vertex);
		return true;
	}
	

	@Test
	public void Test1() {
		String[] sortedAlienWords = new String[] {
				"wrt", "wrf", "er", "ett", "rftt"
				};
		//t->f
		//w->e
		//r->t
		//e->r
		assertEquals("wertf", alienOrder(sortedAlienWords));
	}
	
	@Test
	public void Test2() {
		String[] sortedAlienWords = new String[] {
				"z", "x"
				};
		assertEquals("zx", alienOrder(sortedAlienWords));
	}
	
	@Test
	public void Test3() {
		String[] sortedAlienWords = new String[] {
				"z", "x", "z"
				};
		assertEquals("", alienOrder(sortedAlienWords));
	}
	
	@Test
	public void Test4() {
		String[] sortedAlienWords = new String[] {
				"z", "z"
				};
		assertEquals("z", alienOrder(sortedAlienWords));
	}
	
	@Test
	public void Test5() {
		String[] sortedAlientWords = new String[] {
				"za","zb","ca","cb"
		};
		/*
		 * a->b
		 * z->c
		 * 
		 */
		assertEquals("zcab", alienOrder(sortedAlientWords));
	}
}
