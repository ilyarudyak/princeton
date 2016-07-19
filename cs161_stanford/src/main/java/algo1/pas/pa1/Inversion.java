package algo1.pas.pa1;

import java.util.Arrays;

/**
 * Created by ilyarudyak on 7/19/16.
 */
public class Inversion {

    private int[] a;
    private int[] aux;
    private int inversions;

    public Inversion(int[] a) {
        this.a = a;
        aux = new int[a.length];
    }

    public int getInversions() {
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

    public static void main(String[] args) {

        int[] a = {1, 3, 5, 2, 4, 6};
        Inversion inversion = new Inversion(a);
        inversion.sort();
        System.out.println(Arrays.toString(inversion.a));
        System.out.println(inversion.inversions);

    }
}
