package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🌳 BINARY SEARCH TREE (BST) - Complete Implementation
 * 
 * A Binary Search Tree is a binary tree where:
 * - Left subtree contains nodes with values less than root
 * - Right subtree contains nodes with values greater than root
 * - Both subtrees are also BSTs
 * 
 * Time Complexities:
 * - Search/Insert/Delete: O(log n) average, O(n) worst case
 * - In-order traversal gives sorted sequence
 */
public class JavaBinarySearchTree {
    
    // TreeNode class
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    private TreeNode root;
    
    public JavaBinarySearchTree() {
        this.root = null;
    }
    
    /**
     * Insert a value into BST
     * Time: O(log n) average, O(n) worst case
     */
    public void insert(int val) {
        root = insertRecursive(root, val);
    }
    
    private TreeNode insertRecursive(TreeNode node, int val) {
        // Base case: create new node
        if (node == null) {
            return new TreeNode(val);
        }
        
        // Recursive case: go left or right
        if (val < node.val) {
            node.left = insertRecursive(node.left, val);
        } else if (val > node.val) {
            node.right = insertRecursive(node.right, val);
        }
        // If val == node.val, don't insert duplicates
        
        return node;
    }
    
    /**
     * Search for a value in BST
     * Time: O(log n) average, O(n) worst case
     */
    public boolean search(int val) {
        return searchRecursive(root, val);
    }
    
    private boolean searchRecursive(TreeNode node, int val) {
        if (node == null) return false;
        
        if (val == node.val) return true;
        else if (val < node.val) return searchRecursive(node.left, val);
        else return searchRecursive(node.right, val);
    }
    
    /**
     * Iterative search
     */
    public boolean searchIterative(int val) {
        TreeNode current = root;
        
        while (current != null) {
            if (val == current.val) return true;
            else if (val < current.val) current = current.left;
            else current = current.right;
        }
        
        return false;
    }
    
    /**
     * Delete a value from BST
     * Time: O(log n) average, O(n) worst case
     */
    public void delete(int val) {
        root = deleteRecursive(root, val);
    }
    
    private TreeNode deleteRecursive(TreeNode node, int val) {
        if (node == null) return null;
        
        if (val < node.val) {
            node.left = deleteRecursive(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRecursive(node.right, val);
        } else {
            // Node to be deleted found
            
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2: One child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            // Case 3: Two children
            // Find inorder successor (smallest in right subtree)
            TreeNode successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteRecursive(node.right, successor.val);
        }
        
        return node;
    }
    
    /**
     * Find minimum value in BST
     */
    public int findMin() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return findMin(root).val;
    }
    
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * Find maximum value in BST
     */
    public int findMax() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return findMax(root).val;
    }
    
    private TreeNode findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    /**
     * In-order traversal (gives sorted sequence)
     */
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }
    
    private void inorderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderRecursive(node.left, result);
            result.add(node.val);
            inorderRecursive(node.right, result);
        }
    }
    
    /**
     * Validate if tree is a valid BST
     */
    public boolean isValidBST() {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode node, long minVal, long maxVal) {
        if (node == null) return true;
        
        if (node.val <= minVal || node.val >= maxVal) return false;
        
        return isValidBST(node.left, minVal, node.val) && 
               isValidBST(node.right, node.val, maxVal);
    }
    
    /**
     * Find kth smallest element
     */
    public int kthSmallest(int k) {
        List<Integer> inorder = inorderTraversal();
        if (k <= 0 || k > inorder.size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        return inorder.get(k - 1);
    }
    
    /**
     * Find Lowest Common Ancestor (LCA)
     */
    public TreeNode lowestCommonAncestor(int p, int q) {
        return lcaRecursive(root, p, q);
    }
    
    private TreeNode lcaRecursive(TreeNode node, int p, int q) {
        if (node == null) return null;
        
        // If both p and q are smaller than root, LCA is in left subtree
        if (p < node.val && q < node.val) {
            return lcaRecursive(node.left, p, q);
        }
        
        // If both p and q are greater than root, LCA is in right subtree
        if (p > node.val && q > node.val) {
            return lcaRecursive(node.right, p, q);
        }
        
        // If p and q are on different sides, current node is LCA
        return node;
    }
    
    /**
     * Calculate height of BST
     */
    public int height() {
        return calculateHeight(root);
    }
    
    private int calculateHeight(TreeNode node) {
        if (node == null) return -1;
        
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Count total nodes in BST
     */
    public int size() {
        return countNodes(root);
    }
    
    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    /**
     * Level order traversal
     */
    public List<List<Integer>> levelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    /**
     * Print tree structure
     */
    public void printTree() {
        printTreeHelper(root, "", true);
    }
    
    private void printTreeHelper(TreeNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTreeHelper(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printTreeHelper(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🌳 BINARY SEARCH TREE (BST) DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        JavaBinarySearchTree bst = new JavaBinarySearchTree();
        
        // Insert values
        System.out.println("1. Inserting values: 50, 30, 70, 20, 40, 60, 80");
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            bst.insert(val);
        }
        
        // Print tree structure
        System.out.println("\n2. Tree Structure:");
        bst.printTree();
        
        // Search operations
        System.out.println("\n3. Search Operations:");
        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 25: " + bst.search(25));
        System.out.println("Search 70 (iterative): " + bst.searchIterative(70));
        
        // Min/Max operations
        System.out.println("\n4. Min/Max Operations:");
        System.out.println("Minimum value: " + bst.findMin());
        System.out.println("Maximum value: " + bst.findMax());
        
        // Traversals
        System.out.println("\n5. Traversals:");
        System.out.println("In-order (sorted): " + bst.inorderTraversal());
        System.out.println("Level-order: " + bst.levelOrder());
        
        // Tree properties
        System.out.println("\n6. Tree Properties:");
        System.out.println("Tree height: " + bst.height());
        System.out.println("Tree size: " + bst.size());
        System.out.println("Is valid BST: " + bst.isValidBST());
        
        // Kth smallest
        System.out.println("\n7. Kth Smallest:");
        System.out.println("3rd smallest: " + bst.kthSmallest(3));
        System.out.println("5th smallest: " + bst.kthSmallest(5));
        
        // LCA
        System.out.println("\n8. Lowest Common Ancestor:");
        TreeNode lca = bst.lowestCommonAncestor(20, 40);
        System.out.println("LCA of 20 and 40: " + (lca != null ? lca.val : "null"));
        
        // Delete operations
        System.out.println("\n9. Delete Operations:");
        System.out.println("Before deletion: " + bst.inorderTraversal());
        
        bst.delete(20); // Delete leaf node
        System.out.println("After deleting 20: " + bst.inorderTraversal());
        
        bst.delete(30); // Delete node with two children
        System.out.println("After deleting 30: " + bst.inorderTraversal());
        
        bst.delete(50); // Delete root
        System.out.println("After deleting 50: " + bst.inorderTraversal());
        
        System.out.println("\nFinal tree structure:");
        bst.printTree();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What makes a tree a Binary Search Tree?");
        System.out.println("A1: Left subtree < root < right subtree for all nodes");
        
        System.out.println("\nQ2: Time complexity of BST operations?");
        System.out.println("A2: Average: O(log n), Worst: O(n) for skewed tree");
        
        System.out.println("\nQ3: How to delete a node with two children?");
        System.out.println("A3: Replace with inorder successor (or predecessor)");
        
        System.out.println("\nQ4: Applications of BST?");
        System.out.println("A4: 1) Searching and sorting");
        System.out.println("    2) Database indexing");
        System.out.println("    3) Expression parsing");
        System.out.println("    4) File system organization");
    }
}
