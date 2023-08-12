package leetcode.dp;

public class P63UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // return uniquePathsWithObstacles1(obstacleGrid);
        return uniquePathsWithObstaclesLessMem(obstacleGrid);
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        }

        // first row
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = dp[0][i - 1];
            }
        }

        // first column
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            }
        }

        // rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstaclesLessMem(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];

        if (obstacleGrid[0][0] == 0) {
            dp[0] = 1;
        }

        // first row
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[i] = dp[i - 1];
            }
        }

        // rest
        for (int i = 1; i < m; i++) {

            if (obstacleGrid[i][0] == 1) { // avoid if checks in below loop, check first cell here.
                dp[0] = 0;
            }

            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1];
                } else {
                    dp[j] = 0;
                }
            }
        }

        return dp[n - 1];
    }

}
