class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        // Process nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {

            int current = nums2[i];

            // Remove elements smaller than current
            while (!stack.isEmpty() && stack.peek() < current) {
                stack.pop();
            }

            // If stack is empty, no greater element
            if (stack.isEmpty()) {
                map.put(current, -1);
            } else {
                map.put(current, stack.peek());
            }

            stack.push(current);
        }

        // Build answer for nums1
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}