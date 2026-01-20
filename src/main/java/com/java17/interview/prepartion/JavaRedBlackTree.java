package com.java17.interview.prepartion;

/**
 * 🔴⚫ JAVA RED-BLACK TREE - Self-Balancing Binary Search Tree
 */
public class JavaRedBlackTree {
    
    enum Color { RED, BLACK }
    
    static class Node {
        int data;
        Color color;
        Node left, right, parent;
        
        Node(int data) {
            this.data = data;
            this.color = Color.RED; // New nodes are always red
            this.left = this.right = this.parent = null;
        }
    }
    
    private Node root;
    private Node NIL; // Sentinel node
    
    public JavaRedBlackTree() {
        NIL = new Node(0);
        NIL.color = Color.BLACK;
        root = NIL;
    }
    
    // Left rotate
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        
        if (y.left != NIL) {
            y.left.parent = x;
        }
        
        y.parent = x.parent;
        
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        
        y.left = x;
        x.parent = y;
    }
    
    // Right rotate
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        
        if (y.right != NIL) {
            y.right.parent = x;
        }
        
        y.parent = x.parent;
        
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        
        y.right = x;
        x.parent = y;
    }
    
    // Insert node
    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.left = NIL;
        newNode.right = NIL;
        
        Node parent = null;
        Node current = root;
        
        // Find position for new node
        while (current != NIL) {
            parent = current;
            if (newNode.data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        newNode.parent = parent;
        
        if (parent == null) {
            root = newNode;
        } else if (newNode.data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        
        // Fix Red-Black Tree properties
        insertFixup(newNode);
    }
    
    // Fix Red-Black Tree properties after insertion
    private void insertFixup(Node node) {
        while (node.parent != null && node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                
                if (uncle.color == Color.RED) {
                    // Case 1: Uncle is red
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        // Case 2: Uncle is black, node is right child
                        node = node.parent;
                        leftRotate(node);
                    }
                    // Case 3: Uncle is black, node is left child
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                
                if (uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }
    
    // Search for a value
    public boolean search(int data) {
        return searchHelper(root, data) != NIL;
    }
    
    private Node searchHelper(Node node, int data) {
        if (node == NIL || data == node.data) {
            return node;
        }
        
        if (data < node.data) {
            return searchHelper(node.left, data);
        }
        return searchHelper(node.right, data);
    }
    
    // In-order traversal
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.data + "(" + node.color + ") ");
            inorderHelper(node.right);
        }
    }
    
    // Get tree height
    public int getHeight() {
        return getHeightHelper(root);
    }
    
    private int getHeightHelper(Node node) {
        if (node == NIL) return 0;
        
        int leftHeight = getHeightHelper(node.left);
        int rightHeight = getHeightHelper(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Check if tree satisfies Red-Black properties
    public boolean isValidRedBlackTree() {
        if (root.color != Color.BLACK) return false;
        return checkBlackHeight(root) != -1;
    }
    
    private int checkBlackHeight(Node node) {
        if (node == NIL) return 1;
        
        int leftHeight = checkBlackHeight(node.left);
        int rightHeight = checkBlackHeight(node.right);
        
        if (leftHeight == -1 || rightHeight == -1 || leftHeight != rightHeight) {
            return -1;
        }
        
        // Check red node property
        if (node.color == Color.RED) {
            if ((node.left != NIL && node.left.color == Color.RED) ||
                (node.right != NIL && node.right.color == Color.RED)) {
                return -1;
            }
        }
        
        return leftHeight + (node.color == Color.BLACK ? 1 : 0);
    }
    
    public static void main(String[] args) {
        System.out.println("🔴⚫ RED-BLACK TREE DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        JavaRedBlackTree rbt = new JavaRedBlackTree();
        
        // Insert values
        System.out.println("1. Inserting values: 10, 20, 30, 15, 25, 5, 1");
        int[] values = {10, 20, 30, 15, 25, 5, 1};
        
        for (int value : values) {
            rbt.insert(value);
            System.out.println("Inserted " + value + ", Valid RB Tree: " + rbt.isValidRedBlackTree());
        }
        
        // Display tree
        System.out.println("\n2. In-order Traversal (with colors):");
        rbt.inorderTraversal();
        
        // Search operations
        System.out.println("\n3. Search Operations:");
        int[] searchValues = {15, 25, 100};
        for (int value : searchValues) {
            System.out.println("Search " + value + ": " + rbt.search(value));
        }
        
        // Tree properties
        System.out.println("\n4. Tree Properties:");
        System.out.println("Tree height: " + rbt.getHeight());
        System.out.println("Is valid Red-Black Tree: " + rbt.isValidRedBlackTree());
        
        // Red-Black Tree properties explanation
        System.out.println("\n5. Red-Black Tree Properties:");
        System.out.println("1. Every node is either red or black");
        System.out.println("2. Root is always black");
        System.out.println("3. All leaves (NIL) are black");
        System.out.println("4. Red nodes have black children");
        System.out.println("5. All paths from node to leaves have same black height");
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is a Red-Black Tree?");
        System.out.println("A1: Self-balancing BST with color properties ensuring O(log n) operations");
        
        System.out.println("\nQ2: Red-Black Tree vs AVL Tree?");
        System.out.println("A2: RB Tree: Looser balancing, faster insertion/deletion");
        System.out.println("    AVL Tree: Stricter balancing, faster search");
        
        System.out.println("\nQ3: Time complexities?");
        System.out.println("A3: Search/Insert/Delete: O(log n) guaranteed");
        
        System.out.println("\nQ4: Where are Red-Black Trees used?");
        System.out.println("A4: Java TreeMap/TreeSet, C++ map/set, Linux kernel");
        
        System.out.println("\nQ5: Why Red-Black over simple BST?");
        System.out.println("A5: Prevents degeneration to linked list, guarantees O(log n)");
    }
}