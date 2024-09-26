package learning.dsa.graph;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class Solution {
}

class CityGraph {
    Map<Integer, List<CityConnection>> graph;

    public CityGraph() {
        graph = new HashMap<>();
    }

    public void addConnection(int city1, int city2) {
        graph.putIfAbsent(city1, new ArrayList<>());
        graph.putIfAbsent(city2, new ArrayList<>());
        graph.get(city1).add(new CityConnection(city2, 1)); // Assuming each connection has weight 1
        graph.get(city2).add(new CityConnection(city1, 1)); // Assuming each connection has weight 1
    }

    public Map<Integer, Integer> dijkstra(int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<CityDistance> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));

        for (int city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        pq.offer(new CityDistance(start, 0));

        while (!pq.isEmpty()) {
            CityDistance current = pq.poll();
            int currentCity = current.city;
            int currentDistance = current.distance;

            if (currentDistance > distances.get(currentCity)) {
                continue;
            }

            for (CityConnection neighbor : graph.get(currentCity)) {
                int newDistance = currentDistance + neighbor.weight;
                if (newDistance < distances.get(neighbor.city)) {
                    distances.put(neighbor.city, newDistance);
                    pq.offer(new CityDistance(neighbor.city, newDistance));
                }
            }
        }

        return distances;
    }
}

class CityConnection {
    int city;
    int weight;

    public CityConnection(int city, int weight) {
        this.city = city;
        this.weight = weight;
    }
}

class CityDistance {
    int city;
    int distance;

    public CityDistance(int city, int distance) {
        this.city = city;
        this.distance = distance;
    }
}

class Result {

    /*
     * Complete the 'order' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. UNWEIGHTED_INTEGER_GRAPH city
     *  2. INTEGER company
     */

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i].
     *
     */

    public static List<Integer> order(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
        List<Integer> order = new ArrayList<>();
        CityGraph cityGraph = new CityGraph();
        for (int i = 0; i < cityFrom.size(); i++) {
            int u = cityFrom.get(i);
            int v = cityTo.get(i);
            cityGraph.addConnection(u, v);
        }

        Map<Integer, Integer> distances = cityGraph.dijkstra(company);
        System.out.println("Optimum path distances: " + distances);
        distances.entrySet()
                .stream().sorted(Comparator.comparingInt(Map.Entry::getValue))
                .iterator()
                .forEachRemaining(i -> order.add(i.getKey()));
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//        List<Integer> order = new ArrayList<>();
//        for (int i = 0; i < cityFrom.size(); i++) {
//            int u = cityFrom.get(i);
//            int v = cityTo.get(i);
//            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
//        }
//
//        Queue<Integer> queue = new LinkedList<>();
//        boolean[] visited = new boolean[cityNodes + 1];
//
//        queue.offer(company);
//        visited[company] = true;
//        while (!queue.isEmpty()) {
//            int node = queue.poll();
//
//            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
//            neighbors = neighbors.stream().sorted().toList();
//            for (int neighbor : neighbors) {
//                if (!visited[neighbor]) {
//                    order.add(neighbor);
//                    visited[neighbor] = true;
//                    queue.offer(neighbor);
//                }
//            }
//        }
//        return order;
        //---------
//        Map<Integer, List<List<Integer>>> graph = new HashMap<>();
//        List<Integer> order = new ArrayList<>();
//        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(comparingInt(value -> value.get(0)));
//
//        for (int i = 0; i < cityFrom.size(); i++) {
//            int u = cityFrom.get(i);
//            int v = cityTo.get(i);
//            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(List.of(v, v));
//            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(List.of(u, v));
//        }
//
//        boolean[] visited = new boolean[cityNodes + 1];
//        queue.offer(List.of(0, company));
//        while (!queue.isEmpty()) {
//            var node = queue.poll();
//            int to = node.get(1);
//
//            if (visited[to]) {
//                continue;
//            }
//            visited[to] = true;
//            List<List<Integer>> neighbours = graph.getOrDefault(to, List.of(new ArrayList<>()));
//            neighbours = neighbours.stream().sorted(Comparator.comparingInt(value -> value.get(0))).toList();
//            for (List<Integer> edge : neighbours) {
//                int u = edge.get(0);
//                if (!visited[u]) {
//                    Integer v = edge.get(1);
//                    if(u == to) {
//                        System.out.println(v);
//                        order.add(v);
//                    } else {
//                        System.out.println(u);
//                        order.add(u);
//                    }
//                    queue.add(List.of(v, u));
//                }
//            }
//        }
//        return order;
        return order;
    }

