package learning.dsa.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,1}};
        int numCourses = 3;
        System.out.println(canFinish(prerequisites, numCourses));
    }

    private static boolean canFinish(int[][] prerequisites, int numCourses) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        for(Map.Entry<Integer, List<Integer>> enty : graph.entrySet()) {
            if(dfs(graph, enty.getKey(), new boolean[numCourses+1], new boolean[numCourses+1])) {
                return false;
            }
        }
        return true;
    }

    static boolean dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited, boolean[] stack) {
        visited[node] = true;
        stack[node] = true;
        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>() {});
        for(int neighbour : neighbors) {
            if(stack[neighbour]) {
                return true;
            } else if(!visited[neighbour] && (dfs(graph, neighbour, visited, stack))) {
                    return true;

            }
        }
        stack[node] = false;
        return false;
    }

}
