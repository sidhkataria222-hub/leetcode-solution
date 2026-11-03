//array increasing banana h
// every element must be greater 
//previous se  grrater hona h to greedy best method h
//nums[i]>nums[i-1];
//num[i-1]+1 banan hogaa
class Solution {
    public int minOperations(int[] nums) {
         int count=0;
         for(int i=1; i<nums .length ;i++){
            if(nums[i]<=nums[i-1]){
                int diff=nums[i-1]+1- nums[i];
                count+=diff;
                nums[i]=nums[i-1]+1;
            }
         }
         return count;
    }
}