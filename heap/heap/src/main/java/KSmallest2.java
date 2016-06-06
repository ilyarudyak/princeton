import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * here we solve different problem: if k-th smallest element
 * less than x; there is a solution in O(k)
 */
public class KSmallest2 {

    private MinPQ<Integer> minHeap;
    private int x;
    private int k;
    private int count = 0;

    public KSmallest2(String filename, int x, int k) {
        this.x = x;
        this.k = k;
        minHeap = new MinPQ<>();
        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNext()) {
                minHeap.insert(in.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean check(int index) {

        if (index > minHeap.size()) {
            return false;
        }

        if (minHeap.getByIndex(index) < x) {
            count++;
            if (count >= k) {
                return true;
            }

            return  check(2 * index) ||
                    check(2 * index + 1);
        }

        return false;
    }



    public static void main(String[] args) {
        KSmallest2 ks = new KSmallest2("src/main/resources/minheap.txt", 18, 5);
        System.out.println(ks.minHeap);

        System.out.println(ks.check(1));
    }
}
