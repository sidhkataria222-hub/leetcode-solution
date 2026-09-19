class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int[][]ans=new int[intervals.length][2];
        int idx=0;
        ans[0]=intervals[0];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<=ans[idx][1]){
                ans[idx][1]=Math.max(ans[idx][1],intervals[i][1]);

            }else{
                ans[++idx]=intervals[i];

            }
        }
        return Arrays.copyOf(ans,idx+1 )   ;
    }

}