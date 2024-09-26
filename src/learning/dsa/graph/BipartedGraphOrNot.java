package learning.dsa.graph;

import java.util.Set;

public class BipartedGraphOrNot {

    public static void main(String[] args) {
        int[][] inputArray = {{1,  2}, {1, 3}, {2, 3}, {1,  4}, {4, 5}};
        int n = 5;

        Graph graph = new Graph(n+1);
        for (int[] ints : inputArray) {
            graph.addEdge(ints[0], ints[1], false);
        }

        System.out.println(dfs(graph));
    }

    private static boolean dfs(Graph graph) {
        int[] visited = new int[graph.getVertices()];
        return dfs_helper(graph, visited, 0, -1, 1);
    }

    private static boolean dfs_helper(Graph graph, int[] visited, int node, int parent, int color) {
        Set<Integer> neighbours = graph.getNeighbours().get(node);
        if(neighbours == null || neighbours.isEmpty()) {
            return false;
        }
        visited[node] = color;

        for(int neighbour : graph.getNeighbours().get(node)) {
            if(visited[neighbour] == 0) {
                if(!dfs_helper(graph, visited, neighbour, node,3 - color)) {
                    return false;
                }
            } else if(neighbour != parent && color == visited[neighbour]) {
                return false;
            }
        }
        return true;
    }
}
