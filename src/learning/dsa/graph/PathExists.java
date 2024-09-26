package learning.dsa.graph;

import java.util.*;
import java.util.stream.Collectors;

class PathExists {

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,4}, {2,3}};
        System.out.println(validPath(3, edges, 1, 4));
    }
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);
        System.out.println(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
//            if (node == destination) {
//                return true;
//            }
            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
            neighbors = neighbors.stream().sorted().toList();
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    System.out.println(neighbor);
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }
}
