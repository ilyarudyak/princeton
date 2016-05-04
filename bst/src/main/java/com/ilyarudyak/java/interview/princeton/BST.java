package com.ilyarudyak.java.interview.princeton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
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

    // ----------------- utility ------------------------

    // return number of key-value pairs in BST rooted at x
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) { return 0; }
        return Math.max(height(x.left), height(x.right)) + 1;
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

    // distance from root to Node with given key (# of edges)
    public int path(Key key) {
        return path(root, key);
    }
    // distance from Node x to Node with given key
    private int path(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) { return 0; }
        else if (cmp < 0) { return path(x.left, key) + 1; }
        else { return path(x.right, key) + 1; }
    }
    // the average number of compares required by a random search hit in a given BST
    public double avgCompares() {

        Iterable<Key> keys = levelOrder();
        int pathSum = 0, count = 0;
        for (Key key : keys) {
            System.out.println(key + " ");
            pathSum += path(key) + 1;
            count++;
        }
        return (double) pathSum / count;
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

    public Key floor(Key key) {
        if (root == null) {
            return null;
        }

        Node x = floor(root, key);
        if (x != null) {
            return x.key;
        }

        return null;
    }
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
        } else /* cmp > 0 */ {
            Node y = floor(x.right, key);
            if (y == null) {
                return x;
            } else {
                return y;
            }
        }
    }

    public Key select(int k) {
        if (root == null) {
            return null;
        }
        Node x = select(root, k);
        if (x != null) {
            return x.key;
        }
        return null;
    }
    private Node select(Node x, int k) {

        if (x == null)          { return null; }

        int t = size(x.left);

        if      (t == k)        { return x; }
        else if (t >  k)        { return select(x.left, k); }
        else /* (t <  k) */     { return select(x.right, k - t - 1); }
    }

    public int rank(Key key) {
        if (root == null) {
            return 0;
        }
        return rank(root, key);
    }
    private int rank(Node x, Key key) {
        if (x == null) { return 0; }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) { return size(x.left); }
        else if (cmp < 0) { return rank(x.left, key); }
        else { return rank(x.right, key) + size(x.left) + 1; }
    }

    // ----------------- traversals ---------------------

    public void inorder() {
        inorder(root);
    }
    private void inorder(Node x) {
        if (x == null) { return; }
        inorder(x.left);
        System.out.print(x.key + " ");
        inorder(x.right);
    }

    public Iterable<Key> levelOrder() {

        Queue<Key> keys = new LinkedList<>();
        if (root == null) { return keys; }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node x = nodes.remove();
            if (x != null) {
                keys.add(x.key);
                nodes.add(x.left);
                nodes.add(x.right);
            }
        }
        return keys;
    }

    public void printByLevels() {
        int level = 0;
        System.out.print("level 0: ");
        for (Key key : levelOrder()) {
            if (path(key) == level) {
                System.out.print(key + " ");
            } else {
                level++;
                System.out.println();
                System.out.print("level " + level + ": " + key + " ");
            }
        }
        System.out.println();
    }


    // ----------------- helper functions ----------------

    public static BST<String, Integer> buildSampleBST() throws FileNotFoundException {
        BST<String, Integer> bst = new BST<String, Integer>();
        Scanner in = new Scanner(new File("src/main/resources/tinyST.txt"));
        for (int i = 0; in.hasNext(); i++) {
            String key = in.next();
            bst.put(key, i);
        }
        return bst;
    }
    public static BST<String, Integer> buildBalancedBST() throws FileNotFoundException {
        BST<String, Integer> bst = new BST<String, Integer>();
        Scanner in = new Scanner(new File("src/main/resources/balanced.txt"));
        for (int i = 0; in.hasNext(); i++) {
            String key = in.next();
            bst.put(key, i);
        }
        return bst;
    }

    // ------------------ interview -----------------------

    // 4.1 laakman
    public boolean isBalanced() {
        if (root == null) { return true; }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node x = nodes.remove();
            if (!isBalanced(x)) {
                return false;
            }
            if (x != null) {
                nodes.add(x.left);
                nodes.add(x.right);
            }
        }
        return true;
    }
    private boolean isBalanced(Node x) {
        if (x == null) { return true; }
        return Math.abs(height(x.left) - height(x.right)) <= 1;
    }

    // 4.5 laakman
    private boolean isBST() {
        return isBST(root, null, null);
    }
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) { return true; }
        if (    (min != null && min.compareTo(x.key) >= 0) ||
                (max != null && max.compareTo(x.key) <= 0) ) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }


    public static void main(String[] args) throws FileNotFoundException {

        BST<String, Integer> bst = buildSampleBST();

        bst.printByLevels();
    }
}























