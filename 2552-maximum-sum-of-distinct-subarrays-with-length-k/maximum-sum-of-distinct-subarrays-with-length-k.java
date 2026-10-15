
import java.util.*;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        long sum = 0;
        long maxSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            freq.put(nums[right],
                     freq.getOrDefault(nums[right], 0) + 1);

            if (right - left + 1 > k) {
                int x = nums[left];
                sum -= x;

                freq.put(x, freq.get(x) - 1);
                if (freq.get(x) == 0) {
                    freq.remove(x);
                }
                left++;
            }

            if (right - left + 1 == k && freq.size() == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
