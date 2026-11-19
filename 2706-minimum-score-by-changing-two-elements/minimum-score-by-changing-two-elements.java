//sorting
//change the two smallest to make min
//change the two largest
// change one largest and one sallest

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        if(n<=3){
            return 0;

        }
        int case1=nums[n-1]-nums[2];
        int case2=nums[n-3]-nums[0];
        int case3=nums[n-2]-nums[1];
        return Math.min(case1,Math.min(case2,case3));
    }
}