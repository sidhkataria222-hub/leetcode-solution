class Solution {
    public int findLucky(int[] arr) {

        int[] freq = new int[501];

        // Count frequency
        for (int x : arr) {
            freq[x]++;
        }

        // Find largest lucky number
        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                return i;
            }
        }

        return -1;
    }
}