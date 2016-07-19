package algo1.challenge;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilyarudyak on 7/17/16.
 */
public class SecondLargest {

    private List<Pair<Integer, List<Integer>>> evenList;
    private List<Pair<Integer, List<Integer>>> oddList;
    private List<Pair<Integer, List<Integer>>> curFromList;
    private List<Pair<Integer, List<Integer>>> curToList;
    private boolean isEven = true;
    private int size;

    public SecondLargest(List<Integer> list) {
        evenList = new ArrayList<>();
        for (Integer n: list) {
            evenList.add(new Pair<Integer, List<Integer>>(
                    n, new ArrayList<Integer>()
            ));
        }
        oddList = new ArrayList<>();
        size = list.size();
    }

    public int secondMax() {
        while (size > 1) {
            tournament();
        }
        System.out.println(curToList);
        return Collections.max(curToList.get(0).getValue());
    }

    private void tournament() {

        int n1, n2;
        Pair<Integer, List<Integer>> p1, p2;
        List<Integer> l1, l2;

        if (isEven) {
            curFromList = evenList;
            curToList = oddList;
        } else {
            curFromList = oddList;
            curToList = evenList;
        }

        for (int i = 0; i < size; i += 2) {

            p1 = curFromList.get(i); p2 = curFromList.get(i + 1);
            n1 = p1.getKey(); n2 = p2.getKey();

            if (n1 > n2) {
                l1 = p1.getValue(); l1.add(n2);
                curToList.add( new Pair<>(n1, l1) );
            } else {
                l2 = p2.getValue(); l2.add(n1);
                curToList.add( new Pair<>(n2, l2) );
            }
        }

        isEven = !isEven;
        curFromList.clear();
        size = curToList.size();

    }

    public static void main(String[] args) {
        SecondLargest sl = new SecondLargest(Arrays.asList(1, 3, 5, 2, 7, 4, 8, 9));
        System.out.println(sl.secondMax());
    }
}























