package com.example.datastructures.ui;

import com.example.datastructures.Queue;
import java.awt.*;

public class QueueVisualizer extends DataStructureVisualizer {
    private Queue queue;
    private int startX;
    private int startY;

    public QueueVisualizer(Queue queue) {
        this.queue = queue;
        this.startX = 50;
        this.startY = getHeight() / 2;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (queue == null) return;

        int containerWidth = 600;
        int containerHeight = 100;
        int containerX = startX;
        int containerY = startY - containerHeight / 2;
        
        g2d.setColor(LINE_COLOR);
        g2d.drawRect(containerX, containerY, containerWidth, containerHeight);

        Object[] elements = queue.toArray();
        int elementWidth = 80;
        int elementHeight = 60;
        int elementX = containerX + 20;
        int elementY = containerY + (containerHeight - elementHeight) / 2;

        for (Object element : elements) {
            drawRectangle(g2d, elementX, elementY, elementWidth, elementHeight, String.valueOf(element));
            elementX += elementWidth + 10;
        }

        g2d.setColor(Color.RED);
        g2d.drawString("Front", containerX + 10, containerY - 10);
        g2d.drawString("Rear", containerX + containerWidth - 40, containerY - 10);
    }
} 