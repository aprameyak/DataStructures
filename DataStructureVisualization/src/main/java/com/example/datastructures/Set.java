package com.example.datastructures;

public class Set<T> {
    private ArrayList<T> elements;

    public Set(int initialCapacity) {
        elements = new ArrayList<>(initialCapacity);
    }

    public boolean add(T item) {
        if (!contains(item)) {
            elements.append(item);
            return true;
        }
        return false;
    }

    public boolean contains(T item) {
        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.fetch(i).equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return elements.getLength();
    }

    public boolean isEmpty() {
        return elements.hasNoElements();
    }
}
