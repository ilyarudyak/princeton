package com.ilyarudyak.java.interview.princeton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BSTNonRecursive<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public Value get(Key key) {
        Node cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return cur.val;
            } else if (cmp < 0) {
                cur = cur.left;
            } else /* if (cmp > 0) */ {
                cur = cur.right;
            }
        }
        return null;
    }
    public void put(Key key, Value val) {

        if (root == null) {
            root = new Node(key, val);
            return;
        }

        Node cur = root;
        Node prev = root;
        while (cur != null) {

            prev = cur;
            int cmp = key.compareTo(cur.key);

            if (cmp == 0) {
                cur.val = val;
                return;
            } else if (cmp < 0) {
                cur = cur.left;
            } else /* (cmp > 0) */{
                cur = cur.right;
            }
        }

        int cmp = key.compareTo(prev.key);
        if (cmp == 0) {
            prev.val = val;
        } else if (cmp < 0) {
            prev.left = new Node(key, val);
        } else {
            prev.right = new Node(key, val);
        }

    }
    public void put2(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }

        Node cur = root;
        while (true) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                cur.val = val;
                return;
            } else if (cmp < 0) {
                if (cur.left == null) {
                    cur.left = new Node(key, val);
                    return;
                }
                cur = cur.left;
            } else /* if (cmp > 0) */ {
                if (cur.right == null) {
                    cur.right = new Node(key, val);
                    return;
                }
                cur = cur.right;
            }
        }
    }

    public Key min() {
        if (root == null) {
            return null;
        }

        Node cur = root;
        while (true) {
            if (cur.left == null) {
                return cur.key;
            }
            cur = cur.left;
        }
    }
    public Key max() {
        if (root == null) {
            return null;
        }

        Node cur = root;
        while (true) {
            if (cur.right == null) {
                return cur.key;
            }
            cur = cur.right;
        }
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














