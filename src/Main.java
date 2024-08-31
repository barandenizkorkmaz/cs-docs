import algorithms.graph.minspanningtree.Prim;
import algorithms.graph.search.BreadthFirstSearch;
import algorithms.graph.search.DepthFirstSearch;
import algorithms.graph.shortestpath.Dijkstra;
import datastructures.graph.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        AbstractGraph g = new ListGraph(5, true);
        g.loadEdgesFromFile(new Scanner(new File("/home/denizkorkmaz/Git/BDK-Repositories/Personal/cs-docs/src/graph-dijkstra.txt")));
        System.out.println("Breadth First Search");
        BreadthFirstSearch.breadthFirstSearch(g, 0);
        System.out.println();
        System.out.println("Depth First Search");
        DepthFirstSearch.depthFirstSearch(g, 0);
        int[] pred = new int[5];
        double[] dist = new double[5];
        Dijkstra.dijkstrasAlgorithm(g, 0, pred, dist);
        for(int i = 0; i < pred.length; i++) {
            System.out.println("Node " + i + ": " + "\tPredecessor: " + pred[i] + "\tDistance: " + dist[i]);
        }
        ArrayList<Edge> res = Prim.primsAlgorithm(g, 0);
        for(Edge e : res) {
            System.out.println(e);
        }
    }
}