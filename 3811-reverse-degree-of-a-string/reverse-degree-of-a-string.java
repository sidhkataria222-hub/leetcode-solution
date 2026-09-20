//INT REVERSEValue=26-(s.charAt(I)-'a');
class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            // Reverse alphabet value
            int reverseValue = 26 - (s.charAt(i) - 'a');

            // Position is i + 1
            ans += reverseValue * (i + 1);
        }

        return ans;
    }
}