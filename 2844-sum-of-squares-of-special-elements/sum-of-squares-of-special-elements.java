//array  traversal
//index checking
//i+1=1 4%1=0
//i+1 4%2=0;
//i+1 4%3=/=0;
//i+1=4 4%4=0;

class Solution {
    public int sumOfSquares(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int i=0;i<n;i++){
            if(n %(i+1)==0){
                sum+=nums[i]*nums[i];

            }
        }
        return sum;
    }
}