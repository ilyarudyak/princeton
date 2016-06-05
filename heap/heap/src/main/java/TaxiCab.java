import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyarudyak on 6/6/16.
 */
public class TaxiCab {

    private int N;
    private MinPQ<Integer> minHeap;
    private List<Integer> cubes;

    public TaxiCab(int N) {
        this.N = N;
        minHeap = new MinPQ<>();

        cubes = new ArrayList<>();
        cubes.add(0);
        for (int i = 1; i <= N; i++) {
            cubes.add((int) Math.pow(i, 3));
        }
    }

    public int findTaxiCabNumber() {
        for (int i = 1; i < N; i++) {
            int res;
            if ((res = fillMinHeap(i)) != -1) {
                if (res != 1729) {
                    return res;
                }
            }
        }
        return -1;
    }

    // helper methods
    private int fillMinHeap(int n) {
        int res;
        if (n != 1 && n != N) {
            if ((res = delBelow(cubes.get(n) + cubes.get(n + 1))) != -1) {
                return res;
            }
        }
        for (int i = n + 1; i <= N; i++) {
            minHeap.insert(cubes.get(n) + cubes.get(i));
        }
        return -1;
    }
    private int delBelow(int n) {
        while (true) {
            int min = minHeap.min();
            if (min == n) {
                return min;
            } else if (min < n){
                minHeap.delMin();
            } else {
                return -1;
            }
        }
    }


    public static void main(String[] args) {

        TaxiCab taxi = new TaxiCab(1000);
        System.out.println(taxi.findTaxiCabNumber());

    }
}




















