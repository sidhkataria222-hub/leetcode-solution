//atmost(k)-AtMost(k-1)
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums,k)-atMost(nums,k-1);

    }
    private int atMost(int[] nums,int k){
        int left=0;
        int count=0; 
        int[]freq=new int[nums.length+1];
        int distinct=0;
        for(int right=0;right<nums.length;right++){
            if(freq[nums[right]]++==0){
                distinct++;

            }
            while(distinct>k){
                if(--freq[nums[left]]==0){
                    distinct--;

                }
                left++;

            }
            count+=right-left+1;
        }
        return count;
        
    }
}