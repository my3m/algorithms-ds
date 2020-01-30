package io.graphs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectedGraphTests {
	@Test
	public void Test1() {
		DirectedGraph<Integer> g = new DirectedGraph<Integer>();
		
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(1, 3);
		
		
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		
		g.addEdge(4, 1);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		
		assertTrue(!g.hasCycle());
		g.addEdge(6, 4);

		assertTrue(g.hasCycle());
		
		g.removeEdge(6, 4);
		
		assertFalse(g.hasCycle());
	}
}
