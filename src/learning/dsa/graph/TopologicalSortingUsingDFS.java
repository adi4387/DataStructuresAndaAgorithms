package learning.dsa.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TopologicalSortingUsingDFS {

    public static void main(String[] args) {
        int[][] inputArray = {{1,  2}, {2, 3}, {1,  4}, {4, 5}, {3, 5}, {0, 2}};
        int n = 5;

        Graph graph = new Graph(n+1);
        for (int[] ints : inputArray) {
            graph.addEdge(ints[0], ints[1], false);
        }

        topologicalSorting(graph);
    }

    private static void topologicalSorting(Graph graph) {
        boolean[] visited = new boolean[graph.getVertices()];
        Queue<Integer> vertices = new LinkedList<>();

        for(int i = 0; i < graph.getVertices(); i++) {
            if(!visited[i])
                dfs(graph, i, visited, vertices);
        }

        for(int i = 0; i < graph.getVertices(); i++) {
            System.out.println(vertices.poll());
        }
    }

    private static void dfs(Graph graph, int node, boolean[] visited, Queue<Integer> vertices) {
        visited[node] = true;
        if(graph.getNeighbours().containsKey(node)) {
            Set<Integer> neighbours = graph.getNeighbours().get(node);
            for(int neighbour : neighbours) {
                if(!visited[neighbour]) {
                    dfs(graph, neighbour, visited, vertices);
                }
            }
        }
        vertices.add(node);
    }
}
