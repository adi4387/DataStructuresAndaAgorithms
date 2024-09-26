package learning.dsa;


import learning.dsa.modals.Cookie;

import java.util.*;

public class Main {
    public static void main(String[] args) {

//        Cookie green = new Cookie("Green");
//        Cookie red = new Cookie("Red");
//        System.out.println(green.getColor());
//        System.out.println(red.getColor());
//        var graph = new Graph(7);
//        graph.addEdge(0,1, true);
//        graph.addEdge(1,2, true);
//        graph.addEdge(2,3, true);
//        graph.addEdge(3,4, true);
//        graph.addEdge(4,5, true);
//        graph.addEdge(5,6, true);
//        graph.addEdge(6,0, true);
//        System.out.println(graph);
//        bfs(graph, 1, 6);
//        dfs(graph, 0);
    }

    private static int centre(Graph graph) {
        return graph
                .getNeighbours()
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static void bfs(Graph graph, int source, int destination) {
        boolean[] visited = new boolean[graph.getVertices()];

        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        for(int i = 0; i < graph.getVertices(); i++) {
            parent[i] = -1;
        }

        Queue<Integer> vertices = new LinkedList<>();


        vertices.add(source);
        parent[source] = source;
        distance[source] = 0;
        while(!vertices.isEmpty()) {
            int v = vertices.poll();
            visited[v] = true;
//            System.out.println("[" + v + "]");
            Set<Integer> neighbours = graph.getNeighbours().get(v);
            neighbours.forEach(n -> {
                        if(!visited[n]) {
                            vertices.add(n);
                            parent[n] = v;
                            distance[n] = distance[v] + 1;
                            visited[n] = true;
                        }
            });
        }
        for(int i = 0; i < graph.getVertices(); i++) {
            System.out.println(i + ":" + distance[i]);
        }

        int temp = destination;
        while(temp != source) {
            System.out.print(temp + "--");
            temp = parent[temp];
        }
        System.out.println(source);
    }

    private static void dfs(Graph graph, int source) {
        boolean[] visited = new boolean[graph.getVertices()];
        dfsHelper(graph, source, visited, -1);
    }

    private static void dfsHelper(Graph graph, int node, boolean[] visited, int parent) {
        visited[node] = true;
        System.out.println(node);
        for(int neighbour : graph.getNeighbours().get(node)) {
            if(!visited[neighbour] && parent != neighbour) {
                dfsHelper(graph, neighbour, visited, node);
            } else if(visited[neighbour] && parent != neighbour){
                System.out.println("Cyclic");
            }
        }
    }
}

class Graph {

    private final int vertices;

    private final Map<Integer, Set<Integer>> neighbours = new HashMap<>();

    public int getVertices() {
        return vertices;
    }

    public Map<Integer, Set<Integer>> getNeighbours() {
        return neighbours;
    }

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int v1, int v2, boolean undir) {
        addEdge(v1, v2);
        if(undir) {
            addEdge(v2, v1);
        }
    }

    private void addEdge(int key, int value) {
        neighbours.computeIfAbsent(key, k -> new HashSet<>()).add(value);
        neighbours.get(key).add(value);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", neighbours=" + neighbours +
                '}';
    }
}