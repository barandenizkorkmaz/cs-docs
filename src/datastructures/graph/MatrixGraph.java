package datastructures.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MatrixGraph extends AbstractGraph{

    private double[][] edges;

    public MatrixGraph(int numV, boolean isDirected) {
        super(numV, isDirected);
        edges = new double[numV][numV];
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    @Override
    public void insert(Edge edge) {
        edges[edge.getSource()][edge.getDest()] = edge.getWeight();
        if(!isDirected()){
            edges[edge.getDest()][edge.getSource()] = edge.getWeight();
        }
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return edges[source][dest] == Double.POSITIVE_INFINITY ? null : new Edge(source, dest);
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        List<Edge> edges = new LinkedList<>();
        for(int i = 0; i < this.getNumV(); i++){
            if(this.edges[source][i] != Double.POSITIVE_INFINITY){
                edges.add(getEdge(source, i));
            }
        }
        return edges.iterator();
    }
}
