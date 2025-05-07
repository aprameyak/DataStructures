package com.example.datastructures;

import java.util.Comparator;

public class SingleyLinkedList<T> {

    private class Element {
        T item;
        Element next;

        Element(T item) {
            this.item = item;
            this.next = null;
        }
    }

    private Element first, last;
    private int count;

    public int length() {
        return count;
    }

    public SingleyLinkedList<T> appendToEnd(T value) {
        Element e = new Element(value);
        if (first == null) {
            first = last = e;
        } else {
            last.next = e;
            last = e;
        }
        count++;
        return this;
    }

    public SingleyLinkedList<T> prependToFront(T value) {
        Element e = new Element(value);
        if (first == null) {
            first = last = e;
        } else {
            e.next = first;
            first = e;
        }
        count++;
        return this;
    }

    public T firstItem() {
        return first != null ? first.item : null;
    }

    public T lastItem() {
        return last != null ? last.item : null;
    }

    public T removeFirst() {
        if (first == null) return null;
        T val = first.item;
        first = first.next;
        count--;
        if (first == null) last = null;
        return val;
    }

    public T removeLast() {
        if (first == null) return null;
        T val = last.item;
        if (first == last) {
            first = last = null;
        } else {
            Element curr = first;
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
        while (first != null && rule.compare(first.item, target) == 0) {
            first = first.next;
            count--;
        }
        if (first == null) {
            last = null;
            return this;
        }
        Element curr = first;
        while (curr.next != null) {
            if (rule.compare(curr.next.item, target) == 0) {
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
        return buildReverseArray(first);
    }

    private ArrayList<T> buildReverseArray(Element node) {
        if (node == null) return new ArrayList<>(10);
        ArrayList<T> result = buildReverseArray(node.next);
        result.append(node.item);
        return result;
    }

    public SingleyLinkedList<T> reversedList() {
        SingleyLinkedList<T> reversed = new SingleyLinkedList<>();
        fillReversed(first, reversed);
        return reversed;
    }

    private void fillReversed(Element node, SingleyLinkedList<T> list) {
        if (node == null) return;
        fillReversed(node.next, list);
        list.appendToEnd(node.item);
    }
}
