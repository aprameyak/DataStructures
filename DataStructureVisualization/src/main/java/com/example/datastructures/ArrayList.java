package com.example.datastructures;

public class ArrayList<T> {
    T[] arrayList;
    int size;
    int capacity;

    public ArrayList(int capacity) {
        this.size = size;
        this.arrayList = (T[]) new Object[capacity];
        this.size = 0;
    }   
    public void add(T t) {
        if(size == capacity) {
            resize();
        }
        arrayList[size++] = t;
    }
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return arrayList[index]; 
    }
    public boolean isEmpty() {
        return size == 0;
    }   
    private void resize() {
        T[] newArrayList = (T[]) new Object[2 * capacity]; 
        for (int i = 0; i < size; i++) {
            newArrayList[i] = arrayList[i]; 
        }
        arrayList = newArrayList; 
        capacity = 2 * capacity; 
    }
}
