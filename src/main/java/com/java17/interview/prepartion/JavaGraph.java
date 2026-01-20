package com.java17.interview.prepartion;

import java.util.*;

/**
 * 📊 JAVA GRAPH - Graph Data Structure Implementation
 */
public class JavaGraph {
    
    // Adjacency List representation
    static class Graph {
        private Map<Integer, List<Integer>> adjList;
        private boolean isDirected;
        
        public Graph(boolean isDirected) {
            this.adjList = new HashMap<>();
            this.isDirected = isDirected;
        }
        
        public void addVertex(int vertex) {
            adjList.putIfAbsent(vertex, new ArrayList<>());
        }
        
        public void addEdge(int source, int destination) {
            addVertex(source);
            addVertex(destination);
            
            adjList.get(source).add(destination);
            
            if (!isDirected) {
                adjList.get(destination).add(source);
            }
        }
        
        public List<Integer> getNeighbors(int vertex) {
            return adjList.getOrDefault(vertex, new ArrayList<>());
        }
        
        public Set<Integer> getAllVertices() {
            return adjList.keySet();
        }
        
        public void display() {
            for (int vertex : adjList.keySet()) {
                System.out.println(vertex + " -> " + adjList.get(vertex));
            }
        }
    }
    
    // Weighted Graph
    static class WeightedGraph {
        static class Edge {
            int destination;
            int weight;
            
            Edge(int destination, int weight) {
                this.destination = destination;
                this.weight = weight;
            }
            
            @Override
            public String toString() {
                return "(" + destination + "," + weight + ")";
            }
        }
        
        private Map<Integer, List<Edge>> adjList;
        
        public WeightedGraph() {
            this.adjList = new HashMap<>();
        }
        
        public void addVertex(int vertex) {
            adjList.putIfAbsent(vertex, new ArrayList<>());
        }
        
        public void addEdge(int source, int destination, int weight) {
            addVertex(source);
            addVertex(destination);
            adjList.get(source).add(new Edge(destination, weight));
        }
        
        public List<Edge> getNeighbors(int vertex) {
            return adjList.getOrDefault(vertex, new ArrayList<>());
        }
        
        public void display() {
            for (int vertex : adjList.keySet()) {
                System.out.println(vertex + " -> " + adjList.get(vertex));
            }
        }
    }
    
    // BFS Traversal
    public static List<Integer> bfs(Graph graph, int startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(startVertex);
        visited.add(startVertex);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            
            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    // DFS Traversal
    public static List<Integer> dfs(Graph graph, int startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(graph, startVertex, visited, result);
        return result;
    }
    
    private static void dfsHelper(Graph graph, int vertex, Set<Integer> visited, List<Integer> result) {
        visited.add(vertex);
        result.add(vertex);
        
        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(graph, neighbor, visited, result);
            }
        }
    }
    
    // Detect Cycle in Undirected Graph
    public static boolean hasCycleUndirected(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        
        for (int vertex : graph.getAllVertices()) {
            if (!visited.contains(vertex)) {
                if (hasCycleUndirectedUtil(graph, vertex, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean hasCycleUndirectedUtil(Graph graph, int vertex, int parent, Set<Integer> visited) {
        visited.add(vertex);
        
        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUndirectedUtil(graph, neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }
    
    // Shortest Path using BFS (unweighted)
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
                        return reconstructPath(parent, start, end);
                    }
                }
            }
        }
        
        return new ArrayList<>(); // No path found
    }
    
    private static List<Integer> reconstructPath(Map<Integer, Integer> parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;
        
        while (current != -1) {
            path.add(0, current);
            current = parent.get(current);
        }
        
        return path;
    }
    
    // Dijkstra's Algorithm for Shortest Path
    public static Map<Integer, Integer> dijkstra(WeightedGraph graph, int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Set<Integer> visited = new HashSet<>();
        
        // Initialize distances
        for (int vertex : graph.adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        
        pq.offer(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int distance = current[1];
            
            if (visited.contains(vertex)) continue;
            visited.add(vertex);
            
            for (WeightedGraph.Edge edge : graph.getNeighbors(vertex)) {
                int neighbor = edge.destination;
                int newDistance = distance + edge.weight;
                
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    pq.offer(new int[]{neighbor, newDistance});
                }
            }
        }
        
        return distances;
    }
    
    public static void main(String[] args) {
        System.out.println("📊 JAVA GRAPH DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        // 1. Basic Graph Operations
        System.out.println("1. Basic Graph Operations:");
        Graph graph = new Graph(false); // Undirected graph
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        
        System.out.println("Graph structure:");
        graph.display();
        
        // 2. BFS Traversal
        System.out.println("\n2. BFS Traversal from vertex 0:");
        List<Integer> bfsResult = bfs(graph, 0);
        System.out.println("BFS: " + bfsResult);
        
        // 3. DFS Traversal
        System.out.println("\n3. DFS Traversal from vertex 0:");
        List<Integer> dfsResult = dfs(graph, 0);
        System.out.println("DFS: " + dfsResult);
        
        // 4. Cycle Detection
        System.out.println("\n4. Cycle Detection:");
        System.out.println("Has cycle: " + hasCycleUndirected(graph));
        
        // 5. Shortest Path
        System.out.println("\n5. Shortest Path (BFS):");
        List<Integer> path = shortestPath(graph, 0, 4);
        System.out.println("Path from 0 to 4: " + path);
        
        // 6. Weighted Graph and Dijkstra
        System.out.println("\n6. Weighted Graph and Dijkstra's Algorithm:");
        WeightedGraph weightedGraph = new WeightedGraph();
        
        weightedGraph.addEdge(0, 1, 4);
        weightedGraph.addEdge(0, 2, 2);
        weightedGraph.addEdge(1, 2, 1);
        weightedGraph.addEdge(1, 3, 5);
        weightedGraph.addEdge(2, 3, 8);
        weightedGraph.addEdge(2, 4, 10);
        weightedGraph.addEdge(3, 4, 2);
        
        System.out.println("Weighted Graph:");
        weightedGraph.display();
        
        Map<Integer, Integer> distances = dijkstra(weightedGraph, 0);
        System.out.println("Shortest distances from vertex 0:");
        distances.forEach((vertex, distance) -> 
            System.out.println("To " + vertex + ": " + distance));
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: Graph representation methods?");
        System.out.println("A1: Adjacency Matrix, Adjacency List, Edge List");
        
        System.out.println("\nQ2: BFS vs DFS applications?");
        System.out.println("A2: BFS: Shortest path, level-order");
        System.out.println("    DFS: Topological sort, cycle detection");
        
        System.out.println("\nQ3: Dijkstra's algorithm complexity?");
        System.out.println("A3: O((V + E) log V) with priority queue");
        
        System.out.println("\nQ4: When to use graphs?");
        System.out.println("A4: Social networks, maps, dependencies, web crawling");
        
        System.out.println("\nQ5: Directed vs Undirected graphs?");
        System.out.println("A5: Directed: One-way edges, Undirected: Two-way edges");
    }
}
