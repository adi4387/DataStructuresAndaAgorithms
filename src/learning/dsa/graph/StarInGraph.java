package learning.dsa.graph;

import java.util.*;

class StarInGraph {

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {4,2}};
        System.out.println(findCenter(edges));
    }
    public static int findCenter(int[][] edges) {
        int[] vertices = new int[edges.length + 1];
        for(int[] edge : edges) {
            vertices[edge[0]-1]++;
            vertices[edge[1]-1]++;
        }
        for(int i = 1; i <= vertices.length; i++) {
            if(vertices[i-1] == vertices.length - 1) {
                return i;
            }
        }
        return -1;
    }
}
