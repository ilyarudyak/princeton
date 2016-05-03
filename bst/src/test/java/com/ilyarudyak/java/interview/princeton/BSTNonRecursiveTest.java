package com.ilyarudyak.java.interview.princeton;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilyarudyak on 5/3/16.
 */
public class BSTNonRecursiveTest {

    private BSTNonRecursive<String, Integer> bst;

    @Before
    public void setUp() throws Exception {
        bst = BSTNonRecursive.buildSampleBST();


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
}