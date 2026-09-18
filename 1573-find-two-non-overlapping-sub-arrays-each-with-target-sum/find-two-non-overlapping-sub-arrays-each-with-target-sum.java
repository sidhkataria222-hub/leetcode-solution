import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = 1_000_000;

        int[] best = new int[n];
        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Found a subarray whose sum == target
            if (sum == target) {

                int len = right - left + 1;

                // Previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        len + best[left - 1]
                    );
                }

                // Store shortest valid subarray
                // ending at or before 'right'
                if (right == 0) {
                    best[right] = len;
                } else {
                    best[right] = Math.min(best[right - 1], len);
                }
            } 
            else if (right > 0) {
                // Carry forward previous best
                best[right] = best[right - 1];
            }
        }

        return answer == INF ? -1 : answer;
    }
}