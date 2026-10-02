class Solution {
    public boolean canBeValid(String s, String locked) {

        int n = s.length();

        // Odd length can never be valid
        if (n % 2 == 1) {
            return false;
        }

        // Left to Right
        int balance = 0;

        for (int i = 0; i < n; i++) {

            if (locked.charAt(i) == '0' || s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        // Right to Left
        balance = 0;

        for (int i = n - 1; i >= 0; i--) {

            if (locked.charAt(i) == '0' || s.charAt(i) == ')') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return true;
    }
}