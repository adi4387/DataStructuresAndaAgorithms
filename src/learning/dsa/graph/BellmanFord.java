package learning.dsa.graph;

import java.util.*;

public class BellmanFord {

    public static void main(String[] args) {
        int[][] graph = {{1,2,3},{2,3,4},{1,3,-10}};
        List<List<Integer>> edges = new ArrayList<>();

        for (int[] ints : graph) {
            edges.add(List.of(ints[0], ints[1], ints[2]));
        }
        System.out.println(bellmanFord(3, 1, edges));
    }

    private static Map<Integer, Integer> bellmanFord(int nodes, int src, List<List<Integer>> edges) {
        Map<Integer, Integer> distances = new HashMap<>();
        for(int i = 0; i < nodes + 1; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(src, 0);

        for(int i = 0; i < nodes - 1; i++) {
            for(List<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);
                if(distances.get(u) != Integer.MAX_VALUE && distances.get(u) + wt < distances.get(v)) {
                    distances.put(v, distances.get(u) + wt);
                }
            }
        }

        for(List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);
            if(distances.get(u) != Integer.MAX_VALUE && distances.get(u) + wt < distances.get(v)) {
                System.out.println("Negetive weight cycle found");
                return Map.of();
            }
        }


        return distances;
    }
}