//    Map<Integer, List<List<Integer>>> graph = new HashMap<>();
//    List<Integer> order = new ArrayList<>();
//    PriorityQueue<List<Integer>> queue = new PriorityQueue<>(comparingInt(value -> value.get(0)));
//
//        for (int i = 0; i < cityFrom.size(); i++) {
//        int u = cityFrom.get(i);
//        int v = cityTo.get(i);
//        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(List.of(v,v));
//        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(List.of(u,v));
//    }

//    boolean[] visited = new boolean[cityNodes + 1];
//        queue.offer(List.of(0, company));
//        while (!queue.isEmpty()) {
//        var node = queue.poll();
//        var to = node.get(1);
//
//        if(visited[to]) {
//            continue;
//        }
//        visited[to]=true;
//        List<List<Integer>> neighbours = graph.getOrDefault(to, List.of(new ArrayList<>()));
//        for(List<Integer> edge : neighbours) {
//            if(!visited[edge.get(0)]) {
//                System.out.println(edge.get(0));
//                Integer v = edge.get(1);
//                order.add(v);
//                queue.add(List.of(v, edge.get(0)));
//            }
//        }
//    }
//        return order;

    public static void main(String[] args) {
        System.out.println(order(100,
                List.of(35, 5, 12, 9, 30, 54, 8, 63, 29, 35, 27, 23, 11, 51, 40, 76, 45, 17, 13, 46, 45, 5, 12, 81, 23, 15, 16, 55, 34, 11, 76, 49, 37, 9, 18, 48, 45, 24, 47, 25, 26, 61, 10, 6, 82, 87, 32, 24, 38, 73, 51, 50, 13, 38, 49, 29, 65, 28, 9, 30, 17, 32, 47, 16, 4, 30, 32, 59, 10, 34, 26, 11, 3, 26, 24, 37, 11, 42, 53, 51, 7, 9, 42, 53, 15, 19, 41, 68, 16, 22, 33, 63, 11, 14, 68, 57, 73, 80, 32, 5, 61, 62, 9, 81, 39, 28, 7, 29, 16, 6, 10, 50, 31, 78, 11, 26, 55, 20, 42, 18, 49, 13, 57, 3, 1, 52, 5, 62, 53, 25, 21, 29, 52, 2, 78, 35, 40, 12, 2, 17, 37, 18, 40, 51, 21, 31, 12, 16, 8, 80, 43, 5, 59, 47, 19, 15, 48, 3, 72, 2, 8, 50, 63, 74, 6, 36, 50, 22, 14, 38, 64, 42, 60, 15, 70, 96, 9, 73, 28, 26, 22, 11, 23, 36, 7, 11, 72, 3, 51, 49, 31, 78, 55, 15, 30, 91, 5, 47, 12, 18, 21, 13, 24, 11, 25, 15, 23, 80, 5, 20, 14, 30, 37, 34, 22, 5, 11, 33, 73, 36, 11, 53, 19, 41, 48, 43, 83, 1, 25, 19, 42, 22, 20, 12, 6, 21, 28, 2, 15, 7, 33, 51, 13, 16, 3, 15, 28, 24, 48, 16, 56, 41, 5, 1, 57, 15, 19, 26, 24, 46, 60, 23, 93, 40, 31, 26, 9, 6, 3, 18, 13, 28, 29, 33, 42, 40, 15, 32, 89, 96, 44, 28, 2, 35, 61, 6, 36, 28, 17, 63, 48, 11, 9, 7, 31, 89, 40, 67, 39, 65, 29, 56, 62, 18, 45, 52, 35, 28, 1, 4, 28, 13, 27, 47, 39, 11, 42, 25, 64, 34, 58, 69, 21, 19, 83, 1, 26, 32, 1, 22, 33, 81, 72, 46, 1, 8, 18, 53, 49, 37, 49, 56, 55, 25, 58, 28, 43, 26, 24, 5, 20, 2, 19, 26, 12, 50, 16, 40, 12, 11, 49, 7, 65, 77, 74, 53, 10, 42, 47, 64, 66, 13, 19, 85, 11, 51, 71, 21, 45, 19, 26, 27, 23, 73, 86, 52, 15, 61, 4, 52, 49, 31, 28, 74, 39, 27, 48, 14, 45, 71, 70, 28, 7, 61, 59, 16, 6, 75, 12, 61, 2, 49, 19, 20, 53, 48, 38, 1, 67, 56, 1, 2, 24, 3, 45, 28, 39, 97, 27, 36, 10, 31, 29, 38, 1, 49, 30, 45, 15, 39, 20, 44, 66, 19, 44, 31, 26, 97, 30, 4, 77, 15, 13, 72, 51, 29, 55, 3, 70, 56, 19, 47, 1, 16, 21, 24, 23, 4, 80, 63, 39, 7, 14, 62, 17, 7, 48, 16, 53, 30, 22, 20, 76, 79, 5, 47, 69, 22, 7, 14, 62, 57, 52, 26, 32, 13, 53, 1, 75, 47, 21, 8, 32, 73, 10, 17, 50, 63, 28, 53, 36, 55, 51, 67, 12, 57, 58, 2, 24, 15, 35, 20, 27, 23, 11, 10, 31, 79, 27, 40, 23, 20, 42, 38, 66, 86, 66, 17, 14, 71, 18, 10, 4, 63, 20, 3, 73, 70, 16, 40, 71, 57, 29, 77, 42, 6, 12, 1, 22, 13, 65, 2, 84, 26, 5, 28, 32, 5, 61, 24, 2, 33, 74, 67, 41, 12, 71, 21, 13, 46, 47, 18, 21, 36, 22, 75, 72, 21, 15, 14, 30, 2, 17, 5, 11, 12, 54, 49, 28, 18, 24, 6, 67, 64, 56, 73, 42, 2, 24, 48, 1, 47, 32, 11, 89, 51, 34, 50, 29, 4, 51, 13, 11, 35, 24, 7, 1, 25, 8, 10, 41, 37, 47, 8, 21, 56, 19, 46, 20, 6, 18, 69, 34),
                List.of(88, 31, 78, 84, 41, 58, 63, 84, 44, 41, 68, 26, 48, 65, 66, 88, 61, 90, 20, 75, 78, 24, 69, 86, 91, 83, 28, 56, 41, 90, 100, 89, 54, 62, 62, 50, 54, 59, 64, 58, 49, 100, 92, 14, 87, 94, 58, 80, 64, 92, 88, 70, 82, 90, 82, 70, 88, 68, 20, 86, 51, 39, 73, 88, 11, 43, 91, 85, 71, 65, 34, 65, 60, 57, 77, 87, 39, 43, 91, 71, 56, 29, 53, 69, 19, 84, 71, 73, 95, 76, 68, 95, 96, 83, 87, 78, 100, 89, 72, 29, 80, 70, 90, 83, 64, 43, 27, 42, 33, 52, 59, 82, 52, 84, 50, 70, 82, 85, 70, 47, 56, 18, 92, 4, 52, 57, 6, 78, 60, 89, 35, 35, 96, 71, 95, 54, 75, 44, 97, 63, 52, 60, 81, 97, 88, 97, 96, 99, 64, 87, 93, 15, 94, 51, 20, 90, 70, 57, 76, 94, 53, 68, 90, 78, 56, 56, 94, 67, 17, 50, 78, 82, 97, 97, 78, 97, 77, 97, 48, 32, 58, 67, 35, 89, 28, 89, 89, 79, 73, 73, 73, 83, 87, 29, 95, 95, 87, 80, 22, 65, 84, 23, 49, 98, 44, 33, 28, 91, 27, 37, 79, 45, 94, 48, 23, 37, 94, 45, 86, 46, 52, 76, 95, 78, 63, 64, 89, 10, 53, 43, 52, 52, 44, 65, 11, 33, 60, 43, 95, 18, 38, 87, 85, 48, 67, 99, 73, 25, 54, 79, 86, 89, 99, 3, 94, 55, 48, 61, 37, 56, 66, 78, 99, 46, 36, 86, 54, 62, 72, 38, 40, 64, 78, 80, 80, 96, 60, 95, 98, 99, 88, 50, 61, 89, 83, 38, 91, 40, 21, 77, 85, 91, 63, 52, 75, 95, 65, 75, 50, 79, 97, 74, 96, 89, 79, 62, 94, 79, 41, 34, 39, 64, 100, 85, 90, 54, 56, 96, 94, 56, 86, 74, 91, 71, 91, 8, 48, 67, 34, 44, 36, 97, 73, 67, 83, 50, 30, 83, 83, 48, 53, 88, 75, 60, 82, 31, 81, 59, 39, 11, 53, 51, 24, 37, 80, 64, 41, 54, 39, 69, 84, 57, 82, 82, 84, 70, 29, 63, 87, 86, 72, 26, 59, 99, 97, 54, 75, 49, 84, 33, 44, 33, 63, 79, 91, 95, 85, 75, 67, 69, 77, 53, 89, 95, 44, 69, 60, 98, 77, 97, 95, 33, 17, 68, 60, 39, 46, 92, 51, 85, 96, 88, 99, 83, 66, 51, 58, 20, 88, 83, 14, 13, 34, 16, 96, 56, 88, 99, 85, 81, 44, 59, 87, 91, 75, 81, 77, 46, 21, 56, 64, 69, 79, 52, 99, 60, 35, 100, 37, 56, 88, 78, 74, 88, 70, 74, 84, 82, 98, 97, 87, 81, 24, 80, 85, 48, 67, 25, 90, 99, 67, 26, 89, 75, 35, 48, 53, 56, 77, 65, 98, 86, 91, 91, 81, 94, 77, 79, 95, 46, 100, 86, 58, 53, 64, 98, 59, 45, 98, 53, 34, 41, 38, 88, 35, 36, 74, 92, 82, 86, 50, 64, 96, 90, 97, 95, 87, 11, 99, 52, 81, 63, 87, 37, 14, 42, 70, 95, 77, 51, 66, 100, 73, 51, 83, 100, 77, 99, 34, 87, 97, 66, 28, 100, 31, 33, 74, 91, 61, 58, 92, 100, 72, 78, 48, 74, 57, 100, 32, 60, 98, 29, 93, 40, 26, 38, 33, 36, 71, 71, 95, 44, 91, 69, 77, 14, 100, 92, 15, 76, 70, 83, 70, 98, 53, 100, 98, 32, 94, 72, 63, 66, 26, 55, 44, 47, 66, 92, 74, 59, 26, 94, 92, 74, 87, 78, 86, 9, 38, 99, 36, 60, 63, 79, 97, 95, 61, 67, 83, 49, 69, 39, 59, 71, 97, 64, 97, 28, 100, 40, 65, 63, 84, 74, 72, 68, 56, 64, 51, 60, 95, 97, 90), 20));
    }
}
