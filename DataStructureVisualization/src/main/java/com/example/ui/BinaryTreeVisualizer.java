package com.example.ui;

import com.example.datastructures.BinaryTree;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeVisualizer extends DataStructureVisualizer {
    private BinaryTree tree;
    private int startX;
    private int startY;
    private Map<BinaryTree.TreeNode, Point> nodePositions;
    private BinaryTree.TreeNode highlightedNode;
    private static final int MIN_HORIZONTAL_GAP = 50;
    private static final int VERTICAL_GAP = 60;
    private static final int NODE_RADIUS = 20;
    private static final Color HIGHLIGHT_COLOR = new Color(255, 200, 0);
    private static final Color NORMAL_COLOR = new Color(100, 149, 237);
    private static final Color LINE_COLOR = new Color(70, 70, 70);

    public BinaryTreeVisualizer(BinaryTree tree) {
        this.tree = tree;
        this.nodePositions = new HashMap<>();
        this.startX = getWidth() / 2;
        this.startY = 50;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (tree == null) return;
        nodePositions.clear();
        int depth = calculateTreeDepth(tree.entryPoint);
        int horizontalGap = Math.max(MIN_HORIZONTAL_GAP, getWidth() / (int)Math.pow(2, depth + 1));
        drawTree(g2d, tree.entryPoint, startX, startY, horizontalGap);
    }

    private int calculateTreeDepth(BinaryTree.TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(
            calculateTreeDepth(node.leftChild),
            calculateTreeDepth(node.rightChild)
        );
    }

    private void drawTree(Graphics2D g2d, BinaryTree.TreeNode node, int x, int y, int offset) {
        if (node == null) return;

        nodePositions.put(node, new Point(x, y));

        if (node.leftChild != null) {
            int leftX = x - offset;
            int leftY = y + VERTICAL_GAP;
            drawLine(g2d, x, y + NODE_RADIUS, leftX, leftY - NODE_RADIUS);
        }

        if (node.rightChild != null) {
            int rightX = x + offset;
            int rightY = y + VERTICAL_GAP;
            drawLine(g2d, x, y + NODE_RADIUS, rightX, rightY - NODE_RADIUS);
        }

        drawNode(g2d, x, y, String.valueOf(node.value), node == highlightedNode);

        if (node.leftChild != null) {
            int leftX = x - offset;
            int leftY = y + VERTICAL_GAP;
            drawTree(g2d, node.leftChild, leftX, leftY, offset / 2);
        }

        if (node.rightChild != null) {
            int rightX = x + offset;
            int rightY = y + VERTICAL_GAP;
            drawTree(g2d, node.rightChild, rightX, rightY, offset / 2);
        }
    }

    private void drawNode(Graphics2D g2d, int x, int y, String value, boolean isHighlighted) {
        g2d.setColor(isHighlighted ? HIGHLIGHT_COLOR : NORMAL_COLOR);
        g2d.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(value);
        int textHeight = fm.getHeight();
        g2d.drawString(value, x - textWidth/2, y + textHeight/4);
    }

    private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(LINE_COLOR);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void highlightNode(BinaryTree.TreeNode node) {
        this.highlightedNode = node;
        repaint();
    }

    public void clearHighlight() {
        this.highlightedNode = null;
        repaint();
    }
} 