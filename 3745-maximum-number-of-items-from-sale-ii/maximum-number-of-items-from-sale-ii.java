// Step 1: Frequency of every factor
// Step 2: Calculate number of free-item opportunities
// Exclude the item itself
// Step 3: Sort items by price
// Step 4: Take beneficial enhanced purchases
// No benefit compared to buying cheapest items
//item
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

    
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

    
        int[] gain = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = i; j <= n; j += i) {
                gain[i] += freq[j];
            }

            
            gain[i]--;
        }

        
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long ans = 0;
        long remaining = budget;

        
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            
            if ((long) price >= 2L * minPrice) {
                break;
            }

            if (gain[factor] <= 0) {
                continue;
            }

            long count = Math.min(
                (long) gain[factor],
                remaining / price
            );

            ans += 2L * count;

            remaining -= count * price;
        }

    
        ans += remaining / minPrice;

        return (int) ans;
    }
}
