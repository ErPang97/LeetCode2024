class Solution {
    public boolean isPalindrome(String s) {
        // P:
        // E: 
        // D: none
        // A: we use two-pointers, one from the beginning
        // and one from the end; they increment, and decrement
        // respectively. at each iteration of the loop, we check
        // if the values are the same; if they aren't 
        // return False
        // else if loop finishes, return true
        // C:

        StringBuilder sAlphaNum = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i)) || Character.isAlphabetic(s.charAt(i))){
                sAlphaNum.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        String onlyAlphaNum = sAlphaNum.toString();
        System.out.println(onlyAlphaNum);

        int j = onlyAlphaNum.length() - 1;
        for(int i = 0; i < onlyAlphaNum.length(); i++){
            if(onlyAlphaNum.charAt(i) != onlyAlphaNum.charAt(j)){
                return false;
            }
            j--;
        }
        return true;
    }
}