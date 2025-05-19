package com.example.datastructures;

import com.example.ui.BinaryTreeVisualizer;
import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    public class TreeNode {
        int value;
        TreeNode leftChild, rightChild;
        int height;

        public TreeNode(int val) {
            value = val;
            leftChild = rightChild = null;
            height = 1;
        }
    }

    private TreeNode root;

    public AVLTree() {
        root = null;
    }

    private int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(TreeNode node) {
        return node == null ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.leftChild;
        TreeNode T2 = x.rightChild;
        x.rightChild = y;
        y.leftChild = T2;
        y.height = Math.max(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        x.height = Math.max(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.rightChild;
        TreeNode T2 = y.leftChild;
        y.leftChild = x;
        x.rightChild = T2;
        x.height = Math.max(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = Math.max(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        return y;
    }

    public void add(int val) {
        root = addNode(root, val);
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

        current.height = 1 + Math.max(getHeight(current.leftChild), getHeight(current.rightChild));
        int balance = getBalance(current);

        if (balance > 1 && val < current.leftChild.value) return rightRotate(current);
        if (balance < -1 && val > current.rightChild.value) return leftRotate(current);
        if (balance > 1 && val > current.leftChild.value) {
            current.leftChild = leftRotate(current.leftChild);
            return rightRotate(current);
        }
        if (balance < -1 && val < current.rightChild.value) {
            current.rightChild = rightRotate(current.rightChild);
            return leftRotate(current);
        }

        return current;
    }

    public void delete(int val) {
        root = deleteNode(root, val);
    }

    private TreeNode deleteNode(TreeNode current, int val) {
        if (current == null) return null;

        if (val < current.value) {
            current.leftChild = deleteNode(current.leftChild, val);
        } else if (val > current.value) {
            current.rightChild = deleteNode(current.rightChild, val);
        } else {
            if (current.leftChild == null) return current.rightChild;
            if (current.rightChild == null) return current.leftChild;
            current.value = findMin(current.rightChild);
            current.rightChild = deleteNode(current.rightChild, current.value);
        }

        if (current == null) return null;

        current.height = 1 + Math.max(getHeight(current.leftChild), getHeight(current.rightChild));
        int balance = getBalance(current);

        if (balance > 1 && getBalance(current.leftChild) >= 0) return rightRotate(current);
        if (balance > 1 && getBalance(current.leftChild) < 0) {
            current.leftChild = leftRotate(current.leftChild);
            return rightRotate(current);
        }
        if (balance < -1 && getBalance(current.rightChild) <= 0) return leftRotate(current);
        if (balance < -1 && getBalance(current.rightChild) > 0) {
            current.rightChild = rightRotate(current.rightChild);
            return leftRotate(current);
        }

        return current;
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
        visualizer.showVisualization("AVL Tree Visualization");
    }
} 