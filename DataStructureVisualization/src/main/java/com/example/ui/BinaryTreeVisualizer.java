package com.example.ui;

import com.example.datastructures.BinaryTree;
import java.awt.*;

public class BinaryTreeVisualizer extends DataStructureVisualizer {
    private BinaryTree tree;
    private int startX;
    private int startY;

    public BinaryTreeVisualizer(BinaryTree tree) {
        this.tree = tree;
        this.startX = getWidth() / 2;
        this.startY = 50;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (tree == null) return;
        drawTree(g2d, tree.entryPoint, startX, startY, getWidth() / 4);
    }

    private void drawTree(Graphics2D g2d, BinaryTree.TreeNode node, int x, int y, int offset) {
        if (node == null) return;

        drawNode(g2d, x, y, String.valueOf(node.value));

        if (node.leftChild != null) {
            int leftX = x - offset;
            int leftY = y + VERTICAL_GAP;
            drawLine(g2d, x, y + NODE_RADIUS, leftX, leftY - NODE_RADIUS);
            drawTree(g2d, node.leftChild, leftX, leftY, offset / 2);
        }

        if (node.rightChild != null) {
            int rightX = x + offset;
            int rightY = y + VERTICAL_GAP;
            drawLine(g2d, x, y + NODE_RADIUS, rightX, rightY - NODE_RADIUS);
            drawTree(g2d, node.rightChild, rightX, rightY, offset / 2);
        }
    }
} 