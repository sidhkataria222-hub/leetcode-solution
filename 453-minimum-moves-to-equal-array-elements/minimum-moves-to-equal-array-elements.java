//in one move n-1 elements by 1 
//ek elements ko chodh ke  baki ko +1 kre 
//moves=sum-(n*minimum)
class Solution {
    public int minMoves(int[] nums) {
        int min=nums[0];
        int sum=0;
        for(int num: nums){
            min=Math.min(min,num);
            sum+=num;

        }
        return sum-nums.length*min;
    }
}