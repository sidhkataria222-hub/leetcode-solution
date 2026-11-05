class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDist=Integer.MAX_VALUE;
        int ans=-1;
        for(int i=0;i<points.length;i++){
            int a=points[i][0];
            int b=points[i][1];

            if(a==x || b==y){
                int dist =Math.abs(x-a)+Math.abs(y-b);
                if(dist<minDist){
                    minDist=dist;
                    ans=i;
                }
            }
        }
        return ans;
    }
}