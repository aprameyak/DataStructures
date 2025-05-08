package com.example.datastructures;

public class TestVisualization {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);
        tree.visualize();

        SingleyLinkedList<Integer> sll = new SingleyLinkedList<>();
        sll.appendToEnd(1);
        sll.appendToEnd(2);
        sll.appendToEnd(3);
        sll.appendToEnd(4);
        sll.visualize();

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.appendToEnd(1);
        dll.appendToEnd(2);
        dll.appendToEnd(3);
        dll.appendToEnd(4);
        dll.visualize();

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.visualize();

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.visualize();

        MinHeap minHeap = new MinHeap();
        minHeap.insert(4);
        minHeap.insert(2);
        minHeap.insert(6);
        minHeap.insert(1);
        minHeap.insert(3);
        minHeap.visualize();

        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(4);
        maxHeap.insert(2);
        maxHeap.insert(6);
        maxHeap.insert(1);
        maxHeap.insert(3);
        maxHeap.visualize();
    }
} 