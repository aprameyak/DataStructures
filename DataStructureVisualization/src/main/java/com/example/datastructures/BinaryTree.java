package com.example.datastructures;

public class BinaryTree {
    class Node {
        Node right;
        Node left;
        int key;
        public Node(int item) {
            key = item;
            right = null;
            left = null;
        }
    }

    Node root;  

    public BinaryTree() {
        root = null;
    }

    public void insert(int key) {
        if(root == null) {
            root = new Node(key);
        } else {
            Node curr = root;
            while(curr.left != null && curr.right != null) {
                if(key < curr.key) {
                    if(curr.left == null) {
                        curr.left = new Node(key);
                        return;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    if(curr.right == null) {
                        curr.right = new Node(key);
                        return;
                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
    }

    public void remove(int key) {
        Node curr = root;
        Node prev = null;
        while(curr != null && curr.key != key) {
            prev = curr;
            if(key < curr.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if(curr == null) {
            return;
        }
        if(curr.left == null || curr.right == null) {
            Node newCurr;
            if(curr.left == null) {
                newCurr = curr.right;
            } else {
                newCurr = curr.left;
            }
            if(prev == null) {
                root = newCurr;
            } else {
                if(curr == prev.left) {
                    prev.left = newCurr;
                } else {
                    prev.right = newCurr;
                }
            }
        } else {
            Node p = null;
            Node temp;
            temp = curr.right;
            while(temp.left != null) {
                p = temp;
                temp = temp.left;
            }
            if(p != null) {
                p.left = temp.right;
            } else {
                curr.right = temp.right;
            }
            curr.key = temp.key;
        }
    
    }

}
