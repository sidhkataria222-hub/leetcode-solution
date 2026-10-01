class Solution {
    public boolean areOccurrencesEqual(String s) {
        int freq[]=new int[26];
        for(char c: s.toCharArray()){
            freq[c-'a']++;

        }
        int count=0;
        for(int x:freq){
            if(x>0){
                count=x;
                break;

            }
        }
        for(int x:freq){
            if(x>0 && x !=count){
                return false;

            }
        }
        return true;
    }

}