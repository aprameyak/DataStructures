package com.example.datastructures;

import java.util.ArrayList;

public class Map<K, V> {
    private ArrayList<Entry<K, V>> entries;

    public Map(int capacity) {
        entries = new ArrayList<>(capacity);
    }

    protected static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<K, V> e = entries.get(i);
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        entries.add(new Entry<>(key, value));
    }

    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<K, V> e = entries.get(i);
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public int getLength() {
        return entries.size();
    }

    public Entry<K, V> fetch(int index) {
        return entries.get(index);
    }

    public void append(Entry<K, V> entry) {
        entries.add(entry);
    }
}
