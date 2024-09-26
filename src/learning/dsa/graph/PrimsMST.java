package learning.dsa.graph;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class PrimsMST {

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 3, 3);
        System.out.println(graph.primMst());
    }


    static class Graph {

        int vertices;
        Map<Integer, List<List<Integer>>> neighboursWithWeight = new HashMap<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEdge(int u, int v, int w) {
            neighboursWithWeight.computeIfAbsent(u, k -> {
                List<List<Integer>> edges = new ArrayList<>();
                edges.add(List.of(v,w));
                return edges;
            });

            neighboursWithWeight.computeIfAbsent(v, k -> {
                List<List<Integer>> edges = new ArrayList<>();
                edges.add(List.of(u,w));
                return edges;
            });

            neighboursWithWeight.get(u).add(List.of(v,w));
            neighboursWithWeight.get(v).add(List.of(u,w));
        }

        int primMst() {
            PriorityQueue<List<Integer>> queue = new PriorityQueue<>(comparingInt(value -> value.get(0)));
            boolean[] visited = new boolean[vertices];
            int ans = 0;
            queue.add(List.of(0,0));

            while (!queue.isEmpty()) {
                var node = queue.poll();
                var weight = node.get(0);
                var to = node.get(1);

                if(visited[to]) {
                    continue;
                }
                ans += weight;
                visited[to] =true;
                for(List<Integer> edge : neighboursWithWeight.get(node.get(1))) {
                    if(!visited[edge.get(0)]) {
                        queue.add(List.of(edge.get(1), edge.get(0)));
                    }
                }
            }
            return ans;
        }
    }
}
