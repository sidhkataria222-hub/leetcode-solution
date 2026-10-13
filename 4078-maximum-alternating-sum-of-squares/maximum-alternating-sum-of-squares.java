
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        long ans = 0;
        int n = nums.length;

        for (int x : nums) {
            long v = Math.abs((long) x);
            ans += v * v;
        }

        Arrays.sort(nums);
        long total = 0;

        // The optimal score is the sum of the
        // largest half of squares minus the smallest half.
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            long v = Math.abs((long) nums[i]);
            squares[i] = v * v;
        }

        Arrays.sort(squares);

        for (int i = 0; i < n / 2; i++) {
            total -= squares[i];
        }
        for (int i = n / 2; i < n; i++) {
            total += squares[i];
        }

        return total;
    }
}
