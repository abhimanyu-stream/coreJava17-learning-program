package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🌳 POST-ORDER TRAVERSAL (Left -> Right -> Root)
 * 
 * Post-order traversal visits nodes in the following order:
 * 1. Traverse the left subtree
 * 2. Traverse the right subtree
 * 3. Visit the root node
 * 
 * Used for: Tree deletion, calculating directory sizes, expression evaluation
 */
public class PostOrderRead {
    
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
     * Recursive Post-Order Traversal
     * Time Complexity: O(n), Space Complexity: O(h) where h is height of tree
     */
    public static void postOrderRecursive(TreeNode root, List<Integer> result) {
        if (root == null) return;
        
        postOrderRecursive(root.left, result);   // Left
        postOrderRecursive(root.right, result);  // Right
        result.add(root.val);                    // Root
    }
    
    /**
     * Iterative Post-Order Traversal using Two Stacks
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> postOrderIterativeTwoStacks(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        
        stack1.push(root);
        
        // First stack is used to traverse the tree
        // Second stack is used to store nodes in reverse post-order
        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);
            
            // Push left first, then right
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }
        
        // Pop all items from second stack to get post-order
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        
        return result;
    }
    
    /**
     * Iterative Post-Order Traversal using One Stack
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> postOrderIterativeOneStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        TreeNode current = root;
        
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode peekNode = stack.peek();
                
                // If right child exists and hasn't been processed yet
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        
        return result;
    }
    
    /**
     * Calculate tree height using post-order traversal
     */
    public static int calculateHeight(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Calculate tree size (number of nodes) using post-order traversal
     */
    public static int calculateSize(TreeNode root) {
        if (root == null) return 0;
        
        int leftSize = calculateSize(root.left);
        int rightSize = calculateSize(root.right);
        
        return leftSize + rightSize + 1;
    }
    
    /**
     * Delete entire tree using post-order traversal
     */
    public static TreeNode deleteTree(TreeNode root) {
        if (root == null) return null;
        
        // Delete left and right subtrees first
        root.left = deleteTree(root.left);
        root.right = deleteTree(root.right);
        
        // Delete current node
        System.out.println("Deleting node: " + root.val);
        return null;
    }
    
    /**
     * Check if tree is balanced using post-order traversal
     */
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private static int checkBalance(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = checkBalance(root.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(root.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Create a sample binary tree for testing
     *       4
     *      / \
     *     2   6
     *    / \ / \
     *   1  3 5  7
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
        System.out.println("🌳 POST-ORDER TRAVERSAL DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        TreeNode root = createSampleTree();
        
        // Method 1: Recursive
        System.out.println("1. Recursive Post-Order Traversal:");
        List<Integer> recursiveResult = new ArrayList<>();
        postOrderRecursive(root, recursiveResult);
        System.out.println("Result: " + recursiveResult);
        System.out.println("Pattern: Left subtree, right subtree, then root");
        
        // Method 2: Iterative with Two Stacks
        System.out.println("\n2. Iterative Post-Order (Two Stacks):");
        List<Integer> iterativeResult = postOrderIterativeTwoStacks(root);
        System.out.println("Result: " + iterativeResult);
        
        // Method 3: Iterative with One Stack
        System.out.println("\n3. Iterative Post-Order (One Stack):");
        List<Integer> oneStackResult = postOrderIterativeOneStack(root);
        System.out.println("Result: " + oneStackResult);
        
        // Verify all methods give same result
        System.out.println("\n✅ All methods match: " + 
            (recursiveResult.equals(iterativeResult) && 
             iterativeResult.equals(oneStackResult)));
        
        // Applications of Post-Order Traversal
        System.out.println("\n4. Applications of Post-Order Traversal:");
        
        // Calculate tree height
        int height = calculateHeight(root);
        System.out.println("Tree height: " + height);
        
        // Calculate tree size
        int size = calculateSize(root);
        System.out.println("Tree size (number of nodes): " + size);
        
        // Check if tree is balanced
        boolean balanced = isBalanced(root);
        System.out.println("Is tree balanced: " + balanced);
        
        // Tree deletion demonstration
        System.out.println("\n5. Tree Deletion using Post-Order:");
        TreeNode copyRoot = createSampleTree(); // Create a copy for deletion
        deleteTree(copyRoot);
        System.out.println("✅ Tree deleted in post-order fashion");
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's the order of post-order traversal?");
        System.out.println("A1: Left -> Right -> Root");
        
        System.out.println("\nQ2: Why is post-order useful for tree deletion?");
        System.out.println("A2: We delete children before parent, avoiding dangling pointers");
        
        System.out.println("\nQ3: Applications of post-order traversal?");
        System.out.println("A3: 1) Tree deletion");
        System.out.println("    2) Calculating directory sizes");
        System.out.println("    3) Expression tree evaluation");
        System.out.println("    4) Finding tree height/size");
        
        System.out.println("\nQ4: How to implement iteratively?");
        System.out.println("A4: Use two stacks or one stack with lastVisited pointer");
        
        System.out.println("\nQ5: Difference from pre-order?");
        System.out.println("A5: Post-order processes root last, pre-order processes root first");
    }
}
