package com.example.datastructures;

import com.example.ui.BinaryTreeVisualizer;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public class TreeNode {
        int value;
        TreeNode leftChild, rightChild;
        boolean color;

        public TreeNode(int val) {
            value = val;
            leftChild = rightChild = null;
            color = RED;
        }
    }

    private TreeNode root;

    public RedBlackTree() {
        root = null;
    }

    private boolean isRed(TreeNode node) {
        return node != null && node.color == RED;
    }

    private TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.rightChild;
        h.rightChild = x.leftChild;
        x.leftChild = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.leftChild;
        h.leftChild = x.rightChild;
        x.rightChild = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(TreeNode h) {
        h.color = RED;
        h.leftChild.color = BLACK;
        h.rightChild.color = BLACK;
    }

    public void add(int val) {
        root = addNode(root, val);
        root.color = BLACK;
    }

    private TreeNode addNode(TreeNode current, int val) {
        if (current == null) return new TreeNode(val);

        if (val < current.value) {
            current.leftChild = addNode(current.leftChild, val);
        } else if (val > current.value) {
            current.rightChild = addNode(current.rightChild, val);
        } else {
            return current;
        }

        if (isRed(current.rightChild) && !isRed(current.leftChild)) current = rotateLeft(current);
        if (isRed(current.leftChild) && isRed(current.leftChild.leftChild)) current = rotateRight(current);
        if (isRed(current.leftChild) && isRed(current.rightChild)) flipColors(current);

        return current;
    }

    public void delete(int val) {
        if (!isRed(root.leftChild) && !isRed(root.rightChild)) root.color = RED;
        root = deleteNode(root, val);
        if (root != null) root.color = BLACK;
    }

    private TreeNode deleteNode(TreeNode current, int val) {
        if (current == null) return null;

        if (val < current.value) {
            if (!isRed(current.leftChild) && !isRed(current.leftChild.leftChild))
                current = moveRedLeft(current);
            current.leftChild = deleteNode(current.leftChild, val);
        } else {
            if (isRed(current.leftChild)) current = rotateRight(current);
            if (val == current.value && current.rightChild == null) return null;
            if (!isRed(current.rightChild) && !isRed(current.rightChild.leftChild))
                current = moveRedRight(current);
            if (val == current.value) {
                current.value = findMin(current.rightChild);
                current.rightChild = deleteMin(current.rightChild);
            } else {
                current.rightChild = deleteNode(current.rightChild, val);
            }
        }
        return balance(current);
    }

    private TreeNode moveRedLeft(TreeNode h) {
        flipColors(h);
        if (isRed(h.rightChild.leftChild)) {
            h.rightChild = rotateRight(h.rightChild);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private TreeNode moveRedRight(TreeNode h) {
        flipColors(h);
        if (isRed(h.leftChild.leftChild)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private TreeNode balance(TreeNode h) {
        if (isRed(h.rightChild)) h = rotateLeft(h);
        if (isRed(h.leftChild) && isRed(h.leftChild.leftChild)) h = rotateRight(h);
        if (isRed(h.leftChild) && isRed(h.rightChild)) flipColors(h);
        return h;
    }

    private TreeNode deleteMin(TreeNode h) {
        if (h.leftChild == null) return null;
        if (!isRed(h.leftChild) && !isRed(h.leftChild.leftChild)) h = moveRedLeft(h);
        h.leftChild = deleteMin(h.leftChild);
        return balance(h);
    }

    private int findMin(TreeNode node) {
        while (node.leftChild != null) node = node.leftChild;
        return node.value;
    }

    public List<Integer> traverseInOrder() {
        List<Integer> result = new ArrayList<>();
        traverseInOrder(root, result);
        return result;
    }

    private void traverseInOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            traverseInOrder(node.leftChild, result);
            result.add(node.value);
            traverseInOrder(node.rightChild, result);
        }
    }

    public void visualize() {
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer(this);
        visualizer.showVisualization("Red-Black Tree Visualization");
    }
} 