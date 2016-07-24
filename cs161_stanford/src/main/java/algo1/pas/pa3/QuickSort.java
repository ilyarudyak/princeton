package algo1.pas.pa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by ilyarudyak on 7/24/16.
 */
public class QuickSort {

    private List<Integer> A;
    private int comp;
    private Random random;

    public QuickSort(String filename) {
        A = new ArrayList<>();
        try {
            readFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        random = new Random(new Date().getTime());
    }

    public void sort() {
        sort(0, A.size() - 1);
    }

    // getters
    public List<Integer> getA() {
        return A;
    }
    public int getComp() {
        return comp;
    }

    // helper methods
    private void sort(int l, int r) {
        if (l >= r) return;
        comp += r - l;
        int k = partitionFirst(l, r);
//        int k = partitionLast(l, r);
//        int k = partitionMedianOfThree(l, r);
//        int k = partitionRandom(l, r);
        sort(l, k - 1);
        sort(k + 1, r);
    }
    private void swap (int i, int j) {
        int swap = A.get(i);
        A.set(i, A.get(j));
        A.set(j, swap);
    }
    private void readFile(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        while (in.hasNextInt()) {
            A.add(in.nextInt());
        }
    }
    private boolean isSorted() {
        for (int i = 1; i < A.size(); i++)
            if (A.get(i) < A.get(i - 1)) return false;
        return true;
    }

    // ---------------- partitions ------------------

    // always using the *first* element of the given array as the pivot element
    private int partitionFirst(int l, int r) {
//        System.out.println("l=" + l + " r=" + r);
        int p = A.get(l);
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (A.get(j) < p) {
                swap(i, j);
                i++;
            }
        }
        swap(i - 1, l);
        return i - 1;
    }
    // always using the *final* element of the given array as the pivot element
    private int partitionLast(int l, int r) {
        swap(l, r);
        return partitionFirst(l, r);
    }
    // using the "median-of-three" pivot rule
    private int partitionMedianOfThree(int l, int r) {
        swap(l, getMedianOfThree(l, r));
        return partitionFirst(l, r);
    }
    private int partitionRandom(int l, int r) {
        swap(l, random.nextInt(r - l + 1) + l);
        return partitionFirst(l, r);
    }
    private int getMedianOfThree(int l, int r) {
        int mid = (l + r) / 2;
        int[] a = {l, r, mid};
        Arrays.sort(a);
        return a[1];
    }


    public static void main(String[] args) {

        String filename = "src/main/resources/simpleQuickSort.txt";
        QuickSort qs = new QuickSort(filename);
        qs.sort();
        System.out.println(qs. isSorted());
        System.out.println(qs.getComp());
    }
}



















