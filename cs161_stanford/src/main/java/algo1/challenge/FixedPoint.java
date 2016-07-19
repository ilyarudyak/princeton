package algo1.challenge;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ilyarudyak on 7/18/16.
 */
public class FixedPoint {

    private List<Integer> A;

    public FixedPoint(List<Integer> a) {
        A = a;
    }

    public int fixedPoint() {
        return fixedPoint(0, A.size() - 1);
    }

    private int fixedPoint(int i, int j) {

        if (i > j) {
            return -1;
        }

        int med = (i + j) / 2;

        if (A.get(i) == i) {
            return i;
        } else if (A.get(i) < i) {
                // go right
                return fixedPoint(med + 1, j);
        } else {
                // go left
                return fixedPoint(i, med - 1);
        }
    }

    public static void main(String[] args) {

        FixedPoint fp = new FixedPoint(Arrays.asList(-5, -3, 0, 1, 10));
        System.out.println(fp.fixedPoint());

        FixedPoint fp2 = new FixedPoint(Arrays.asList(-5, -3, 0, 1, 4));
        System.out.println(fp2.fixedPoint());
    }


}


























