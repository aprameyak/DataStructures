package com.example.datastructures;

import com.example.datastructures.ui.BinaryTreeVisualizer;

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
            if (current.leftChild == null) {
                return current.rightChild;
            } else if (current.rightChild == null) {
                return current.leftChild;
            }
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

    private void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.leftChild);
            System.out.print(node.value + " ");
            traverseInOrder(node.rightChild);
        }
    }

    public void traverseInOrder() {
        traverseInOrder(entryPoint);
    }

    public void visualize() {
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer(this);
        visualizer.showVisualization("Binary Tree Visualization");
    }
}
