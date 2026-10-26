package com.example.datastructures;

import java.util.ArrayList;
import java.util.Objects;

public class Map<K, V> {
    private static final double MAX_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] buckets;
    private final ArrayList<Entry<K, V>> insertionOrder = new ArrayList<>();
    private int size;

    public Map(int capacity) {
        buckets = newBuckets(Math.max(1, capacity));
    }

    protected static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value) {
        int index = indexFor(key, buckets.length);
        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                e.value = value;
                return;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value, buckets[index]);
        buckets[index] = entry;
        insertionOrder.add(entry);
        size++;
        if (size > buckets.length * MAX_LOAD_FACTOR) {
            resize(buckets.length * 2);
        }
    }

    public V get(K key) {
        for (Entry<K, V> e = buckets[indexFor(key, buckets.length)]; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        for (Entry<K, V> e = buckets[indexFor(key, buckets.length)]; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                return true;
            }
        }
        return false;
    }

    public V remove(K key) {
        int index = indexFor(key, buckets.length);
        Entry<K, V> previous = null;
        for (Entry<K, V> e = buckets[index]; e != null; previous = e, e = e.next) {
            if (Objects.equals(e.key, key)) {
                if (previous == null) {
                    buckets[index] = e.next;
                } else {
                    previous.next = e.next;
                }
                insertionOrder.remove(e);
                size--;
                return e.value;
            }
        }
        return null;
    }

    public int getLength() {
        return size;
    }

    public Entry<K, V> fetch(int index) {
        return insertionOrder.get(index);
    }

    private static int indexFor(Object key, int length) {
        return key == null ? 0 : (key.hashCode() & 0x7fffffff) % length;
    }

    private void resize(int capacity) {
        Entry<K, V>[] old = buckets;
        buckets = newBuckets(capacity);
        for (Entry<K, V> head : old) {
            for (Entry<K, V> e = head; e != null; ) {
                Entry<K, V> next = e.next;
                int index = indexFor(e.key, capacity);
                e.next = buckets[index];
                buckets[index] = e;
                e = next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Entry<K, V>[] newBuckets(int capacity) {
        return (Entry<K, V>[]) new Entry[capacity];
    }
}
