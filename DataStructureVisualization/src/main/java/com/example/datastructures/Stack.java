package com.example.datastructures;

import com.example.ui.StackVisualizer;

public class Stack<T> {
    private ArrayList<T> data;
    private int top;

    public Stack(int initialCapacity) {
        data = new ArrayList<>(initialCapacity);
        top = 0;
    }

    public void push(T value) {
        data.append(value);
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        top--;
        return data.fetch(top);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data.fetch(top - 1);
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public void visualize() {
        StackVisualizer visualizer = new StackVisualizer(this);
        visualizer.showVisualization("Stack Visualization");
    }
}
