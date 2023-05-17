package leetcode.graph;

public class P1020Enclaves {

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dfs(0, 0, grid);
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(i, 0, grid);
            }
            if (grid[i][n - 1] == 1) {
                dfs(i, n - 1, grid);
            }
        }


        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                dfs(0, j, grid);
            }
            if (grid[m - 1][j] == 1) {
                dfs(m - 1, j, grid);
            }
        }

        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                total = total + grid[i][j];
            }
        }

        return total;
    }

    private void dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0; // mark visited
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }
}