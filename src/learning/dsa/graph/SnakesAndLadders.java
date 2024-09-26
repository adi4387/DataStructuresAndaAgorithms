package learning.dsa.graph;

import java.util.*;

public class SnakesAndLadders {

    public static void main(String[] args) {

        List<List<Integer>> ladders = new ArrayList<>();

        List<List<Integer>> snakes = new ArrayList<>();
        snakes.add(List.of(7,1));
        snakes.add(List.of(8,1));
        snakes.add(List.of(6,1));
        snakes.add(List.of(5,1));
        snakes.add(List.of(4,1));
        snakes.add(List.of(3,1));
        snakes.add(List.of(2,1));

        System.out.println(minimumChance(9, ladders, snakes));
    }

    private static int bfs(Graph graph, int source, int destination) {
        boolean[] visited = new boolean[graph.getVertices()];

        int[] distance = new int[graph.getVertices()];
        int[] parent = new int[graph.getVertices()];
        for(int i = 0; i < graph.getVertices(); i++) {
            parent[i] = -1;
        }

        Queue<Integer> vertices = new LinkedList<>();


        vertices.add(source);
        parent[source] = source;
        visited[source] = true;

        distance[source] = 0;
        while(!vertices.isEmpty()) {
            int v = vertices.poll();
//            System.out.println("[" + v + "]");
            Set<Integer> neighbours = Optional.ofNullable(graph.getNeighbours().get(v)).orElse(new HashSet<>());
            neighbours.forEach(n -> {
                if(!visited[n]) {
                    vertices.add(n);
                    parent[n] = v;
                    distance[n] = distance[v] + 1;
                    visited[n] = true;
                }
            });
        }
        for(int i = 1; i < graph.getVertices(); i++) {
            System.out.println(i + ":" + parent[i] + ":" + distance[i]);
        }

        int temp = destination;
        while(temp != source && temp > -1) {
            System.out.print(temp + "--");
            temp = parent[temp];
        }
        System.out.println(source);
        return distance[destination] <= 0 ? -1:distance[destination];
    }

    public static int minimumChance(int n, List<List<Integer>> ladders, List<List<Integer>> snakes) {
        Map<Integer, Integer> board = new HashMap<>();
        for(int i = 1; i < n + 1; i++) {
            board.put(i, -1);
        }

        for(List<Integer> ladder : ladders) {
            Integer source = ladder.get(0);
            Integer destination = ladder.get(1);
            board.put(source, destination);
        }

        for(List<Integer> snake : snakes) {
            Integer source = snake.get(0);
            Integer destination = snake.get(1);
            board.put(source, destination);
        }

        Graph graph = new Graph(n + 1);
        for(int u = 1; u < n; u++) {
            for(int dice = 1; dice <= 6; dice++) {
                int v = u + dice;
                if(v <= n) {
                    v = board.get(v) < 0 ? v : board.get(v);
                    graph.addEdge(u, v, false);
                }
            }
        }
        return bfs(graph, 1, n);
    }
}

