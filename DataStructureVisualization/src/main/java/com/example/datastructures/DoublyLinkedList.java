package com.example.datastructures;

import com.example.datastructures.ui.LinkedListVisualizer;

public class DoublyLinkedList<T> {
    private class Node {
        T data;
        Node prev, next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head, tail;
    private int length;

    public void addToFront(T value) {
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        length++;
    }

    public void addToEnd(T value) {
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        length++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        length--;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        length--;
        return data;
    }

    public T getFirst() {
        return isEmpty() ? null : head.data;
    }

    public T getLast() {
        return isEmpty() ? null : tail.data;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void visualize() {
        LinkedListVisualizer visualizer = new LinkedListVisualizer(this);
        visualizer.showVisualization("Doubly Linked List Visualization");
    }
}
