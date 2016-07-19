package algo1.pas.pa1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 7/19/16.
 */
public class Inversion {

    public static final int SIZE = 100_000;

    private int[] a;
    private int[] aux;
    private long inversions;

    public Inversion(int[] a) {
        this.a = a;
        aux = new int[a.length];
    }
    public Inversion(String filename) {
        a = new int[SIZE];
        aux = new int[a.length];
        try {
            readArrayFromFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long getInversions() {
        return inversions;
    }

    private void merge(int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                                            a[k] = aux[j++];
                // counting inversions
                inversions += mid - i + 1;
            }
            else                            a[k] = aux[i++];
        }
    }

    // merge sort a[lo..hi] using auxiliary array aux[lo..hi]
    private void sort(int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(lo, mid);
        sort(mid + 1, hi);
        merge(lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     */
    public void sort() {
        sort(0, a.length-1);
    }

    // helper function
    private void readArrayFromFile(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        int i = 0;
        while (in.hasNextInt()) {
            a[i] = in.nextInt();
            i++;
        }
    }

    public static void main(String[] args) {

        String filename = "src/main/resources/IntegerArray.txt";
        Inversion inversion = new Inversion(filename);
        inversion.sort();
        System.out.println(inversion.inversions);

    }
}
