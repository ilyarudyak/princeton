package algo1.pas.pa2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Idea of implementation is from here:
 * http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/
 * UF is from here: http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/UF.java.html.
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

        @Override
        public String toString() {
            return "(" + src +
                    ", " + dst +
                    ")";
        }
    }
    /**
     * We represent Graph with its edges.
     * We don't eliminate here the same edges like (0, 1) and (1,0),
     * but we do this when create a Graph from file.
     *
     * We don't use multiple edges and self-loops. We also don't use
     * combined nodes - we use UF for these purposes.
     */
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
    public Karger(String filename) {
        G = buildGraphFromFile(filename);
        uf = new UF(G.getV());
        cV = G.getV();
        random = new Random(new Date().getTime());
    }

    public int buildCut() {

        // wait until we have only 2 combined nodes
        // we may also use count of UF
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

        int i = random.nextInt(G.getE());

        Edge e = G.getEdges().get(i);
        int src = e.getSrc();
        int dst = e.getDst();

        // this is key idea - we don't eliminate nodes
        // we just wait until we hit non-contracted edge
        // (between non-connected in UF nodes)
        if (!uf.connected(src, dst)) {
            uf.union(src, dst);
            cV--;
        }

    }
    private Graph buildGraphFromFile(String filename) {
        int v = 0, e = 0;
        List<Edge> edges = new ArrayList<>();

        Scanner in = null;
        try {
            in = new Scanner(new File(filename));
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                v++;
                e += extractEdges(line, edges);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

//        System.out.println(edges);
        return new Graph(v, e, edges);
    }
    private int extractEdges(String line, List<Edge> edges) {
        String[] splitLine = line.split("\\s+");
        int e = 0;
        // provided file start numbering with 1
        // but our UF need to start from 0
        int src = Integer.parseInt(splitLine[0]) - 1;
        for (int i = 1; i < splitLine.length; i++) {
            int dst = Integer.parseInt(splitLine[i]) - 1;
            if (src < dst) {
                edges.add(new Edge(src, dst));
                e++;
            }
        }
        return e;
    }


    public static void main(String[] args) {

//        List<Edge> edges = new ArrayList<>(Arrays.asList(
//                new Edge(0, 1), new Edge(0, 3),
//                new Edge(1, 2), new Edge(1, 3),
//                new Edge(2, 3)
//        ));
//        Graph G = new Graph(4, 5, edges);
//        Karger karger = new Karger(G);

        int min = 50;
        for (int i = 0; i < 100; i++) {
            Karger karger = new Karger("src/main/resources/kargerMinCut.txt");
            int cut = karger.buildCut();
            if (cut < min) {
                min = cut;
            }
        }
        System.out.println(min);
    }

}

























