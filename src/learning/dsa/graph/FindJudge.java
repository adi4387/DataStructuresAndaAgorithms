package learning.dsa.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindJudge {
//    public static int findJudge(int n, int[][] trust) {
//        int[] trusts = new int[n];
//        int[] trustedBy = new int[n];
//        for(int[] t : trust) {
//            int trustier = t[0];
//            int trustee = t[1];
//            trusts[trustier - 1]++;
//            trustedBy[trustee - 1]++;
//        }
//        for(int i = 1; i <= n; i++) {
//            if(trusts[i-1] == 0 && trustedBy[i-1] == n - 1) {
//                return i;
//            }
//        }
//        return -1;
//    }

    //    public static int findJudge(int n, int[][] trust) {
//        if(n == 1) {
//            return 1;
//        }
//        Map<Integer, Set<Integer>> town = new HashMap<>();
//        boolean[] people = new boolean[n+1];
//        for(int[] t : trust) {
//            int trustier = t[0];
//            int trustee = t[1];
//            town.computeIfAbsent(trustee, k -> new HashSet<>()).add(trustier);
//            town.get(trustee).add(trustier);
//            people[trustier] = true;
//        }
//        return town.entrySet()
//                .stream()
//                .filter(e -> !people[e.getKey()] && e.getValue().size() == n-1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(-1);
//    }
    public static int findJudge(int n, int[][] trust) {
        if (trust.length == 0 && n == 1) {
            return n;
        }
        Graph graph = new Graph(n);
        boolean[] parents = new boolean[n + 1];
        for (int[] ints : trust) {
            parents[ints[0]] = true;
            graph.addEdge(ints[0], ints[1], false);
        }

        Set<Map.Entry<Integer, Set<Integer>>> judges = graph.neighbours.entrySet()
                .stream()
                .filter(integerSetEntry -> (integerSetEntry.getValue().size() == n - 1))
                .collect(Collectors.toSet());
        if (judges.size() == 1 && !parents[judges.iterator().next().getKey()]) {
            return judges.iterator().next().getKey();
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = {{1, 3}, {2, 3}, {1, 4}, {2, 4}};
        // int[][] trust = {{1,2},{3,2},{1,3},{4,1},{3,1},{2,1},{2,3},{4,3},{4,2},{3,4},{2,4}};

        System.out.println(findJudge(4, trust));
    }

    static class Graph {
        int vertices;
        Map<Integer, Set<Integer>> neighbours = new HashMap<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEdge(int u, int v, boolean undir) {
            neighbours.computeIfAbsent(v, k -> new HashSet<>()).add(u);
            if (undir) {
                neighbours.get(v).add(u);
                neighbours.get(u).add(v);
            } else {
                neighbours.get(v).add(u);
            }
        }
    }
}
