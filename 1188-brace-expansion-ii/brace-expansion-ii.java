class Solution {

    int index;

    public List<String> braceExpansionII(String expression) {
        index = 0;

        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }


    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {

            Set<String> part = parseConcatenation(s);

            result.addAll(part);

            if (index < s.length() && s.charAt(index) == ',') {
                index++;
            } else {
                break;
            }
        }

        return result;
    }

    
    private Set<String> parseConcatenation(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> current;

            if (s.charAt(index) == '{') {

                index++; // skip {

                current = parse(s);

                index++; // skip }

            } else {

                current = new HashSet<>();

                current.add(String.valueOf(s.charAt(index)));

                index++;
            }

            result = multiply(result, current);
        }

        return result;
    }

    
    private Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}