package com.example.datastructures;

import com.example.ui.QueueVisualizer;

public class Queue<T> {
    private ArrayList<T> elements;
    private int frontIndex;

    public Queue(int initialCapacity) {
        this.elements = new ArrayList<>(initialCapacity);
        this.frontIndex = 0;
    }

    public Queue() {
        this(16);
    }

    public void enqueue(T item) {
        elements.append(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.fetch(frontIndex++);
    }

    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.fetch(frontIndex);
    }

    public boolean isEmpty() {
        return frontIndex >= elements.getLength();
    }

    public int size() {
        return elements.getLength() - frontIndex;
    }

    public Object[] toArray() {
        int size = elements.getLength() - frontIndex;
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements.fetch(frontIndex + i);
        }
        return result;
    }

    public void visualize() {
        QueueVisualizer visualizer = new QueueVisualizer(this);
        visualizer.showVisualization("Queue Visualization");
    }
}
