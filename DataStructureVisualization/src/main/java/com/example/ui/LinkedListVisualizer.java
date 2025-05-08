package com.example.ui;

import com.example.datastructures.SingleyLinkedList;
import com.example.datastructures.DoublyLinkedList;
import java.awt.*;

public class LinkedListVisualizer extends DataStructureVisualizer {
    private Object list;
    private boolean isDoubly;
    private int startX;
    private int startY;

    public LinkedListVisualizer(SingleyLinkedList list) {
        this.list = list;
        this.isDoubly = false;
        this.startX = 50;
        this.startY = getHeight() / 2;
    }

    public LinkedListVisualizer(DoublyLinkedList list) {
        this.list = list;
        this.isDoubly = true;
        this.startX = 50;
        this.startY = getHeight() / 2;
    }

    @Override
    protected void drawDataStructure(Graphics2D g2d) {
        if (list == null) return;
        
        if (isDoubly) {
            drawDoublyLinkedList(g2d, (DoublyLinkedList) list);
        } else {
            drawSinglyLinkedList(g2d, (SingleyLinkedList) list);
        }
    }

    private void drawSinglyLinkedList(Graphics2D g2d, SingleyLinkedList list) {
        SingleyLinkedList.Node current = list.head;
        int x = startX;
        
        while (current != null) {
            drawNode(g2d, x, startY, String.valueOf(current.data));
            if (current.next != null) {
                drawArrow(g2d, x + NODE_RADIUS, startY, x + HORIZONTAL_GAP, startY);
            }
            x += HORIZONTAL_GAP + NODE_RADIUS * 2;
            current = current.next;
        }
    }

    private void drawDoublyLinkedList(Graphics2D g2d, DoublyLinkedList list) {
        DoublyLinkedList.Node current = list.head;
        int x = startX;
        
        while (current != null) {
            drawNode(g2d, x, startY, String.valueOf(current.data));
            if (current.next != null) {
                drawArrow(g2d, x + NODE_RADIUS, startY, x + HORIZONTAL_GAP, startY);
            }
            if (current.prev != null) {
                drawArrow(g2d, x - NODE_RADIUS, startY, x - HORIZONTAL_GAP, startY);
            }
            x += HORIZONTAL_GAP + NODE_RADIUS * 2;
            current = current.next;
        }
    }
} 