class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int[] freq=new int[2001];
        for(int x:arr){
            freq[x+1000]++;

        }
        boolean[]used=new boolean[arr.length+1];
        for(int i=0; i<2001;i++){
            if(freq[i]>0){
                int f=freq[i];
                if(used[f]){
                    return false;

                }
                used[f]=true;

            }
        }
        return true;
    }
}