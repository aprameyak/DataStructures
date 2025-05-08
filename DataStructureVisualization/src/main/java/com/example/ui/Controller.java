package com.example.ui;

import com.example.datastructures.*;
import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JComboBox<String> structureSelector;
    private JPanel visualizationPanel;
    private JButton visualizeButton;
    private JButton clearButton;

    public Controller() {
        setTitle("Data Structure Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout());
        cardLayout = new CardLayout();
        visualizationPanel = new JPanel(cardLayout);
        
        String[] structures = {
            "Binary Tree",
            "Singly Linked List",
            "Doubly Linked List",
            "Stack",
            "Queue",
            "Min Heap",
            "Max Heap"
        };
        
        structureSelector = new JComboBox<>(structures);
        visualizeButton = new JButton("Visualize");
        clearButton = new JButton("Clear");
    }

    private void setupLayout() {
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Data Structure:"));
        controlPanel.add(structureSelector);
        controlPanel.add(visualizeButton);
        controlPanel.add(clearButton);

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(visualizationPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void setupEventHandlers() {
        visualizeButton.addActionListener(e -> visualizeSelectedStructure());
        clearButton.addActionListener(e -> clearVisualization());
    }

    private void visualizeSelectedStructure() {
        String selected = (String) structureSelector.getSelectedItem();
        DataStructureVisualizer visualizer = null;

        switch (selected) {
            case "Binary Tree":
                BinaryTree tree = new BinaryTree();
                tree.add(50);
                tree.add(30);
                tree.add(70);
                tree.add(20);
                tree.add(40);
                tree.add(60);
                tree.add(80);
                visualizer = new BinaryTreeVisualizer(tree);
                break;

            case "Singly Linked List":
                SingleyLinkedList<Integer> sll = new SingleyLinkedList<>();
                sll.appendToEnd(1);
                sll.appendToEnd(2);
                sll.appendToEnd(3);
                sll.appendToEnd(4);
                visualizer = new LinkedListVisualizer(sll);
                break;

            case "Doubly Linked List":
                DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
                dll.addToEnd(1);
                dll.addToEnd(2);
                dll.addToEnd(3);
                dll.addToEnd(4);
                visualizer = new LinkedListVisualizer(dll);
                break;

            case "Stack":
                Stack<Integer> stack = new Stack<>(10);
                stack.push(1);
                stack.push(2);
                stack.push(3);
                stack.push(4);
                visualizer = new StackVisualizer(stack);
                break;

            case "Queue":
                Queue<Integer> queue = new Queue<>(10);
                queue.enqueue(1);
                queue.enqueue(2);
                queue.enqueue(3);
                queue.enqueue(4);
                visualizer = new QueueVisualizer(queue);
                break;

            case "Min Heap":
                MinHeap minHeap = new MinHeap(10);
                minHeap.insert(4);
                minHeap.insert(2);
                minHeap.insert(6);
                minHeap.insert(1);
                minHeap.insert(3);
                visualizer = new HeapVisualizer(minHeap);
                break;

            case "Max Heap":
                MaxHeap maxHeap = new MaxHeap(10);
                maxHeap.insert(4);
                maxHeap.insert(2);
                maxHeap.insert(6);
                maxHeap.insert(1);
                maxHeap.insert(3);
                visualizer = new HeapVisualizer(maxHeap);
                break;
        }

        if (visualizer != null) {
            visualizationPanel.removeAll();
            visualizationPanel.add(visualizer, selected);
            cardLayout.show(visualizationPanel, selected);
            visualizationPanel.revalidate();
            visualizationPanel.repaint();
        }
    }

    private void clearVisualization() {
        visualizationPanel.removeAll();
        visualizationPanel.revalidate();
        visualizationPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            controller.setVisible(true);
        });
    }
} 