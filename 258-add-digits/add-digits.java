// sum jab tak nikalan h jab tak single digit ka nh aajaye

class Solution {
    public int addDigits(int num) {
        while(num>=10){
            int sum=0;
            while(num>0){
                int digit=num%10;//last digit
                sum+=digit;
                num=num/10;

            }
            num=sum;

        }
        return num;
    }
}