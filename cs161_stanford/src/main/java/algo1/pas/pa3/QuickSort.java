package algo1.pas.pa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 7/24/16.
 */
public class QuickSort {

    private List<Integer> A;

    public QuickSort(String filename) {
        A = new ArrayList<>();
        try {
            readFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        sort(0, A.size() - 1);
    }

    // getters
    public List<Integer> getA() {
        return A;
    }

    // helper methods
    private void sort(int l, int r) {
        if (l >= r) return;
        int p = partition(l, r);
        sort(l, p - 1);
        sort(p + 1, r);
    }
    private int partition(int l, int r) {
        System.out.println("l=" + l + " r=" + r);
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

    public static void main(String[] args) {

        String filename = "src/main/resources/simpleQuickSort.txt";
        QuickSort qs = new QuickSort(filename);
        qs.sort();
        System.out.println(qs.getA());
    }
}



















