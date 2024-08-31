package algorithms.graph.sort;

import algorithms.graph.search.DepthFirstSearch;
import datastructures.graph.AbstractGraph;
import datastructures.graph.Graph;

import java.io.File;
import java.util.*;

/** This program outputs the topological sort of a directed graph
 that contains no cycles.
 */
public class TopologicalSort {
    /**
     * The main method that performs the topological sort.
     *
     * @param args The command line arguments
     * @pre arg[0] contains the name of the file
     * that contains the graph. It has no cycles.
     */
    public static void main(String[] args) {
        Graph theGraph = null;
        int numVertices = 0;
        try {
            // Connect Scanner to input file.
            Scanner scan = new Scanner(new File(args[0]));
            // Load the graph data from a file.
            theGraph = AbstractGraph.createGraph(scan, true, "List");
            numVertices = theGraph.getNumV();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
            // Error exit.
        }
        // Perform the depth‐first search.
        DepthFirstSearch.depthFirstSearch(theGraph, 0);

        // Topological Sort is the reverse of finish order in dfs. Please update the print statements in the dfs to view topological sort.
    }
}
