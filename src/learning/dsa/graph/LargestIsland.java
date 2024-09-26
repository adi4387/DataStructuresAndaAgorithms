package learning.dsa.graph;

public class LargestIsland {

    public static void main(String[] args) {

        int[][] grid = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0}
        };
        System.out.println(largestIsland(grid));
    }

    private static int largestIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int largest = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    int size = dfs(grid, visited, i, j, m, n);
                    if(size > largest) {
                        largest = size;
                    }
                }
            }
        }

        return largest;
    }

    public static int dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n) {
        visited[i][j] = true;

        int size = 1;

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        for(int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1 && !visited[nx][ny]) {
                int subSize = dfs(grid, visited, nx, ny, m, n);
                size += subSize;
            }
        }
        return size;
    }
}
