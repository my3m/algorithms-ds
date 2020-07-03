package com.interviewcake.trees;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

import static org.junit.Assert.*;

public class GraphColoring {

    public static class GraphNode {

        private String label;
        private Set<GraphNode> neighbors;
        private Optional<String> color;

        public GraphNode(String label) {
            this.label = label;
            neighbors = new HashSet<GraphNode>();
            color = Optional.empty();
        }

        public String getLabel() {
            return label;
        }

        public Set<GraphNode> getNeighbors() {
            return Collections.unmodifiableSet(neighbors);
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        public boolean hasColor() {
            return color.isPresent();
        }

        public String getColor() {
            return color.get();
        }

        public void setColor(String color) {
            this.color = Optional.ofNullable(color);
        }
    }

    public static void colorGraph(GraphNode[] graph, String[] colors) {

        // create a valid coloring for the graph
        // LinkedList<GraphNode> s = new LinkedList<>();
        // s.push(graph[0]);
        
        //N*D algo
        //if we arbitrary set a color for first node
        //then for all neighbors, we can assign them 

        int idx = 0;
        for(int i = 0; i < graph.length; i++) {
            GraphNode curr = graph[i];
            if(curr.hasColor())
                continue;
            LinkedList<GraphNode> s = new LinkedList<>();
            s.push(curr);
            Set<GraphNode> visiting = new HashSet<>();
            while(s.size() > 0) {
                GraphNode node = s.pop();
                visiting.add(node);
                Set<String> illegalColors = new HashSet<>();
                for(GraphNode nei : node.getNeighbors()) {
                    if(nei.hasColor())
                        illegalColors.add(nei.getColor());
                }
                //assign a color for "node"
                for(String color : colors) {
                    if(!illegalColors.contains(color)) {
                        node.setColor(color);
                        break;
                    }
                }          
                //push neighbors to stack
                for(GraphNode nei : node.getNeighbors()) {
                    // if(nei.equals(node))
                    //     throw new IllegalArgumentException("");
                    //avoids cycle
                    if(!nei.hasColor()) {
                        s.push(nei);
                    }
                }
            }
        }
        
        // Set<GraphNode> visited = new HashSet<>();
        // for(int i = 0; i < graph.length; i++) 
        //     dfs(graph[i], null, visited);
    }
    
    static void dfs(GraphNode node, GraphNode parent, Set<GraphNode> visited) {
        if(visited.contains(node))
            return;
        visited.add(node);
        System.out.println(node.label);
        for(GraphNode nei : node.getNeighbors()) {
            dfs(nei, node, visited);
        }
    }


















    // tests

    @Test
    public void lineGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void separateGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void triangleGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void envelopeGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        final GraphNode nodeE = new GraphNode("E");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeD);
        nodeB.addNeighbor(nodeE);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeC.addNeighbor(nodeE);
        nodeD.addNeighbor(nodeB);
        nodeD.addNeighbor(nodeC);
        nodeD.addNeighbor(nodeE);
        nodeE.addNeighbor(nodeB);
        nodeE.addNeighbor(nodeC);
        nodeE.addNeighbor(nodeD);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD, nodeE};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test(expected = Exception.class)
    public void loopGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        nodeA.addNeighbor(nodeA);
        final GraphNode[] graph = new GraphNode[] {nodeA};
        colorGraph(graph, getColors());
    }

    private static String[] getColors() {
        return new String[] {"red", "green", "blue", "orange", "yellow", "white"};
    }

    private static void validateGraphColoring(GraphNode[] graph) {

        for (final GraphNode node : graph) {
            if (!node.hasColor()) {
                fail(String.format("Found non-colored node %s", node.getLabel()));
            }
        }

        int maxDegree = 0;
        final Set<String> usedColors = new HashSet<>();

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            maxDegree = Math.max(maxDegree, neighbors.size());
            usedColors.add(node.getColor());
        }

        final int allowedColorCount = maxDegree + 1;

        if (usedColors.size() > allowedColorCount) {
            fail(String.format("Too many colors: %d allowed, but %d actually used",
                               allowedColorCount, usedColors.size()));
        }

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            for (final GraphNode neighbor: neighbors) {
                if (neighbor.getColor().equals(node.getColor())) {
                    fail(String.format("Neighbor nodes %s and %s have the same color %s",
                                       node.getLabel(), neighbor.getLabel(), node.getColor()));
                }
            }
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GraphColoring.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}