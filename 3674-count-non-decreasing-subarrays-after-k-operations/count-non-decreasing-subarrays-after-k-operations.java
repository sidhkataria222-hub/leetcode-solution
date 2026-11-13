
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        long ans = 0;
        long cost = 0;

        // Store {value, count}
        Deque<int[]> dq = new ArrayDeque<>();

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller values into current value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] last = dq.pollLast();

                int val = last[0];
                int cnt = last[1];

                count += cnt;

                cost += (long) (num - val) * cnt;
            }

            dq.offerLast(new int[]{num, count});

            // Reduce window if cost exceeds k
            while (cost > k) {

                int[] first = dq.pollFirst();

                int val = first[0];
                int cnt = first[1];

                cost -= (long) (val - nums[j]);

                j--;

                if (cnt > 1) {
                    dq.offerFirst(new int[]{val, cnt - 1});
                }
            }

            // Count valid subarrays
            ans += j - i + 1;
        }

        return ans;
    }
}
