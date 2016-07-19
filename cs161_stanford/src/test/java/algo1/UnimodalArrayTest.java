package algo1;

import algo1.challenge.UnimodalArray;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by ilyarudyak on 7/17/16.
 */
public class UnimodalArrayTest {

    private static final int SIZE = 10;
    private static final int ITERATION = 100;
    private static final int BASE = 1000;
    private List<List<Integer>> lists;
    private Random random = new Random(new Date().getTime());
    private List<UnimodalArray> arrays;

    @Before
    public void setUp() throws Exception {
        lists = new ArrayList<>();
        arrays = new ArrayList<>();
        for (int i = 0; i < ITERATION; i++) {
            List<Integer> l = buildUnimodalList();
            lists.add(l);
            arrays.add(new UnimodalArray(l));
        }
    }

    private List<Integer> buildUnimodalList() {
        List<Integer> list = new ArrayList<>();

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        int size = random.nextInt(SIZE);
        for (int i = 0; i < size; i++) {
            l1.add(random.nextInt(BASE));
        }
        for (int i = 0; i < SIZE - size - 1; i++) {
            l2.add(random.nextInt(BASE));
        }

        int max1 = 0, max2 = 0;
        if (!l1.isEmpty()) {
            max1 = Collections.max(l1);
        }
        if (!l2.isEmpty()) {
            max2 = Collections.max(l2);
        }

        Collections.sort(l1);
        Collections.sort(l2, Collections.<Integer>reverseOrder());

        list.addAll(l1);
        list.add(max1 + max2);
        list.addAll(l2);

//        System.out.println(list);
        return list;
    }

    @org.junit.Test
    public void testMax() throws Exception {
        for (int i = 0; i < ITERATION; i++) {
            assertEquals((int)Collections.max(lists.get(i)), arrays.get(i).max());
        }
    }
}