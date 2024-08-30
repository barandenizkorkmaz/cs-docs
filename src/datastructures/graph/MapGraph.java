package datastructures.graph;

import java.util.*;

/** A MapGraph is an extension of the AbstractGraph abstract class
 that uses an array of maps to represent the edges.
 */
public class MapGraph extends AbstractGraph {
    // Data Field
    /**
     * An array of maps to contain the edges that
     * originate with each vertex.
     */
    private Map<Integer, Edge>[] outgoingEdges;

    /**
     * Construct a graph with the specified number of vertices and directionality.
     *
     * @param numV     The number of vertices
     * @param directed The directionality flag
     */
    public MapGraph(int numV, boolean directed) {
        super(numV, directed);
        outgoingEdges = new Map[numV];
        for (int i = 0; i < numV; i++) {
            outgoingEdges[i] = new LinkedHashMap<>();
        }
    }

    /**
     * Insert a new edge into the graph.
     *
     * @param edge The new edge
     */
    public void insert(Edge edge) {
        int source = edge.getSource();
        int dest = edge.getDest();
        double weight = edge.getWeight();
        outgoingEdges[source].put(dest, edge);
        if (!isDirected()) {
            Edge reverseEdge = new Edge(dest, source, weight);
            outgoingEdges[dest].put(source, reverseEdge);
        }
    }

    /**
     * Get the edge between two vertices.
     *
     * @param source The source
     * @param dest   The destination
     * @return the edge between these two vertices
     * or null if an edge does not exist.
     */
    public Edge getEdge(int source, int dest) {
        return outgoingEdges[source].get(dest);
    }

    /**
     * Determine whether an edge exists.
     *
     * @param source The source vertex
     * @param dest   The destination vertex
     * @return true if there is an edge from source to dest
     */
    public boolean isEdge(int source, int dest) {
        return outgoingEdges[source].containsKey(dest);
    }

    public Iterator<Edge> edgeIterator(int source) {
        return outgoingEdges[source].values().iterator();
    }

}
