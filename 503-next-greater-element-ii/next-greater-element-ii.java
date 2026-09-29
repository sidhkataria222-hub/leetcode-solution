class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer>stack=new Stack<>();
        for(int i=2 *n-1 ;i>=0;i--){
            int current =nums[i%n];

            while( !stack.isEmpty() && stack.peek()<=current){
                stack.pop();
            }
            if(i<n){
                if(!stack.isEmpty()){
                    ans[i]=stack.peek();
                }
        }
        stack.push(current);
        }
        return ans;
    }
}