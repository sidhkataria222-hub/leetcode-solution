
import java.util.Arrays;

class Solution {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;

        int[] even = new int[(n + 1) / 2];
        int[] odd = new int[n / 2];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even[i / 2] = nums[i];
            } else {
                odd[i / 2] = nums[i];
            }
        }

        Arrays.sort(even);
        Arrays.sort(odd);

        int e = 0;
        int o = odd.length - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = even[e++];
            } else {
                nums[i] = odd[o--];
            }
        }

        return nums;
    }
}
