
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long leftSum = 0, rightSum = 0;
    int leftSize = 0, rightSize = 0;

    void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }

    void insert(int val) {

        if (left.isEmpty() || val <= left.lastKey()) {
            add(left, val);
            leftSum += val;
            leftSize++;
        } else {
            add(right, val);
            rightSum += val;
            rightSize++;
        }

        balance();
    }

    void erase(int val) {

        if (left.containsKey(val)) {
            remove(left, val);
            leftSum -= val;
            leftSize--;
        } else {
            remove(right, val);
            rightSum -= val;
            rightSize--;
        }

        balance();
    }

    void balance() {

        while (leftSize > rightSize + 1) {
            int val = left.lastKey();

            remove(left, val);
            leftSum -= val;
            leftSize--;

            add(right, val);
            rightSum += val;
            rightSize++;
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();

            remove(right, val);
            rightSum -= val;
            rightSize--;

            add(left, val);
            leftSum += val;
            leftSize++;
        }
    }

    long getCost() {

        long median = left.lastKey();

        return median * leftSize - leftSum
             + rightSum - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;
        long[] cost = new long[n - x + 1];

        // Step 1: Calculate cost of every window
        for (int i = 0; i < n; i++) {

            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        // Step 2: DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= k; j++) {

                if (dp[i][j] == INF) continue;

                // Choice 1: Skip current position
                dp[i + 1][j] = Math.min(
                    dp[i + 1][j],
                    dp[i][j]
                );

                // Choice 2: Take a window
                if (j < k && i + x <= n) {

                    dp[i + x][j + 1] = Math.min(
                        dp[i + x][j + 1],
                        dp[i][j] + cost[i]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
