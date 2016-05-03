package com.ilyarudyak.java.interview.princeton;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by ilyarudyak on 5/3/16.
 */
public class BSTHeightTest {

    private BSTHeight<String, Integer> bst;

    @Before
    public void setUp() throws Exception {
        bst = BSTHeight.buildSampleBST();


    }

    @Test
    public void testGetHeight() throws Exception {

        Scanner in = new Scanner(new File("src/main/resources/tinyST.txt"));
        while (in.hasNext()) {
            String key = in.next();
            assertEquals(bst.getHeight(key), bst.getHeight2(key));
        }
    }
}