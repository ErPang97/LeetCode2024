class Solution {
    public int countSubstrings(String s) {
        /**
        P: - given a string s, we want to count
        how many substrings of this string, that are palindromes
           - can we define do this in a 1DP way?
           - we can if we define a subproblem:
                - if we center the substring at some index k, 
                the number of palindromes is the number from 
                k-1 + the number of palindromes centered at k
        E: - for the frst example, clear that the only palindromic
        substrings are of length 1:
        'a', 'b', and 'c'
           - for the second example we have many for the input "aaa"
           of length 1:
           "a", "a", "a"
           of length 2:
           "aa", "aa"
           of length 3:
           "aaa"
        D:
            - none necessary
        A:
            int numPalindomres = 0
            for i in [0, s.length-1]:
                int left, right = i
                while (left >= 0 && right < s.length) // considers odd length palindromes
                    if (s[left] is same char as s[right]) 
                        left--
                        right++
                        numPalindomes++
                    else:
                        break
                left = i, right = i + 1
                while (left >= 0 && right < s.length) // consider even length ones
                    if (s[left] is same char as s[right]) 
                        left--
                        right++
                        numPalindomes++
                    else:
                        break
            return numPalindromes
        */
        // C:

        int numPalindromes = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length()) { // odd length palindromes
                if (s.charAt(left) == s.charAt(right)) {
                    numPalindromes++;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            left = i;
            right = i+1;
            while (left >= 0 && right < s.length()) { // even length palindromes
                if (s.charAt(left) == s.charAt(right)) {
                    numPalindromes++;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
        return numPalindromes;
    }
}