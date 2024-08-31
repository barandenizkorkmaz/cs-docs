package algorithms.graph.search;

import datastructures.graph.Edge;
import datastructures.graph.Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/** Class to implement the breadth‐first search algorithm. */
public class BreadthFirstSearch {

    /** Perform a breadth‐first search of a graph.
     @post The array parent will contain the predecessor
     of each vertex in the breadth‐first search tree.
     @param graph The graph to be searched
     @param start The start vertex
     @return The array of parents
     */
    public static int[] breadthFirstSearch(Graph graph, int start) {
        Queue<Integer> queue = new LinkedList<Integer>();

        // Declare array parent and initialize its elements to –1.
        int[] parent = new int[graph.getNumV()];
        for (int i = 0; i < graph.getNumV(); i++) {
            parent[i] = -1;
        }

        // Declare array visited and initialize its elements to false.
        boolean[] visited = new boolean[graph.getNumV()];

        // Mark the start vertex as visited and insert it into the queue.
        visited[start] = true;
        queue.offer(start);

        // Perform breadth‐first search until done
        while (!queue.isEmpty()) {
            // Take a vertex, current, out of the queue.
            int current = queue.remove();
            System.out.print(current + "\t");
            // Examine each vertex, neighbor, adjacent to current.
            Iterator<Edge> itr = graph.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                if (!visited[neighbor]) {
                    // Mark it visited.
                    visited[neighbor] = true;
                    // Place it into the queue.
                    queue.offer(neighbor);
                    // Insert the edge (current, neighbor) into the tree.
                    parent[neighbor] = current;
                }
            }
        }
        return parent;
    }
}
