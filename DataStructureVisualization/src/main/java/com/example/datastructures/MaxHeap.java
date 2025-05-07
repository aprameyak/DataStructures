package com.example.datastructures;

public class MaxHeap {
    private ArrayList<Integer> heap;

    public MaxHeap(int capacity) {
        heap = new ArrayList<>(capacity);
    }

    public void insert(int value) {
        heap.append(value);
        int index = heap.getLength() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.fetch(index) <= heap.fetch(parent)) break;
            swap(index, parent);
            index = parent;
        }
    }

    public int peek() {
        if (heap.getLength() == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.fetch(0);
    }

    public int extractMax() {
        if (heap.getLength() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.fetch(0);
        ArrayList<Integer> newHeap = new ArrayList<>(heap.getCapacity());
        for (int i = 1; i < heap.getLength(); i++) {
            newHeap.append(heap.fetch(i));
        }
        heap = newHeap;
        heapify(0);
        return max;
    }

    private void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heap.getLength() && heap.fetch(left) > heap.fetch(largest)) {
            largest = left;
        }
        if (right < heap.getLength() && heap.fetch(right) > heap.fetch(largest)) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.fetch(i);
        ArrayList<Integer> rebuilt = new ArrayList<>(heap.getCapacity());
        for (int k = 0; k < heap.getLength(); k++) {
            if (k == i) {
                rebuilt.append(heap.fetch(j));
            } else if (k == j) {
                rebuilt.append(temp);
            } else {
                rebuilt.append(heap.fetch(k));
            }
        }
        heap = rebuilt;
    }
}
