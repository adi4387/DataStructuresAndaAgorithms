package learning.dsa.graph;

import java.util.List;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

public class KruskalMST {

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 3, 3);
        System.out.println(graph.kruskalMst());
    }

    static class Graph {

        int vertices;
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(comparingInt(value -> value.get(0)));

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEdge(int x, int y, int w) {
            queue.add(List.of(w,x,y));
        }

        int kruskalMst() {
            DSU dsu = new DSU(vertices);
            int ans = 0;
            while (!queue.isEmpty()) {
                var edge = queue.poll();
                Integer from = edge.get(1);
                Integer to = edge.get(2);
                if(dsu.find(from) != dsu.find(to)) {
                    dsu.unite(from, to);
                    ans += edge.get(0);
                }
            }
            return ans;
        }
    }


}

class DSU {

    int[] parents;
    int[] ranks;

    DSU(int vertices) {
        parents = new int[vertices];
        ranks = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parents[i] = -1;
            ranks[i] = 1;
        }
    }

    int find(int i) {
        if(parents[i] == -1) {
            return i;
        }

        return parents[i] = find(parents[i]);
    }

    void unite(int u, int v) {
        int s1 = find(u);
        int s2 = find(v);

        if(s1 != s2) {
            if(ranks[s2] < ranks[s1]) {
                parents[s2] = s1;
                ranks[s1] += ranks[s2];
            } else {
                parents[s1] = s2;
                ranks[s2] += ranks[s1];
            }
        }
    }


}

