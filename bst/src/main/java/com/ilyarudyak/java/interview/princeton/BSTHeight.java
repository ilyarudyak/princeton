package com.ilyarudyak.java.interview.princeton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 5/3/16.
 */
public class BSTHeight<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree
        private int height;        // height of the tree

        public Node(Key key, Value val, int N, int h) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.height = h;
        }
    }

    public int size() {
        return size(root);
    }

    // ----------------- utility ------------------------

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) { return -1; }
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    private Node getNode(Key key) {
        Node cur  = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return cur;
            } else if (cmp < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }
    public int getSize(Key key) {
        Node cur  = getNode(key);
        if (cur != null) {
            return cur.N;
        }
        return 0;
    }
    public int getHeight(Key key) {
        Node cur  = getNode(key);
        if (cur != null) {
            return height(cur);
        }
        return 0;
    }
    public int getHeight2(Key key) {
        Node cur  = getNode(key);
        if (cur != null) {
            return cur.height;
        }
        return 0;
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
            return new Node(key, val, 1, 0);
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
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // ----------------- helper functions ----------------

    public static BSTHeight<String, Integer> buildSampleBST() throws FileNotFoundException {
        BSTHeight<String, Integer> bst = new BSTHeight<>();
        Scanner in = new Scanner(new File("src/main/resources/tinyST.txt"));
        for (int i = 0; in.hasNext(); i++) {
            String key = in.next();
            bst.put(key, i);
        }
        return bst;
    }


    public static void main(String[] args) throws FileNotFoundException {

        BSTHeight<String, Integer> bst = buildSampleBST();

        System.out.println("S:" + bst.get("S") + ":" + bst.getHeight2("S"));
        System.out.println("X:" + bst.get("X") + ":" + bst.getHeight2("X"));
    }
}
















