package com.example.datastructures;

public class Stack<T> {
    T[] stack;
    int size;
    int capacity;

    public Stack(int capacity) {
        this.size = size;
        this.stack = (T[]) new Object[capacity];
        this.size = 0;
    }   
    public void push(T t) {
        if(size == capacity) {
            resize()
        }
        stack[size++] = t;
    }
    public T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T t = stack[--size];
        stack[size] = null;
        return t;
    }
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[size - 1]; 
    }
    public boolean isEmpty() {
        return size == 0;
    }   
    private void resize() {
        T[] newStack = (T[]) new Object[2 * capacity]; 
        for (int i = 0; i < size; i++) {
            newStack[i] = stack[i]; 
        }
        stack = newStack; 
        capacity = 2 * capacity; 
    }
}
