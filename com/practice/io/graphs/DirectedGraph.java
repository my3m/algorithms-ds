package io.graphs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Adjacency List
 * 	+ Sparse graphs/Saves Memory
 */
public class DirectedGraph<T> implements Iterable<T> {
	private final Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

	public boolean addNode(T node) {
		if (graph.containsKey(node))
			return false;
		graph.put(node, new HashSet<T>());
		return true;
	}

	public void addEdge(T source, T target) {
		if (!graph.containsKey(source) || !graph.containsKey(target))
			throw new NoSuchElementException();
		graph.get(source).add(target);
	}

	public void removeEdge(T source, T target) {
		if (!graph.containsKey(source) || !graph.containsKey(target))
			throw new NoSuchElementException();
		graph.get(source).remove(target);
	}

	public boolean edgeExists(T start, T end) {
		if (!graph.containsKey(start) || !graph.containsKey(end))
			throw new NoSuchElementException();

		return graph.get(start).contains(end);
	}

	public Set<T> edgesFrom(T source) {
		if (!graph.containsKey(source))
			throw new NoSuchElementException();
		return Collections.unmodifiableSet(graph.get(source));
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return graph.keySet().iterator();
	}

	public int size() {
		return graph.size();
	}

	public boolean isEmpty() {
		return graph.size() == 0;
	}
	
	boolean hasCycle() {
		Set<T> visiting = new HashSet<T>();
		Set<T> expanded = new HashSet<T>();
		
		for(T v : graph.keySet()) {
			if(!expanded.contains(v)) 
				if(hasCycle(v, visiting, expanded))
					return true;
		}
		return false;
	}
	
	boolean hasCycle(T vertex, Set<T> visiting, Set<T> expanded) {
		if(visiting.contains(vertex)) {
			return true;
		}
		visiting.add(vertex);
		for(T v : graph.get(vertex)) {
			if(!expanded.contains(v))
				if(hasCycle(v, visiting, expanded))
					return true;
		}
		visiting.remove(vertex);
		expanded.add(vertex);
		return false;
	}
	
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
		
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		g.addEdge(6, 4);

		
		assertTrue(g.hasCycle());
	}
}
