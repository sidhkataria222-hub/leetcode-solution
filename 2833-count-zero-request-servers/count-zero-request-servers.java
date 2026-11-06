
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;

        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[m];

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int active = 0;

        for (int i = 0; i < m; i++) {

            int time = q[i][0];

            while (right < logs.length &&
                   logs[right][1] <= time) {

                int id = logs[right][0];

                map.put(id, map.getOrDefault(id, 0) + 1);

                if (map.get(id) == 1) {
                    active++;
                }

                right++;
            }

            while (left < right &&
                   logs[left][1] < time - x) {

                int id = logs[left][0];

                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0) {
                    active--;
                    map.remove(id);
                }

                left++;
            }

            ans[q[i][1]] = n - active;
        }

        return ans;
    }
}
