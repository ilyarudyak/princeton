package algo1.pas.pa5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ilyarudyak on 7/21/16.
 */
public class Dijkstra {

    private List<Integer> distTo;
    private List<WeightedEdge> edgeTo;
    private PriorityQueue<Integer> pq;
    private UndirectedWeightedGraph G;

    public Dijkstra(UndirectedWeightedGraph g, int s) {

        G = g;

        distTo = new ArrayList<>();
        for (int i = 0; i < G.getV(); i++) { distTo.add(Integer.MAX_VALUE); }
        distTo.set(s, 0);

        edgeTo = new ArrayList<>();
        pq = new PriorityQueue<>();
    }

    public void buildShortestPath() {

    }

    // utility functions
    public int getDist(int v) {
        return distTo.get(v);
    }
    public boolean isPathExists(int v) {
        return distTo.get(v) < Integer.MAX_VALUE;
    }
    public Iterable<WeightedEdge> getPath(int v) {
        return null;
    }

    public static void main(String[] args) {

        String filename = "src/main/resources/simpleDijkstra.txt";
        UndirectedWeightedGraph G = new UndirectedWeightedGraph(filename, 4, false);
        Dijkstra dijkstra = new Dijkstra(G, 0);
        dijkstra.buildShortestPath();
        System.out.println(dijkstra.getDist(3));
    }
}



















