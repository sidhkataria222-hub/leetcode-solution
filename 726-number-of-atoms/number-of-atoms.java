//initilize the stack
//check opening barcket

//check closing
//check count
//alphabets sort using maping

//atom parsing brackets
//final output

import java.util.*;

class Solution {
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();

        stack.push(new HashMap<>());

        int n = formula.length();
        int i = 0;

        while (i < n) {
            char ch = formula.charAt(i);

            if (ch == '(') {
                stack.push(new HashMap<>());
                i++;
            }

            else if (ch == ')') {
                i++;

                int multiplier = 0;

                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10
                            + (formula.charAt(i) - '0');
                    i++;
                }

                if (multiplier == 0) {
                    multiplier = 1;
                }

                Map<String, Integer> top = stack.pop();
                Map<String, Integer> current = stack.peek();

                for (String atom : top.keySet()) {
                    current.put(
                        atom,
                        current.getOrDefault(atom, 0)
                        + top.get(atom) * multiplier
                    );
                }
            }

            else {
                int start = i;
                i++;

                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }

                String atom = formula.substring(start, i);

                int count = 0;

                while (i < n && Character.isDigit(formula.charAt(i))) {
                    count = count * 10 + (formula.charAt(i) - '0');
                    i++;
                }

                if (count == 0) {
                    count = 1;
                }

                Map<String, Integer> current = stack.peek();

                current.put(
                    atom,
                    current.getOrDefault(atom, 0) + count
                );
            }
        }

        Map<String, Integer> result = stack.pop();

        TreeMap<String, Integer> sorted = new TreeMap<>(result);

        StringBuilder answer = new StringBuilder();

        for (String atom : sorted.keySet()) {
            answer.append(atom);

            int count = sorted.get(atom);

            if (count > 1) {
                answer.append(count);
            }
        }

        return answer.toString();
    }
}