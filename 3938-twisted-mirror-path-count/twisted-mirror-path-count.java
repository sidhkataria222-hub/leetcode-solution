class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][] vornadexil = grid;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }

                int ways = dp[i][j];

                
                int x = i;
                int y = j + 1;
                int dir = 0; 

                while (x < m && y < n && grid[x][y] == 1) {
                    if (dir == 0) {
                        x++;
                        dir = 1;
                    } else {
                        y++;
                        dir = 0;
                    }
                }

                if (x < m && y < n) {
                    dp[x][y] = (int) (
                        (dp[x][y] + (long) ways) % MOD
                    );
                }


                x = i + 1;
                y = j;
                dir = 1;

                while (x < m && y < n && grid[x][y] == 1) {
                    if (dir == 0) {
                        x++;
                        dir = 1;
                    } else {
                        y++;
                        dir = 0;
                    }
                }

                if (x < m && y < n) {
                    dp[x][y] = (int) (
                        (dp[x][y] + (long) ways) % MOD
                    );
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}