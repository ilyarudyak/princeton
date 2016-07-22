package algo1.pas.pa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ilyarudyak on 7/21/16.
 */
public class Dijkstra {

    private List<Integer> distTo;
    private List<WeightedEdge> edgeTo;
    private IndexMinPQ<Integer> pq;
    private UndirectedWeightedGraph G;

    public Dijkstra(UndirectedWeightedGraph g, int s) {

        G = g;

        distTo = new ArrayList<>();
        for (int i = 0; i < G.getV(); i++) { distTo.add(Integer.MAX_VALUE); }
        distTo.set(s, 0);

        edgeTo = new ArrayList<>();
        for (int i = 0; i < G.getV(); i++) {
            edgeTo.add(null);
        }
        pq = new IndexMinPQ<>(G.getV());
        pq.insert(s, 0);
    }

    public void buildShortestPath() {

        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (WeightedEdge e: G.adj(v)) {
                relax(e, v);
            }
        }
    }

    private void relax(WeightedEdge e, int v) {
        int w = e.getOther(v);
        if (distTo.get(w) > distTo.get(v) + e.getWeight()) {
            distTo.set(w, distTo.get(v) + e.getWeight());
            edgeTo.set(w, e);
            if (pq.contains(w)) pq.decreaseKey(w, distTo.get(w));
            else                pq.insert(w, distTo.get(w));
        }
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

//        String filename = "src/main/resources/simpleDijkstra.txt";
//        UndirectedWeightedGraph G = new UndirectedWeightedGraph(filename, 4, false);
//        Dijkstra dijkstra = new Dijkstra(G, 0);
//        dijkstra.buildShortestPath();
//        System.out.println(dijkstra.getDist(3));

        String filename = "src/main/resources/dijkstraData.txt";
        UndirectedWeightedGraph G = new UndirectedWeightedGraph(filename, 200, true);
        Dijkstra dijkstra = new Dijkstra(G, 0);
        dijkstra.buildShortestPath();

        List<Integer> nodes = new ArrayList<>(Arrays.asList(7,37,59,82,99,115,133,165,188,197));
        for (int v: nodes) {
            System.out.print (dijkstra.getDist(v - 1) + " ");
        }
        System.out.println();
    }
}



















