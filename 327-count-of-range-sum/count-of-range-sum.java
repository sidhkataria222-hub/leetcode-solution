class Solution {

    long[] temp;

    public int countRangeSum(int[] nums, int lower, int upper) {

        int n = nums.length;

        long[] prefix = new long[n + 1];

        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        temp = new long[n + 1];

        return (int) mergeSort(
            prefix,
            0,
            n,
            lower,
            upper
        );
    }

    long mergeSort(
        long[] prefix,
        int left,
        int right,
        int lower,
        int upper
    ) {

        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        long count = 0;

        
        count += mergeSort(
            prefix,
            left,
            mid,
            lower,
            upper
        );

    
        count += mergeSort(
            prefix,
            mid + 1,
            right,
            lower,
            upper
        );

        
        int low = mid + 1;
        int high = mid + 1;

        for (int i = left; i <= mid; i++) {

            while (
                low <= right &&
                prefix[low] - prefix[i] < lower
            ) {
                low++;
            }

            while (
                high <= right &&
                prefix[high] - prefix[i] <= upper
            ) {
                high++;
            }

            count += high - low;
        }

        
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {

            if (prefix[i] <= prefix[j]) {
                temp[k++] = prefix[i++];
            } else {
                temp[k++] = prefix[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = prefix[i++];
        }

        while (j <= right) {
            temp[k++] = prefix[j++];
        }

        for (i = left; i <= right; i++) {
            prefix[i] = temp[i];
        }

        return count;
    }
}

