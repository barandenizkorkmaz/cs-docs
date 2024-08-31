package algorithms.graph.minspanningtree;

import datastructures.graph.Edge;
import datastructures.graph.Graph;

import java.util.*;

public class Prim {

    /** Prim's Minimum Spanning Tree algorithm.
     @param graph The weighted graph to be searched
     @param start The start vertex
     @return An ArrayList of edges that forms the MST
     */
    public static ArrayList<Edge> primsAlgorithm(Graph graph, int start) {
        ArrayList<Edge> result = new ArrayList<>();
        int numV = graph.getNumV();

        // Use a HashSet to represent V–S.
        Set<Integer> vMinusS = new HashSet<>(numV);
        // Declare the priority queue.
        Queue<Edge> pQ = new PriorityQueue<>(numV, (e1, e2) -> Double.compare(e1.getWeight(), e2.getWeight()));

        // Initialize V–S.
        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }
        int current = start;
        // Main loop
        while (!vMinusS.isEmpty()) {
            // Update priority queue.
            Iterator<Edge> iter = graph.edgeIterator(current);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                int dest = edge.getDest();
                if (vMinusS.contains(dest)) {
                    pQ.add(edge);
                }
            }
            // Find the shortest edge whose source is in S and
            // destination is in V–S.
            int dest = -1;
            Edge edge = null;
            do {
                edge = pQ.remove();
                dest = edge.getDest();
            } while(!vMinusS.contains(dest));
            // Take dest out of vMinusS.
            vMinusS.remove(dest);
            // Add edge to result.
            result.add(edge);
            // Make this the current vertex.
            current = dest;
        }
        return result;
    }
}
