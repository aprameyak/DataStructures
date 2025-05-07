package com.example.datastructures;

public class Graph<E> {
    private Map<String, Map<String, Integer>> edges;
    private Map<String, E> nodeData;

    public Graph() {
        edges = new Map<>(10);
        nodeData = new Map<>(10);
    }

    public void registerNode(String label, E data) {
        if (nodeData.get(label) != null) {
            throw new IllegalArgumentException("Node already exists: " + label);
        }
        nodeData.put(label, data);
        edges.put(label, new Map<>(10));
    }

    public void linkNodes(String from, String to, int weight) {
        Map<String, Integer> fromEdges = edges.get(from);
        if (fromEdges == null) {
            fromEdges = new Map<>(10);
            edges.put(from, fromEdges);
        }
        fromEdges.put(to, weight);
    }

    public ArrayList<String> breadthSearch(String start) {
        Set<String> visited = new Set<>(10);
        Queue<String> queue = new Queue<>(10);
        ArrayList<String> visitOrder = new ArrayList<>(10);

        visited.add(start);
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            String current = queue.dequeue();
            visitOrder.append(current);

            Map<String, Integer> neighbors = edges.get(current);
            if (neighbors != null) {
                int len = neighbors.getLength();
                for (int i = 0; i < len; i++) {
                    Map.Entry<String, Integer> entry = neighbors.fetch(i);
                    String neighbor = entry.key;
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.enqueue(neighbor);
                    }
                }
            }
        }

        return visitOrder;
    }

    public ArrayList<String> depthSearch(String start) {
        Set<String> visited = new Set<>(10);
        Stack<String> stack = new Stack<>(10);
        ArrayList<String> visitOrder = new ArrayList<>(10);

        visited.add(start);
        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            visitOrder.append(current);

            Map<String, Integer> neighbors = edges.get(current);
            if (neighbors != null) {
                int len = neighbors.getLength();
                for (int i = 0; i < len; i++) {
                    Map.Entry<String, Integer> entry = neighbors.fetch(i);
                    String neighbor = entry.key;
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        stack.push(neighbor);
                    }
                }
            }
        }

        return visitOrder;
    }

    public Map<String, Integer> neighborsOf(String label) {
        Map<String, Integer> neighbors = edges.get(label);
        return (neighbors != null) ? neighbors : new Map<>(10);
    }

    public int edgeWeight(String from, String to) {
        Map<String, Integer> fromEdges = edges.get(from);
        if (fromEdges == null) {
            return -1;
        }
        Integer w = fromEdges.get(to);
        return (w != null) ? w : -1;
    }

    public E valueOf(String node) {
        return nodeData.get(node);
    }

    public ArrayList<String> allNodes() {
        ArrayList<String> all = new ArrayList<>(10);
        int len = nodeData.getLength();
        for (int i = 0; i < len; i++) {
            Map.Entry<String, E> entry = nodeData.fetch(i);
            all.append(entry.key);
        }
        return all;
    }
}
