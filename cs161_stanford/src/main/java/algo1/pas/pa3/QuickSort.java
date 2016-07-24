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

    // getters
    public List<Integer> getA() {
        return A;
    }

    // helper methods
    private void readFile(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        while (in.hasNextInt()) {
            A.add(in.nextInt());
        }
    }

    public static void main(String[] args) {

        String filename = "src/main/resources/simpleQuickSort.txt";
        QuickSort qs = new QuickSort(filename);
        System.out.println(qs.getA());
    }
}



















