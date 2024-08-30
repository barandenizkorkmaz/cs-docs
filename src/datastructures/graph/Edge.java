package datastructures.graph;

public class Edge {
    private int source;
    private int dest;
    private double weight;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
        this.weight = 1.0;
    }

    public Edge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDest(){
        return dest;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return source == edge.source && dest == edge.dest;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(source);
        result = 31 * result + Integer.hashCode(dest);
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
