
//s[i]=s[i+d]

import java.util.*;

class Solution {

    public int minimumChanges(String s, int k) {

        int n = s.length();

        // Step 1: Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minCost = n;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d == 0) {

                        int changes = 0;

                        // Compare characters in each group
                        for (int offset = 0; offset < d; offset++) {

                            int left = i + offset;
                            int right = j - d + 1 + offset;

                            while (left < right) {

                                if (s.charAt(left) !=
                                    s.charAt(right)) {
                                    changes++;
                                }

                                left += d;
                                right -= d;
                            }
                        }

                        minCost = Math.min(minCost, changes);
                    }
                }

                cost[i][j] = minCost;
            }
        }

        // Step 2: DP
        int INF = 1000000;

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // i = number of characters considered
        for (int i = 2; i <= n; i++) {

            // j = number of partitions
            for (int j = 1; j <= k; j++) {

                // Previous partition point
                for (int p = 0; p <= i - 2; p++) {

                    if (dp[p][j - 1] != INF) {

                        dp[i][j] = Math.min(
                            dp[i][j],
                            dp[p][j - 1] + cost[p][i - 1]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }
}
