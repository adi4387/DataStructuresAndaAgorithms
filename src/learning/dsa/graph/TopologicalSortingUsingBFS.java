package learning.dsa.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TopologicalSortingUsingBFS {

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
        int[] indegree = new int[graph.getVertices()];
        for(int i = 0; i < indegree.length; i++) {
            if(graph.getNeighbours().containsKey(i)) {
                Set<Integer> neighbours = graph.getNeighbours().get(i);
                for(int neighbour : neighbours) {
                    indegree[neighbour]++;
                }
            }
        }

        Queue<Integer> vertices = new LinkedList<>();

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0)
                vertices.add(i);
        }

        while(!vertices.isEmpty()) {
            int v = vertices.poll();
            System.out.println(v);
            for(int i = 0; i < indegree.length; i++) {
                if(graph.getNeighbours().containsKey(i)) {
                    Set<Integer> neighbours = graph.getNeighbours().get(i);
                    for(int neighbour : neighbours) {
                        indegree[neighbour]--;
                        if(indegree[neighbour] == 0) {
                            vertices.add(neighbour);
                        }
                    }
                }
            }
        }
    }
}
