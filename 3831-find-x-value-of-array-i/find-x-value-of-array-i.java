//keep freqencies of element
//an element is ranked by
//higher
//if equal then larger  first
// x sum (value*frequency)
class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

    
        long[] dp = new long[k];

        for (int num : nums) {

            int mod = num % k;

            long[] next = new long[k];

            
            next[mod] = 1;

            
            for (int r = 0; r < k; r++) {

                int newRemainder = (int)((long) r * mod % k);

                next[newRemainder] += dp[r];
            }

            
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}