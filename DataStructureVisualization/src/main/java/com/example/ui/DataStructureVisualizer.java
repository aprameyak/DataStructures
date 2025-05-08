package com.example.datastructures.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class DataStructureVisualizer extends JPanel {
    protected static final int NODE_RADIUS = 30;
    protected static final int HORIZONTAL_GAP = 50;
    protected static final int VERTICAL_GAP = 50;
    protected static final Color NODE_COLOR = new Color(70, 130, 180);
    protected static final Color LINE_COLOR = new Color(100, 100, 100);
    protected static final Color TEXT_COLOR = Color.WHITE;

    public DataStructureVisualizer() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    public void showVisualization(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawDataStructure(g2d);
    }

    protected abstract void drawDataStructure(Graphics2D g2d);

    protected void drawNode(Graphics2D g2d, int x, int y, String value) {
        g2d.setColor(NODE_COLOR);
        g2d.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        
        g2d.setColor(LINE_COLOR);
        g2d.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        
        g2d.setColor(TEXT_COLOR);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(value);
        int textHeight = fm.getHeight();
        g2d.drawString(value, x - textWidth/2, y + textHeight/4);
    }

    protected void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(LINE_COLOR);
        g2d.drawLine(x1, y1, x2, y2);
    }
} 