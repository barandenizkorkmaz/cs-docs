package algorithms.graph.search;

import datastructures.graph.Edge;
import datastructures.graph.Graph;

import java.util.Arrays;
import java.util.Iterator;

/** Class to implement the depth‐first search algorithm. */
public class DepthFirstSearch {

    /** Start depth‐first search the graph starting at vertex start.
     @param graph The graph object.
     @param start The start vertex
     */
    public static void depthFirstSearch(Graph graph, int start){
        int n = graph.getNumV();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        depthFirstSearchRecurse(graph, start, visited, parent);
    }

    /** Recursively depth‐first search the graph starting at vertex current.
     @param graph The graph object.
     @param current The start vertex
     @param visited Boolean array of visited nodes
     @param parent Integer array of parent nodes for corresponding vertices.
     */
    private static void depthFirstSearchRecurse(Graph graph, int current, boolean[] visited, int[] parent) {
        /* Mark the current vertex visited. */
        visited[current] = true;
        System.out.print(current + "\t");
        /* Examine each vertex adjacent to the current vertex */
        Iterator<Edge> itr = graph.edgeIterator(current);
        while (itr.hasNext()) {
            int neighbor = itr.next().getDest();
            /* Process a neighbor that has not been visited */
            if (!visited[neighbor]) {
                /* Insert (current, neighbor) into the depth‐first search tree. */
                parent[neighbor] = current;
                /* Recursively apply the algorithm starting at neighbor. */
                depthFirstSearchRecurse(graph, neighbor, visited, parent);
            }
        }
    }
}
