class Solution {
    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        q.add(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                String curr = q.poll();

                if (isValid(curr)) {
                    ans.add(curr);
                    found = true;
                }

                if (found) continue;

                for (int i = 0; i < curr.length(); i++) {

                    // Only remove parentheses
                    if (curr.charAt(i) != '(' &&
                        curr.charAt(i) != ')') {
                        continue;
                    }

                    String next =
                        curr.substring(0, i) +
                        curr.substring(i + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                    }
                }
            }

            if (found) break;
        }

        return ans;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {
                balance--;

                if (balance < 0)
                    return false;
            }
        }

        return balance == 0;
    }
}
