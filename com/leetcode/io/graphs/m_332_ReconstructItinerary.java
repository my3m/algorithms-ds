package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Test;

/*
 * Testing
 */
//[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//"JFK"
//["JFK", "MUC"], "MUC"
//["MUC", "LHR"], "LHR"
//["LHR", "SFO"], "SFO"
//["SFO", "SJC"], "SJC"

//[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//"JFK"
//["JFK", "SFO"], ["JFK", "ATL"], "ATL"
//["ATL", "JFK"], ["ATL", "SFO"], "JFK"
//["JFK", "SFO"] "SFO"
//["SFO", "ATL"], "ATL",
//["ATL", "SFO"], "SFO"

public class m_332_ReconstructItinerary {
	HashMap<String, List<String>> map;
	int stops;
	HashMap<String, HashSet<Integer>> used;

	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, LinkedList<String>> g = buildGraph(tickets);
		//visited
		Map<String, Set<Integer>> visited = new HashMap<String, Set<Integer>>();
		List<String> path = new ArrayList<String>();
		//dfs(tickets, visited, g, path, "", "JFK", 0);
		dfs2(tickets, visited, g, path, "JFK", 0);
		return path;
	}
	
	/*
	 
	 JFK -> NRT
	 NRT -> JFK
	 JFK -> KUL
	 
	 */
	int dfs(List<List<String>> tickets, Map<String, Set<Integer>> visited,
			Map<String, LinkedList<String>> graph, List<String> path, String origin, String to, int n) {
		path.add(to);
		if(path.size() == tickets.size() + 1) {
			return 1;
		}
		/*
		 * Traverse each neighbour node
		 */
		char[] space = new char[n];
		Arrays.fill(space, ' ');
		String spaces = new String(space);
		
		visited.putIfAbsent(to, new HashSet<Integer>());
		List<String> neighbours = graph.get(to);
		System.out.printf(spaces + "Neighbours of %s=[%s]\r\n", to, String.join(",", neighbours));
		for(int i = 0; i < neighbours.size(); i++) {
			String node = neighbours.get(i);
			System.out.printf(spaces + "   %s\r\n", node);
			if(visited.containsKey(to) && visited.get(to).contains(i)) {
				System.out.printf(spaces + "   already visited (%s)\r\n", node);
				continue;
			}
			//path.add(to);
			
			visited.get(to).add(i);
			if(dfs(tickets, visited, graph, path, to, node, n + 3) == 1) {
				return 1;
			}
			path.remove(path.size() - 1);
			visited.get(to).remove(i);
		}
		System.out.println(spaces + "(no paths)");
		return 0;
	}
	
	
	/*
	 * I have no idea how this works, but similar to topological sort,
	 * exhausts all nodes, then adds it front of a linked list, or top of a stack
	 */
	void dfs2(List<List<String>> tickets, Map<String, Set<Integer>> visited,
			Map<String, LinkedList<String>> graph, List<String> path, String to, int n) {
		
		char[] space = new char[n];
		Arrays.fill(space, ' ');
		String spaces = new String(space);
		
		LinkedList<String> neighbours = graph.get(to);
		System.out.printf(spaces + "Neighbours of %s=[%s]\r\n", to, String.join(",", neighbours));
		while(neighbours.size() > 0) {
			System.out.printf(spaces + "   %s\r\n", neighbours.peek());
			dfs2(tickets, visited, graph, path, neighbours.pop(), n + 1);
		}
		System.out.printf(spaces + "adding %s to front queue (no paths)\r\n", to);
		path.add(0, to);		
	}
	
	public Map<String, LinkedList<String>> buildGraph(List<List<String>> edges) {
		Map<String, LinkedList<String>> g = new HashMap<String, LinkedList<String>>();
		for(List<String> edge : edges) {
			for(String edgeV : edge) {
				g.putIfAbsent(edgeV, new LinkedList<String>());
			}
			g.get(edge.get(0)).add(edge.get(1));
		}
		for(String key : g.keySet()) {
			Collections.sort(g.get(key));
		}
		return g;
	}

	boolean isSmallestLexicographicalOrder(String str1, String str2) {
		int i = 0;
		int j = 0;
		int len = Math.min(str1.length(), str2.length());
		for (int k = 0; k < len; k++) {
			if (str1.charAt(i) == str2.charAt(j)) {
				i++;
				j++;
			}
		}
		if (i < str1.length() && j < str2.length()) {
			return str1.charAt(i) < str2.charAt(j);
		} else {
			return str1.length() < str2.length();
		}
	}

	String findsmallestLexicographicalOrder(List<String> strings) {
		if (strings.size() == 0)
			return null;
		if (strings.size() == 1)
			return strings.get(0);
		String smallest = strings.get(0);
		for (int i = 1; i < strings.size(); i++) {
			int n = 0;
			int m = 0;
			String comparing = strings.get(i);
			int len = Math.min(smallest.length(), comparing.length());
			// abcd, abc
			// abc, def
			// SFXO, SFX
			for (int j = 0; j < len; j++) {
				if (smallest.charAt(n) == comparing.charAt(m)) {
					n++;
					m++;
				}
			}
			if (n < smallest.length() && m < comparing.length()) {
				smallest = smallest.charAt(n) < comparing.charAt(m) ? smallest : comparing;
			} else {
				smallest = smallest.length() < comparing.length() ? smallest : comparing;
			}
		}
		return smallest;
	}

    @Test
    public void Test1() {
    	List<List<String>> itinerary = new ArrayList<List<String>>();
    	itinerary.add(Arrays.asList("MUC","LHR"));
    	itinerary.add(Arrays.asList("JFK","MUC"));
    	itinerary.add(Arrays.asList("SFO","SJC"));
    	itinerary.add(Arrays.asList("LHR","SFO"));
    	
    	List<String> expected = Arrays.asList("JFK","MUC","LHR","SFO","SJC");
    	assertEquals(expected, findItinerary(itinerary));	
    }

	@Test
	public void Test2() {

//		PriorityQueue<String> pqueue = new PriorityQueue<String>();
//		pqueue.add("SFO");
//		pqueue.add("SFX");
//		pqueue.add("SFOD");
//		pqueue.add("JFK");
//		pqueue.add("ZIO");
//		while (pqueue.size() > 0) {
//			System.out.println(pqueue.poll());
//		}

		List<List<String>> itinerary = new ArrayList<List<String>>();
		itinerary.add(Arrays.asList("JFK", "SFO"));
		itinerary.add(Arrays.asList("JFK", "ATL"));
		itinerary.add(Arrays.asList("SFO", "ATL"));
		itinerary.add(Arrays.asList("ATL", "JFK"));
		itinerary.add(Arrays.asList("ATL", "SFO"));

		List<String> expected = Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");
		assertEquals(expected, findItinerary(itinerary));
	}

	/*
	 * The lexicographical smallest from "KUL", "NRT" is "KUL", it does not have
	 * another connecting ticket, so it failed the test case, Beware of picking the
	 * next node, if it is not the one next
	 */
	@Test
	public void Test3() {
		List<List<String>> itinerary = new ArrayList<List<String>>();
		itinerary.add(Arrays.asList("JFK", "KUL"));
		itinerary.add(Arrays.asList("JFK", "NRT"));
		itinerary.add(Arrays.asList("NRT", "JFK"));

		List<String> expected = Arrays.asList("JFK", "NRT", "JFK", "KUL");
		assertEquals(expected, findItinerary(itinerary));
	}
	
	@Test
	public void Test4() {
		List<List<String>> itinerary = new ArrayList<List<String>>();
		itinerary.add(Arrays.asList("EZE","AXA"));
		itinerary.add(Arrays.asList("TIA","ANU"));
		itinerary.add(Arrays.asList("ANU","JFK"));
		itinerary.add(Arrays.asList("JFK","ANU"));
		itinerary.add(Arrays.asList("ANU","EZE"));
		itinerary.add(Arrays.asList("TIA","ANU"));
		itinerary.add(Arrays.asList("AXA","TIA"));
		itinerary.add(Arrays.asList("TIA","JFK"));
		itinerary.add(Arrays.asList("ANU","TIA"));
		itinerary.add(Arrays.asList("JFK","TIA"));
		
		List<String> expected = Arrays.asList("JFK","ANU","EZE","AXA","TIA","ANU","JFK","TIA","ANU","TIA","JFK");
		assertEquals(expected, findItinerary(itinerary));
		
	}
}
