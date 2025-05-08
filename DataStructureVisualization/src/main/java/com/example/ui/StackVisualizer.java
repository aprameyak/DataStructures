package com.example.datastructures.ui;

import com.example.datastructures.Stack;
import java.awt.*;

public class StackVisualizer extends DataStructureVisualizer {
    private Stack stack;
    private int startX;
    private int startY;

    public StackVisualizer(Stack stack) {
        this.stack = stack;
        this.startX = getWidth() / 2;
        this.startY = getHeight() - 50;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (stack == null) return;

        int containerWidth = 100;
        int containerHeight = 300;
        int containerX = startX - containerWidth / 2;
        int containerY = startY - containerHeight;
        
        g2d.setColor(LINE_COLOR);
        g2d.drawRect(containerX, containerY, containerWidth, containerHeight);

        Object[] elements = stack.toArray();
        int elementHeight = 40;
        int elementWidth = 80;
        int elementX = containerX + (containerWidth - elementWidth) / 2;
        int elementY = startY - elementHeight;

        for (Object element : elements) {
            drawRectangle(g2d, elementX, elementY, elementWidth, elementHeight, String.valueOf(element));
            elementY -= elementHeight + 5;
        }
    }
} 