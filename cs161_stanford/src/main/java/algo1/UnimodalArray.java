package algo1;

import java.util.Arrays;
import java.util.List;

/**
 * 2. You are a given a unimodal array of n distinct elements,
 * meaning that its entries are in increasing order up until
 * its maximum element, after which its elements are in
 * decreasing order. Give an algorithm to compute the maximum
 * element that runs in O(log n) time.
 */
public class UnimodalArray {

    private List<Integer> list;

    public UnimodalArray(List<Integer> list) {
        this.list = list;
    }

    public int max() {
        return max(0, list.size() - 1);
    }

    // helper methods
    private int max(int i, int j) {

        if (j - i == 1) {
            return Math.max(list.get(i), list.get(j));
        }

        // med == 0 only when i == 0 and j == 1 but
        // this is the case above
        int med = (i + j) / 2;

        if (list.get(med - 1) < list.get(med)) {
            // go right
            return max(med, j);
        } else {
            // go left
            return max(i, med);
        }

    }

    public static void main(String[] args) {
        UnimodalArray ua = new UnimodalArray(Arrays.asList(3, 5, 7, 6, 4, 2));
        System.out.println(ua.max());
    }
}























