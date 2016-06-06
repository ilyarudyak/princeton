import javafx.util.Pair;

import java.util.Comparator;

/**
 * find k-th smallest element in a min heap
 */
public class KSmallest {

    private MinPQ<Integer> minHeap;
    private MinPQ<Pair<Integer, Integer>> minHeap2;

    public KSmallest() {

        minHeap = new MinPQ<>();
        minHeap2 = new MinPQ<>(10, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < 10; i++) {
            minHeap.insert((1 + i) * 10);
        }
    }

    public int findKSmallest(int k) {
        int count = 0;
        int min = minHeap.min();
        minHeap2.insert(new Pair<>(min, 1));

        while (true) {

            Pair<Integer, Integer> pair = minHeap2.delMin();

            count++;
            if (count == k) {
                return pair.getKey();
            }

            int index = pair.getValue();
            Integer a = minHeap.getByIndex(2 * index);
            Integer b = minHeap.getByIndex(2 * index + 1);
            if (a != null) minHeap2.insert(new Pair<>(a, 2 * index));
            if (b != null) minHeap2.insert(new Pair<>(b, 2 * index + 1));
        }
    }

    public static void main(String[] args) {

        KSmallest ks = new KSmallest();
        System.out.println(ks.minHeap);
        System.out.println(ks.findKSmallest(7));
    }
}





















