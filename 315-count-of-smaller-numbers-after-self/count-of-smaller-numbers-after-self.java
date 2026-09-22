class Solution {

    int[] count;
    int[] indexes;

    public List<Integer> countSmaller(int[] nums) {

        int n = nums.length;

        count = new int[n];
        indexes = new int[n];

        for(int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, 0, n - 1);

        List<Integer> ans = new ArrayList<>();

        for(int c : count) {
            ans.add(c);
        }

        return ans;
    }

    private void mergeSort(int[] nums, int low, int high) {

        if(low >= high) return;

        int mid = low + (high - low) / 2;

        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);

        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];

        int left = low;
        int right = mid + 1;
        int k = 0;

        int rightCount = 0;

        while(left <= mid && right <= high) {

            if(nums[indexes[right]] < nums[indexes[left]]) {

                temp[k++] = indexes[right++];
                rightCount++;

            } else {

                count[indexes[left]] += rightCount;
                temp[k++] = indexes[left++];
            }
        }

        while(left <= mid) {

            count[indexes[left]] += rightCount;
            temp[k++] = indexes[left++];
        }

        while(right <= high) {
            temp[k++] = indexes[right++];
        }

        for(int i = 0; i < temp.length; i++) {
            indexes[low + i] = temp[i];
        }
    }
}