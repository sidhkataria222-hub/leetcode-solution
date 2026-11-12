 //Elements continuously hone chahiye.
//Har next element previous element se strictly greater hona chahiye.
 //Agar increasing sequence break ho jaati hai, toh counting reset hogi.

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int count=1;
        int maxLength=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                count++;
            }
            else{
                count=1;

            }
            maxLength=Math.max(maxLength,count);
        }
        return maxLength;
    }
}