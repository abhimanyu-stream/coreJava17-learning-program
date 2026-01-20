package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🌳 PRE-ORDER TRAVERSAL (Root -> Left -> Right)
 * 
 * Pre-order traversal visits nodes in the following order:
 * 1. Visit the root node
 * 2. Traverse the left subtree
 * 3. Traverse the right subtree
 * 
 * Used for: Tree copying, expression tree evaluation, serialization
 */
public class PreOrderRead {
    
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
     * Recursive Pre-Order Traversal
     * Time Complexity: O(n), Space Complexity: O(h) where h is height of tree
     */
    public static void preOrderRecursive(TreeNode root, List<Integer> result) {
        if (root == null) return;
        
        result.add(root.val);                   // Root
        preOrderRecursive(root.left, result);   // Left
        preOrderRecursive(root.right, result);  // Right
    }
    
    /**
     * Iterative Pre-Order Traversal using Stack
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);  // Process root
            
            // Push right first, then left (stack is LIFO)
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        
        return result;
    }
    
    /**
     * Morris Pre-Order Traversal (No extra space)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static List<Integer> preOrderMorris(TreeNode root) {
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
                    // Process current before going left (pre-order)
                    result.add(current.val);
                    // Make current as right child of its inorder predecessor
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Revert the changes made
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Serialize binary tree using pre-order traversal
     */
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private static void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }
    
    /**
     * Deserialize binary tree from pre-order string
     */
    public static TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if ("null".equals(val)) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
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
        System.out.println("🌳 PRE-ORDER TRAVERSAL DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        TreeNode root = createSampleTree();
        
        // Method 1: Recursive
        System.out.println("1. Recursive Pre-Order Traversal:");
        List<Integer> recursiveResult = new ArrayList<>();
        preOrderRecursive(root, recursiveResult);
        System.out.println("Result: " + recursiveResult);
        System.out.println("Pattern: Root first, then left subtree, then right subtree");
        
        // Method 2: Iterative
        System.out.println("\n2. Iterative Pre-Order Traversal:");
        List<Integer> iterativeResult = preOrderIterative(root);
        System.out.println("Result: " + iterativeResult);
        
        // Method 3: Morris Traversal
        System.out.println("\n3. Morris Pre-Order Traversal (O(1) space):");
        List<Integer> morrisResult = preOrderMorris(root);
        System.out.println("Result: " + morrisResult);
        
        // Verify all methods give same result
        System.out.println("\n✅ All methods match: " + 
            (recursiveResult.equals(iterativeResult) && 
             iterativeResult.equals(morrisResult)));
        
        // Serialization/Deserialization
        System.out.println("\n4. Tree Serialization using Pre-Order:");
        String serialized = serialize(root);
        System.out.println("Serialized: " + serialized);
        
        TreeNode deserialized = deserialize(serialized);
        List<Integer> deserializedResult = new ArrayList<>();
        preOrderRecursive(deserialized, deserializedResult);
        System.out.println("Deserialized tree traversal: " + deserializedResult);
        System.out.println("✅ Serialization works: " + recursiveResult.equals(deserializedResult));
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What's the order of pre-order traversal?");
        System.out.println("A1: Root -> Left -> Right");
        
        System.out.println("\nQ2: Applications of pre-order traversal?");
        System.out.println("A2: 1) Tree serialization/copying");
        System.out.println("    2) Expression tree evaluation");
        System.out.println("    3) Directory structure traversal");
        System.out.println("    4) Creating a copy of the tree");
        
        System.out.println("\nQ3: How to implement without recursion?");
        System.out.println("A3: Use stack - push right child first, then left child");
        
        System.out.println("\nQ4: Difference from in-order traversal?");
        System.out.println("A4: Pre-order processes root first, in-order processes root between left and right");
    }
}
