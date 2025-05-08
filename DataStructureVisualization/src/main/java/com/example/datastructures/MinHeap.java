package com.example.datastructures;

import com.example.ui.HeapVisualizer;

public class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap(int capacity) {
        heap = new ArrayList<>(capacity);
    }

    public void insert(int value) {
        heap.append(value);
        int index = heap.getLength() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.fetch(index) >= heap.fetch(parent)) break;
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

    public int extractMin() {
        if (heap.getLength() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap.fetch(0);

        ArrayList<Integer> newHeap = new ArrayList<>(heap.getCapacity());
        for (int i = 1; i < heap.getLength(); i++) {
            if (i != 0) {
                newHeap.append(heap.fetch(i));
            }
        }
        heap = newHeap;
        heapify(0);

        return min;
    }

    private void heapify(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heap.getLength() && heap.fetch(left) < heap.fetch(smallest)) {
            smallest = left;
        }
        if (right < heap.getLength() && heap.fetch(right) < heap.fetch(smallest)) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
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

    public void visualize() {
        HeapVisualizer visualizer = new HeapVisualizer(this);
        visualizer.showVisualization("Min Heap Visualization");
    }
}
