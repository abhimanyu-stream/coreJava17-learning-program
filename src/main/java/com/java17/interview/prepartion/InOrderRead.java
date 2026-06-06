package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🌳 IN-ORDER TRAVERSAL (Left -> Root -> Right)
 * 
 * In-order traversal visits nodes in the following order:
 * 1. Traverse the left subtree
 * 2. Visit the root node
 * 3. Traverse the right subtree
 * 
 * For Binary Search Trees, in-order traversal gives sorted order.
 */
public class InOrderRead {

    // TreeNode class for binary tree
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Recursive In-Order Traversal
     * Time Complexity: O(n), Space Complexity: O(h) where h is height of tree
     */
    public static void inOrderRecursive(TreeNode root, List<Integer> result) {
        if (root == null)
            return;

        inOrderRecursive(root.left, result); // Left
        result.add(root.val); // Root
        inOrderRecursive(root.right, result); // Right
    }

    /**
     * Iterative In-Order Traversal using Stack
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current is null, so we backtrack from stack
            current = stack.pop();
            result.add(current.val); // Process root

            // Move to right subtree
            current = current.right;
        }

        return result;
    }

    /**
     * Morris In-Order Traversal (No extra space)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static List<Integer> inOrderMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // No left child, process current and move right
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Make current as right child of its inorder predecessor
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Revert the changes made
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        return result;
    }

    /**
     * Create a sample binary tree for testing
     * 4
     * / \
     * 2 6
     * / \ / \
     * 1 3 5 7
     */
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        System.out.println("🌳 IN-ORDER TRAVERSAL DEMONSTRATION");
        System.out.println("=".repeat(50));

        TreeNode root = createSampleTree();

        // Method 1: Recursive
        System.out.println("1. Recursive In-Order Traversal:");
        List<Integer> recursiveResult = new ArrayList<>();
        inOrderRecursive(root, recursiveResult);
        System.out.println("Result: " + recursiveResult);
        System.out.println("Expected for BST: [1, 2, 3, 4, 5, 6, 7] (sorted order)");

        // Method 2: Iterative
        System.out.println("\n2. Iterative In-Order Traversal:");
        List<Integer> iterativeResult = inOrderIterative(root);
        System.out.println("Result: " + iterativeResult);

        // Method 3: Morris Traversal
        System.out.println("\n3. Morris In-Order Traversal (O(1) space):");
        List<Integer> morrisResult = inOrderMorris(root);
        System.out.println("Result: " + morrisResult);

        // Verify all methods give same result
        System.out.println("\n✅ All methods match: " +
                (recursiveResult.equals(iterativeResult) &&
                        iterativeResult.equals(morrisResult)));

        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's the time complexity of in-order traversal?");
        System.out.println("A1: O(n) - we visit each node exactly once");

        System.out.println("\nQ2: What's special about in-order traversal of BST?");
        System.out.println("A2: It gives nodes in sorted (ascending) order");

        System.out.println("\nQ3: How to do in-order traversal without recursion?");
        System.out.println("A3: Use stack to simulate recursion or Morris traversal for O(1) space");

        System.out.println("\nQ4: Applications of in-order traversal?");
        System.out.println("A4: 1) Get sorted data from BST");
        System.out.println("    2) Validate if tree is BST");
        System.out.println("    3) Find kth smallest element in BST");
    }
}
