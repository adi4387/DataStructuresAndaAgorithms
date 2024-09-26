package learning.dsa.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        if (undir) {
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
