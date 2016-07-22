package algo1.pas.pa5;

/**
 * Created by ilyarudyak on 7/21/16.
 */
public class WeightedEdge {
    private int src;
    private int dst;
    private int weight;

    public WeightedEdge(int src, int dst, int weight) {
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
    public int getOther (int v) {
        return v == src ? dst : src;
    }
}
