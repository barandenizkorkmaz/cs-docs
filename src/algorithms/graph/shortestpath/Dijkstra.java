package algorithms.graph.shortestpath;

import datastructures.graph.Graph;

import java.util.HashSet;

public class Dijkstra {

    public static final int UNDEFINED = Integer.MIN_VALUE;
    public static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * Dijkstra's Shortest‐Path algorithm.
     *
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred  Output array to contain the predecessors in the shortest path
     * @param dist  Output array to contain the distance in the shortest path
     */
    public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {
        int numV = graph.getNumV();
        System.out.println(numV);
        HashSet<Integer> vMinusS = new HashSet<>(numV);

        // Initialization.
        for (int v = 0; v < numV; v++) {
            dist[v] = INFINITY;
            pred[v] = UNDEFINED;
            vMinusS.add(v);
        }

        dist[start] = 0;

        // Main loop
        while (!vMinusS.isEmpty()) {
            // Find the value u in V–S with the smallest dist[u].
            double minDist = INFINITY;
            int u = UNDEFINED;

            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }

            // Remove u from vMinusS
            vMinusS.remove(u);

            // Update the distances.
            for (int v : vMinusS) {
                if (graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight();
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}