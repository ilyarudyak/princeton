package algo1.pas.pa2;

import java.util.*;

/**
 * Created by ilyarudyak on 7/20/16.
 */
public class Karger {

    private static class Edge {
        private int src;
        private int dst;

        public Edge(int src, int dst) {
            this.src = src;
            this.dst = dst;
        }

        public int getSrc() {
            return src;
        }

        public int getDst() {
            return dst;
        }
    }
    private static class Graph {

        private int V;
        private int E;
        private List<Edge> edges;

        public Graph(int v, int e, List<Edge> edges) {
            V = v;
            E = e;
            this.edges = edges;
        }

        public int getV() {
            return V;
        }

        public int getE() {
            return E;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private Graph G;
    private UF uf;
    private int cV;
    private Random random;

    public Karger(Graph g) {
        G = g;
        uf = new UF(G.getV());
        cV = G.getV();
        random = new Random(new Date().getTime());
    }

    public int buildCut() {

        while (cV > 2) {
            contractEdge();
        }

        return countCut();
    }

    // helper functions
    private int countCut() {
        int count = 0;
        for (Edge e: G.getEdges()) {
            int src = e.getSrc();
            int dst = e.getDst();
            if (!uf.connected(src, dst)) {
                count++;
            }
        }
        return count;
    }
    private void contractEdge() {

        int i = random.nextInt(G.getV());

        Edge e = G.getEdges().get(i);
        int src = e.getSrc();
        int dst = e.getDst();

        if (!uf.connected(src, dst)) {
            uf.union(src, dst);
            cV--;
        }

    }

    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>(Arrays.asList(
                new Edge(0, 1), new Edge(0, 3),
                new Edge(1, 2), new Edge(1, 3),
                new Edge(2, 3)
        ));
        Graph G = new Graph(4, 5, edges);
        Karger karger = new Karger(G);

        System.out.println(karger.buildCut());
    }

}

























