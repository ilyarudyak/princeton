package algo1.pas.pa5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 7/21/16.
 */
public class UndirectedWeightedGraph {

    private static class Edge {
        private int src;
        private int dst;
        private int weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        public int getSrc() {
            return src;
        }

        public int getDst() {
            return dst;
        }

        public int getWeight() {
            return weight;
        }
    }

    private int V;
    private int E;
    private List<List<Edge>> adj;
    private boolean isStartedFromOne;

    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public UndirectedWeightedGraph(int V) {

        this.V = V;
        this.E = 0;

        adj = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            adj.add( new ArrayList<Edge>() );
        }
    }

    /**
     * Initializes a graph from an input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public UndirectedWeightedGraph(String filename, int v, boolean isStartedFromOne) {

        this(v);
        this.isStartedFromOne = isStartedFromOne;

        Scanner in = null;
        String line;
        try {
            in = new Scanner(new File(filename));
            while (in.hasNextLine()) {
                line = in.nextLine();
                extractEdges(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // getters and setters
    private void extractEdges(String line) {
        String[] edges = line.split("\\s+");
        int src = Integer.parseInt(edges[0]);
        for (int i = 1; i < edges.length; i++) {
            String[] edges2 = edges[i].split(",");
            int dst = Integer.parseInt(edges2[0]);
            int weight = Integer.parseInt(edges2[1]);
            addEdge(src, dst, weight);
        }
    }
    public int getV() {
        return V;
    }
    public int getE() {
        return E;
    }

    public void addEdge(int src, int dst, int weight) {
        E++;

        // in file nodes numbered from 1, we need from 0
        if (isStartedFromOne) {
            src -= 1;
            dst -= 1;
        }

        adj.get(src).add( new Edge(src, dst, weight) );
//        adj.get(dst).add( new Edge(dst, src, weight) );
    }
    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + " ");
            for (Edge e : adj.get(v)) {
                s.append(e.dst + "," + e.weight + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {

//        String filename = "src/main/resources/dijkstraData.txt";
        String filename = "src/main/resources/simpleDijkstra.txt";
        UndirectedWeightedGraph G = new UndirectedWeightedGraph(filename, 4, false);
        System.out.println(G);
    }
}



















