// use of next permutation  
//string me convert kro
//check dec pair
//pivot se bda check
//pivpt ke right vala reverse kro

class Solution {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        int i = arr.length - 2;

        
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        
        if (i < 0) {
            return -1;
        }

        
        int j = arr.length - 1;

        while (arr[j] <= arr[i]) {
            j--;
        }

        
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        
        int left = i + 1;
        int right = arr.length - 1;

        while (left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        
        try {
            return Integer.parseInt(new String(arr));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}