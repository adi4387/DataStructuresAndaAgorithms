package learning.dsa.graph;

import java.util.List;
import java.util.Map;

public class TSP {

    public static void main(String[] args) {



        int[][] dist = {
                {0,20,42,25},
                {20,0,30,34},
                {42,30,0,10},
                {25,34,10,0}
        };
        System.out.println(tsp(dist, 1,0,4));
    }

    static int tsp(int[][] dist, int setOfCities, int city, int n) {

        if(setOfCities == (1<<n) - 1) {
            return dist[city][0];
        }

        int ans = Integer.MAX_VALUE;

        for(int choice = 0; choice < n; choice++) {
            if((setOfCities & (1 << choice)) == 0) {
                int sub = dist[city][choice] + tsp(dist, setOfCities | (1<<choice), choice, n);
                ans = Math.min(ans, sub);
                System.out.println(city);
            }
        }
        return ans;
    }
}
