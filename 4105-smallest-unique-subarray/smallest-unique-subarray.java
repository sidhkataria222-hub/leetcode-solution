
import java.util.*;

class Solution {
    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;
    static final long BASE1 = 131;
    static final long BASE2 = 13331;

    long[] pow1, pow2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }

        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean check(int[] nums, int len) {
        int n = nums.length;

        long h1 = 0, h2 = 0;

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        Map<Long, Integer> map = new HashMap<>();

        long key = h1 * MOD2 + h2;
        map.put(key, 1);

        for (int i = len; i < n; i++) {

            h1 = (h1 - nums[i - len] * pow1[len - 1] % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + nums[i]) % MOD1;

            h2 = (h2 - nums[i - len] * pow2[len - 1] % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;

            key = h1 * MOD2 + h2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
