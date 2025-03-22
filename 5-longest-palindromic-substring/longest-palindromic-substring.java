class Solution {

    public String longestPalindrome(String s) {
        /**
         * P: we want to return the longest substring of s
         * that is a Palindrome. Could be multiple however!
         * We can certainly do this in a 1-D programming way, 
         * if we consider the following subproblems:
         *    - if we end consider our string at some index k, 
         *    what is the longest palindrome up to that point
         */
        /**
         * E: in the example babad, the possible palindromes are:
         * length 1: "b", "a", "d"
         * length 2: none
         * length 3: "bab", "aba"
         * length 4: none
         * length 5: none
         */
        /**
         * D: Arrays are likely the only thing needed
         */
        /**
         * A:
         *  Brute force idea is looping through from i = 0 to n
         *  and checking every substring from k = 0 to i, and checking for
         *  longest palindrome. 
         *  To reduce computation complexity, we can use memoization to, perhaps
         *  just to store previous palindromes.
         * 
         *  Alg: 
         *  init HashSet<String> palindromes
         *  String longestPalindrome = ""
         *  for i in range(len(s)):
         *      candidates = [k for s.substring() centered at k if substring is palindrome]
         *      longestPalindrome = max length string in candidate
         *  
         * ADDENDuM: instead consider all substrings centered at the letter k instead
         * - will need to be careful and considfer even length substrings as well
         */
        // C:


        String longestPalindrome = "";
        int longestLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right <= s.length()-1 && s.charAt(left) == s.charAt(right)) {

                if (right - left + 1 >= longestLength) {
                    longestPalindrome = s.substring(left, right + 1);
                    longestLength = longestPalindrome.length();
                }
                left--;
                right++;
            }

            left = i;
            right = i + 1;
            while (left >= 0 && right <= s.length()-1 && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 >= longestLength) {
                    longestPalindrome = s.substring(left, right + 1);
                    longestLength = longestPalindrome.length();
                }
                left--;
                right++;
            }
        }
        return longestPalindrome;
        
    }

}