package com.example.datastructures;

import com.example.datastructures.ui.QueueVisualizer;

public class Queue<T> {
    private ArrayList<T> elements;
    private int frontIndex;

    public Queue(int initialCapacity) {
        this.elements = new ArrayList<>(initialCapacity);
        this.frontIndex = 0;
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

    public void visualize() {
        QueueVisualizer visualizer = new QueueVisualizer(this);
        visualizer.showVisualization("Queue Visualization");
    }
}
