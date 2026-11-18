//A string is beautiful if:
//Its length is even.
// Every pair of adjacent characters must be equal.
class Solution {
    public int minChanges(String s) {

        int count = 0;
// two character at a time
        for (int i = 0; i < s.length(); i += 2) {

            if (s.charAt(i) != s.charAt(i + 1)) {
                count++;
            }
        }

        return count;
    }
}
