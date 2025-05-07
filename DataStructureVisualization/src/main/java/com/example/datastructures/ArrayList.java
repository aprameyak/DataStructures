package com.example.datastructures;

public class ArrayList<T> {
    private T[] items;
    private int count;
    private int maxSize;
    
    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        maxSize = initialCapacity;
        items = (T[]) java.util.Arrays.copyOf(new Object[initialCapacity], initialCapacity, Object[].class);
        count = 0;
    }

    public void append(T element) {
        if (count == maxSize) {
            expandCapacity();
        }
        items[count++] = element;
    }

    public T fetch(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return items[index];
    }

    public boolean hasNoElements() {
        return count == 0;
    }

    public int getLength() {
        return count;
    }

    public int getCapacity() {
        return maxSize;
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] larger = (T[]) java.lang.reflect.Array.newInstance(items.getClass().getComponentType(), maxSize * 2);
        for (int i = 0; i < count; i++) {
            larger[i] = items[i];
        }
        items = larger;
        maxSize *= 2;
    }
}
