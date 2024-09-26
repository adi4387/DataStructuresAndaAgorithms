package learning.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnionCycleFinder {

    public static void main(String[] args) {
        var graph = new DisjointSetUnionCycleFinder.Graph(7);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,4);
        graph.addEdge(2,3);
        graph.addEdge(2,6);
        graph.addEdge(2,5);
        graph.addEdge(6,5);
        System.out.println(graph.cycle());
    }

    static class Graph {

        Graph(int vertices) {
            this.vertices = vertices;
        }
        int vertices;
        List<List<Integer>> pairs = new ArrayList<>();

        void addEdge (int u, int v) {
            pairs.add(List.of(u,v));
        }

        public int find(int node, int[] parent) {
            if(parent[node] == -1) {
                return node;
            }
            return parent[node] = find(parent[node], parent);
        }

        public void union(int u, int v, int[] parent, int[] ranks) {
            int s1 = find(u, parent);
            int s2 = find(v, parent);

            if(s1 != s2) {
                if(ranks[s1] < ranks[s2]) {
                    parent[s1] = s2;
                    ranks[s2] += ranks[s1];
                } else {
                    parent[s2] = s1;
                    ranks[s1] += ranks[s2];
                }
            }
        }

        public boolean cycle() {
            int[] parent = new int[vertices];
            int[] ranks = new int[vertices];
            for(int i = 0; i < vertices; i++) {
                parent[i] = -1;
            }
            for (List<Integer> pair : pairs) {
                int s1 = find(pair.get(0), parent);
                int s2 = find(pair.get(1), parent);

                if(s1 != s2) {
                    union(s1, s2, parent, ranks);
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}
