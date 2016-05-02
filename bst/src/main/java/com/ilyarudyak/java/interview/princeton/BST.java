package com.ilyarudyak.java.interview.princeton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 5/1/16.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    // ----------------- get and put ---------------------

    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.val;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.val = val;
        } else if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else {
            x.right = put(x.right, key, val);
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public int getSize(Key key) {
        Node cur  = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return cur.N;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return 0;
    }

    // ----------------- rank functions ------------------

    public Key min() {
        return min(root).key;
    }
    private Node min(Node x) {
        if (x == null) {
            return null;
        }

        if (x.left == null) {
            return x;
        }

        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }
    private Node max(Node x) {
        if (x == null) {
            return null;
        }

        if (x.right == null) {
            return x;
        }

        return max(x.right);
    }

    // ----------------- helper functions ----------------

    private static BST buildSampleBST() throws FileNotFoundException {
        BST<String, Integer> bst = new BST<String, Integer>();
        Scanner in = new Scanner(new File("src/main/resources/tinyST.txt"));
        for (int i = 0; in.hasNext(); i++) {
            String key = in.next();
            bst.put(key, i);
        }
//        in.close();
//
//        in = new Scanner(new File("src/main/resources/tinyST.txt"));
//        while (in.hasNext()) {
//            String key = in.next();
//            System.out.println(key + ":" + bst.get(key) + ":" + bst.getSize(key));
//        }
        return bst;
    }


    public static void main(String[] args) throws FileNotFoundException {

        BST bst = buildSampleBST();

        System.out.println("min=" + bst.min() + " max=" + bst.max());

    }
}























