package com.example.datastructures;

import com.example.ui.BinaryTreeVisualizer;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    public class TreeNode {
        int value;
        TreeNode leftChild, rightChild;

        public TreeNode(int val) {
            value = val;
            leftChild = rightChild = null;
        }
    }

    private TreeNode entryPoint;

    public BinaryTree() {
        entryPoint = null;
    }

    public void add(int val) {
        entryPoint = addNode(entryPoint, val);
    }

    private TreeNode addNode(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }

        if (val < current.value) {
            current.leftChild = addNode(current.leftChild, val);
        } else if (val > current.value) {
            current.rightChild = addNode(current.rightChild, val);
        }
        return current;
    }

    public void delete(int val) {
        entryPoint = deleteNode(entryPoint, val);
    }

    private TreeNode deleteNode(TreeNode current, int val) {
        if (current == null) {
            return null;
        }

        if (val < current.value) {
            current.leftChild = deleteNode(current.leftChild, val);
        } else if (val > current.value) {
            current.rightChild = deleteNode(current.rightChild, val);
        } else {
            // Node with only one child or no child
            if (current.leftChild == null) {
                return current.rightChild;
            } else if (current.rightChild == null) {
                return current.leftChild;
            }

            // Node with two children: Get the inorder successor (smallest in right subtree)
            current.value = findMin(current.rightChild);
            current.rightChild = deleteNode(current.rightChild, current.value);
        }
        return current;
    }

    private int findMin(TreeNode node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node.value;
    }

    public List<Integer> traverseInOrder() {
        List<Integer> result = new ArrayList<>();
        traverseInOrder(entryPoint, result);
        return result;
    }

    private void traverseInOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            traverseInOrder(node.leftChild, result);
            result.add(node.value);
            traverseInOrder(node.rightChild, result);
        }
    }

    public List<Integer> traversePreOrder() {
        List<Integer> result = new ArrayList<>();
        traversePreOrder(entryPoint, result);
        return result;
    }

    private void traversePreOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            traversePreOrder(node.leftChild, result);
            traversePreOrder(node.rightChild, result);
        }
    }

    public List<Integer> traversePostOrder() {
        List<Integer> result = new ArrayList<>();
        traversePostOrder(entryPoint, result);
        return result;
    }

    private void traversePostOrder(TreeNode node, List<Integer> result) {
        if (node != null) {
            traversePostOrder(node.leftChild, result);
            traversePostOrder(node.rightChild, result);
            result.add(node.value);
        }
    }

    public void visualize() {
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer(this);
        visualizer.showVisualization("Binary Tree Visualization");
    }
}

