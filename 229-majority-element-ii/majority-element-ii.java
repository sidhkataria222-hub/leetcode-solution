// jo array se n/3 time appear ho
// max2 count 
// traverse the loop 2 times

import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {

        int candidate1 = 0, candidate2 = 1;
        int count1 = 0, count2 = 0;

        
        for (int num : nums) {

            if (num == candidate1) {
                count1++;
            }
            else if (num == candidate2) {
                count2++;
            }
            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            }
            else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }

        
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();

        if (count1 > nums.length / 3)
            result.add(candidate1);

        if (count2 > nums.length / 3)
            result.add(candidate2);

        return result;
    }
}