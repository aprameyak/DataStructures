package com.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JPanel visualizationPanel;
    private JComboBox<String> structureSelector;
    private JButton visualizeButton;
    private JButton clearButton;
    private JButton addButton;
    private JButton removeButton;
    private JTextField inputField;
    private JLabel statusLabel;

    public View() {
        setTitle("Data Structure Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setupLayout();
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout());
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        visualizationPanel = new JPanel(new BorderLayout());
        
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
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        inputField = new JTextField(10);
        statusLabel = new JLabel("Select a data structure to begin");
    }

    private void setupLayout() {
        // Control Panel Setup
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.add(new JLabel("Data Structure:"));
        controlPanel.add(structureSelector);
        controlPanel.add(new JLabel("Value:"));
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(visualizeButton);
        controlPanel.add(clearButton);

        // Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);

        // Visualization Panel
        visualizationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        visualizationPanel.setBackground(Color.WHITE);

        // Add all panels to main panel
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(visualizationPanel, BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    public void setVisualizationPanel(Component component) {
        visualizationPanel.removeAll();
        visualizationPanel.add(component, BorderLayout.CENTER);
        visualizationPanel.revalidate();
        visualizationPanel.repaint();
    }

    public void clearVisualization() {
        visualizationPanel.removeAll();
        visualizationPanel.revalidate();
        visualizationPanel.repaint();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public String getSelectedStructure() {
        return (String) structureSelector.getSelectedItem();
    }

    public String getInputValue() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }

    public void addVisualizeListener(ActionListener listener) {
        visualizeButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public void addAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addRemoveListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void addStructureSelectionListener(ActionListener listener) {
        structureSelector.addActionListener(listener);
    }

    public void setAddButtonEnabled(boolean enabled) {
        addButton.setEnabled(enabled);
    }

    public void setRemoveButtonEnabled(boolean enabled) {
        removeButton.setEnabled(enabled);
    }

    public void setInputFieldEnabled(boolean enabled) {
        inputField.setEnabled(enabled);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.setVisible(true);
        });
    }
}