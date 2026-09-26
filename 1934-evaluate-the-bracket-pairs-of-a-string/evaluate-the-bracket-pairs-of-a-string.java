class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // Store knowledge in HashMap
        HashMap<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                StringBuilder key = new StringBuilder();
                i++; // move after '('

                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

    
                ans.append(map.getOrDefault(key.toString(), "?"));

            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}