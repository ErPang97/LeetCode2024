class Solution {
    public boolean isPalindrome(int x) {
        
        int i = 0;
        String num = ""+x;
        while(i < num.length()){
            if(num.charAt(i) != num.charAt(num.length() - 1 - i)){
                return false;
            }
            i++;
        }
        return true;
    }
}