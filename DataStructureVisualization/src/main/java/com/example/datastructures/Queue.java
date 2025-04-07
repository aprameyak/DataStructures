package com.example.datastructures;

public class Queue<T> {
    T[] queue;
    int size;
    int capacity;

    public Queue(int capacity) {
        this.size = size;
        queue = (T[]) new Object[capacity];
        this.size = 0;
    }   
    public void push(T t) {
        if(size == capacity) {
            resize()
        }
        queue[size++] = t;
    }
    public T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T t = queue[0];
        size--;
        for (int i = 0; i < size; i++) {
            queue[i] = queue[i + 1]; 
        }
        return t;
    }
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[0]; 
    }
    public boolean isEmpty() {
        return size == 0;
    }   
    private void resize() {
        T[] newQueue = (T[]) new Object[2 * capacity]; 
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i]; 
        }
        queue = newQueue; 
        capacity = 2 * capacity; 
    }
}
