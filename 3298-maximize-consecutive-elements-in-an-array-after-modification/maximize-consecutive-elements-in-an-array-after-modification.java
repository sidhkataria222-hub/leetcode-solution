import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            // Increase num to num + 1
            dp.put(num + 1, Math.max(
                dp.getOrDefault(num + 1, 0),
                dp.getOrDefault(num, 0) + 1
            ));

            // Keep num unchanged
            dp.put(num, Math.max(
                dp.getOrDefault(num, 0),
                dp.getOrDefault(num - 1, 0) + 1
            ));

            ans = Math.max(ans, Math.max(
                dp.getOrDefault(num, 0),
                dp.getOrDefault(num + 1, 0)
            ));
        }

        return ans;
    }
}