package learning.dsa.graph;

import java.util.List;
import java.util.Set;

public class CyclicDetectionInDirectedGraph {

    public static void main(String[] args) {
        int[][] inputArray = {{1,  2}, {1, 3}, {2, 3}, {1,  4}, {4, 5}};
        int n = 5;

        Graph graph = new Graph(n+1);
        for (int[] ints : inputArray) {
            graph.addEdge(ints[0], ints[1], false);
        }
        System.out.println(cyclic(graph));
    }

    private static boolean cyclic(Graph graph) {
        boolean[] visited = new boolean[graph.getVertices() + 1];
        boolean[] stack = new boolean[graph.getVertices() + 1];

        return dfs(graph, visited, stack, 1);
    }

    private static boolean dfs(Graph graph, boolean[] visited, boolean[] stack, int node) {
        Set<Integer> neighbours = graph.getNeighbours().get(node);
        if(neighbours == null || neighbours.isEmpty()) {
            return false;
        }
        visited[node] = true;
        stack[node] = true;
        for(int neighbour : neighbours) {
            if(stack[neighbour]) {
                return true;
            } else if(!visited[neighbour]) {
                if(dfs(graph, visited, stack, neighbour)) {
                    return true;
                }
            }
        }
        stack[node] = false;
        return false;
    }
}
