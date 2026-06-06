package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🔍 BREADTH-FIRST SEARCH (BFS) - Level Order Traversal
 * 
 * BFS explores nodes level by level, visiting all nodes at current depth
 * before moving to nodes at the next depth level.
 * 
 * Uses: Shortest path in unweighted graphs, level order traversal, 
 *       finding connected components, web crawling
 */
public class BFS {
    
    // TreeNode class for binary tree BFS
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Graph representation using adjacency list
    static class Graph {
        private Map<Integer, List<Integer>> adjList;
        
        public Graph() {
            adjList = new HashMap<>();
        }
        
        public void addEdge(int u, int v) {
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // For undirected graph
        }
        
        public List<Integer> getNeighbors(int node) {
            return adjList.getOrDefault(node, new ArrayList<>());
        }
        
        public Set<Integer> getAllNodes() {
            return adjList.keySet();
        }
    }
    
    /**
     * BFS for Binary Tree - Level Order Traversal
     * Time Complexity: O(n), Space Complexity: O(w) where w is max width
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
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
     * BFS for Graph - Standard Implementation
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> bfsGraph(Graph graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(startNode);
        visited.add(startNode);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            
            // Visit all unvisited neighbors
            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    /**
     * BFS to find shortest path in unweighted graph
     */
    public static List<Integer> shortestPath(Graph graph, int start, int end) {
        if (start == end) return Arrays.asList(start);
        
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        parent.put(start, -1);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                    
                    if (neighbor == end) {
                        // Reconstruct path
                        List<Integer> path = new ArrayList<>();
                        int node = end;
                        while (node != -1) {
                            path.add(0, node);
                            node = parent.get(node);
                        }
                        return path;
                    }
                }
            }
        }
        
        return new ArrayList<>(); // No path found
    }
    
    /**
     * BFS to find all connected components
     */
    public static List<List<Integer>> findConnectedComponents(Graph graph) {
        List<List<Integer>> components = new ArrayList<>();
        Set<Integer> globalVisited = new HashSet<>();
        
        for (int node : graph.getAllNodes()) {
            if (!globalVisited.contains(node)) {
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                
                queue.offer(node);
                globalVisited.add(node);
                
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    component.add(current);
                    
                    for (int neighbor : graph.getNeighbors(current)) {
                        if (!globalVisited.contains(neighbor)) {
                            globalVisited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
                
                components.add(component);
            }
        }
        
        return components;
    }
    
    /**
     * BFS for minimum steps to reach target (like word ladder)
     */
    public static int minStepsToTarget(int start, int target) {
        if (start == target) return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                // Generate next possible states (example: +1, -1, *2)
                int[] nextStates = {current + 1, current - 1, current * 2};
                
                for (int next : nextStates) {
                    if (next == target) return steps;
                    
                    if (next > 0 && next <= 10000 && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        
        return -1; // Target not reachable
    }
    
    /**
     * Create sample binary tree
     *       3
     *      / \
     *     9   20
     *        /  \
     *       15   7
     */
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    /**
     * Create sample graph
     *   0---1---2
     *   |   |   |
     *   3---4---5
     */
    public static Graph createSampleGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        return graph;
    }
    
    public static void main(String[] args) {
        System.out.println("🔍 BREADTH-FIRST SEARCH (BFS) DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        // 1. BFS on Binary Tree (Level Order Traversal)
        System.out.println("1. BFS on Binary Tree - Level Order Traversal:");
        TreeNode root = createSampleTree();
        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Level order result: " + levels);
        System.out.println("Each inner list represents one level of the tree");
        
        // 2. BFS on Graph
        System.out.println("\n2. BFS on Graph:");
        Graph graph = createSampleGraph();
        List<Integer> bfsResult = bfsGraph(graph, 0);
        System.out.println("BFS traversal from node 0: " + bfsResult);
        
        // 3. Shortest Path using BFS
        System.out.println("\n3. Shortest Path using BFS:");
        List<Integer> path = shortestPath(graph, 0, 5);
        System.out.println("Shortest path from 0 to 5: " + path);
        System.out.println("Path length: " + (path.size() - 1) + " edges");
        
        // 4. Connected Components
        System.out.println("\n4. Connected Components:");
        // Add isolated nodes to demonstrate multiple components
        Graph graphWithComponents = createSampleGraph();
        graphWithComponents.addEdge(6, 7); // Separate component
        List<List<Integer>> components = findConnectedComponents(graphWithComponents);
        System.out.println("Connected components: " + components);
        
        // 5. Minimum Steps Problem
        System.out.println("\n5. Minimum Steps to Target:");
        int steps = minStepsToTarget(2, 8);
        System.out.println("Min steps from 2 to 8 (operations: +1, -1, *2): " + steps);
        
        // Interview Questions and Concepts
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What data structure does BFS use?");
        System.out.println("A1: Queue (FIFO - First In, First Out)");
        
        System.out.println("\nQ2: Time and Space complexity of BFS?");
        System.out.println("A2: Time: O(V + E), Space: O(V) for graphs");
        System.out.println("    Time: O(n), Space: O(w) for trees (w = max width)");
        
        System.out.println("\nQ3: When to use BFS vs DFS?");
        System.out.println("A3: BFS: Shortest path, level-order, minimum steps");
        System.out.println("    DFS: Topological sort, cycle detection, path finding");
        
        System.out.println("\nQ4: Applications of BFS?");
        System.out.println("A4: 1) Shortest path in unweighted graphs");
        System.out.println("    2) Level order tree traversal");
        System.out.println("    3) Web crawling");
        System.out.println("    4) Social network analysis (degrees of separation)");
        System.out.println("    5) GPS navigation systems");
        
        System.out.println("\nQ5: BFS vs Dijkstra's algorithm?");
        System.out.println("A5: BFS: Unweighted graphs, O(V+E)");
        System.out.println("    Dijkstra: Weighted graphs, O((V+E)logV)");
    }
}
