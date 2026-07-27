package com.example.datastructures;

import java.util.Comparator;
import com.example.ui.LinkedListVisualizer;

public class SingleyLinkedList<T> {

    public class Node {
        public T data;
        public Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    private Node last;
    private int count;

    public int length() {
        return count;
    }

    public SingleyLinkedList<T> appendToEnd(T value) {
        Node e = new Node(value);
        if (head == null) {
            head = last = e;
        } else {
            last.next = e;
            last = e;
        }
        count++;
        return this;
    }

    public SingleyLinkedList<T> prependToFront(T value) {
        Node e = new Node(value);
        if (head == null) {
            head = last = e;
        } else {
            e.next = head;
            head = e;
        }
        count++;
        return this;
    }

    public T firstItem() {
        return head != null ? head.data : null;
    }

    public T lastItem() {
        return last != null ? last.data : null;
    }

    public T removeFirst() {
        if (head == null) return null;
        T val = head.data;
        head = head.next;
        count--;
        if (head == null) last = null;
        return val;
    }

    public T removeLast() {
        if (head == null) return null;
        T val = last.data;
        if (head == last) {
            head = last = null;
        } else {
            Node curr = head;
            while (curr.next != last) {
                curr = curr.next;
            }
            curr.next = null;
            last = curr;
        }
        count--;
        return val;
    }

    public SingleyLinkedList<T> eliminate(T target, Comparator<T> rule) {
        while (head != null && rule.compare(head.data, target) == 0) {
            head = head.next;
            count--;
        }
        if (head == null) {
            last = null;
            return this;
        }
        Node curr = head;
        while (curr.next != null) {
            if (rule.compare(curr.next.data, target) == 0) {
                curr.next = curr.next.next;
                count--;
                if (curr.next == null) last = curr;
            } else {
                curr = curr.next;
            }
        }
        return this;
    }

    public ArrayList<T> reversedArray() {
        return buildReverseArray(head);
    }

    private ArrayList<T> buildReverseArray(Node node) {
        if (node == null) return new ArrayList<>(10);
        ArrayList<T> result = buildReverseArray(node.next);
        result.append(node.data);
        return result;
    }

    public SingleyLinkedList<T> reversedList() {
        SingleyLinkedList<T> reversed = new SingleyLinkedList<>();
        fillReversed(head, reversed);
        return reversed;
    }

    private void fillReversed(Node node, SingleyLinkedList<T> list) {
        if (node == null) return;
        fillReversed(node.next, list);
        list.appendToEnd(node.data);
    }

    public void visualize() {
        LinkedListVisualizer visualizer = new LinkedListVisualizer(this);
        visualizer.showVisualization("Singly Linked List Visualization");
    }
}
