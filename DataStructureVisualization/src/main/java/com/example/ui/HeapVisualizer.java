package com.example.ui;

import com.example.datastructures.MinHeap;
import com.example.datastructures.MaxHeap;
import java.awt.*;

public class HeapVisualizer extends DataStructureVisualizer {
    private Object heap;
    private boolean isMinHeap;
    private int startX;
    private int startY;

    public HeapVisualizer(MinHeap heap) {
        this.heap = heap;
        this.isMinHeap = true;
        this.startX = getWidth() / 2;
        this.startY = 50;
    }

    public HeapVisualizer(MaxHeap heap) {
        this.heap = heap;
        this.isMinHeap = false;
        this.startX = getWidth() / 2;
        this.startY = 50;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (heap == null) return;

        if (isMinHeap) {
            drawHeap(g2d, (MinHeap) heap);
        } else {
            drawHeap(g2d, (MaxHeap) heap);
        }
    }

    private void drawHeap(Graphics2D g2d, Object heap) {
        Object[] elements = getHeapElements(heap);
        if (elements == null || elements.length == 0) return;

        int level = 0;
        int nodesInLevel = 1;
        int currentIndex = 0;
        int y = startY;

        while (currentIndex < elements.length) {
            int x = startX - (nodesInLevel - 1) * (NODE_RADIUS * 2 + HORIZONTAL_GAP) / 2;
            
            for (int i = 0; i < nodesInLevel && currentIndex < elements.length; i++) {
                // Draw node
                drawNode(g2d, x, y, String.valueOf(elements[currentIndex]));
                
                // Draw connections to children
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;
                
                if (leftChildIndex < elements.length) {
                    int childX = x - (nodesInLevel - 1) * (NODE_RADIUS * 2 + HORIZONTAL_GAP) / 2;
                    drawLine(g2d, x, y + NODE_RADIUS, childX, y + VERTICAL_GAP - NODE_RADIUS);
                }
                
                if (rightChildIndex < elements.length) {
                    int childX = x + (nodesInLevel - 1) * (NODE_RADIUS * 2 + HORIZONTAL_GAP) / 2;
                    drawLine(g2d, x, y + NODE_RADIUS, childX, y + VERTICAL_GAP - NODE_RADIUS);
                }
                
                x += NODE_RADIUS * 2 + HORIZONTAL_GAP;
                currentIndex++;
            }
            
            level++;
            nodesInLevel *= 2;
            y += VERTICAL_GAP;
        }
    }

    private Object[] getHeapElements(Object heap) {
        if (heap instanceof MinHeap) {
            return ((MinHeap) heap).toArray();
        } else if (heap instanceof MaxHeap) {
            return ((MaxHeap) heap).toArray();
        }
        return null;
    }
} 