package com.ilyarudyak.java.interview.princeton;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by ilyarudyak on 5/2/16.
 */
public class BSTTest {

    private BST<String, Integer> bst;

    @Before
    public void setUp() throws Exception {
        bst = BST.buildSampleBST();


    }

    @Test
    public void testPath() throws Exception {

        assertEquals(0, bst.path("S"));
        assertEquals(1, bst.path("E"));
        assertEquals(2, bst.path("A"));
        assertEquals(3, bst.path("C"));
        assertEquals(4, bst.path("M"));
        assertEquals(5, bst.path("L"));
    }

    @Test
    public void testRank() throws Exception {

        assertEquals(0, bst.rank("A"));
        assertEquals(1, bst.rank("C"));
        assertEquals(2, bst.rank("E"));
        assertEquals(3, bst.rank("H"));
        assertEquals(4, bst.rank("L"));
        assertEquals(5, bst.rank("M"));
        assertEquals(6, bst.rank("P"));
        assertEquals(7, bst.rank("R"));
        assertEquals(8, bst.rank("S"));
        assertEquals(9, bst.rank("X"));

    }
}