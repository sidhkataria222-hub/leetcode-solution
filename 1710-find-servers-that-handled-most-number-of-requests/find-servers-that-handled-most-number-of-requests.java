import java.util.*;
//1. Free servers ko available mein add karo
// 2. Agar koi server available nahi
 // 3. Preferred server
 // 4. Next available server find karo
 // 5. Server ko busy mark karo
 // 6. Finish time aur server store 
  // 7. Maximum requests find karo
  // 8. Maximum wale servers return karo
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

        
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                int[] server = busy.poll();
                available.add(server[1]);
            }

        
            if (available.isEmpty()) {
                continue;
            }

            
            int preferred = i % k;

            Integer server = available.ceiling(preferred);

            if (server == null) {
                server = available.first();
            }

            
            available.remove(server);

            count[server]++;

            
            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

    
        int max = 0;

        for (int x : count) {
            max = Math.max(max, x);
        }

        
                List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}