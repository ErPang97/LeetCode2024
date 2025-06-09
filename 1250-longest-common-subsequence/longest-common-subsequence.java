class Solution {
    /**
    P:
    - given two strings:
        - text1, and text2
    - we want to return length of longest common subsequence
        - if no common subsequence, return 0
    - recall subsequence definition:
        - generated from the original string with some characters 
        deleted, without changing the relative order of appearance
        of the remaining characters
    E:
    - examples make sense
    D:
    - use an array for DP
    A:
    - for this problem we consider 2d dynamic programing
    - lets consider the subproblem, where we only consider a 
    substring of text1 and text2, say substring 0, i for text1
    and substring 0, j for text2
    - and lets say we make a dp array, where we store the length
    of the longest subsequence dp[i][j], for substring 0,i of text1
    and for substring 0,j of text2 respectively
        - we know at a base case when i = 0, and j = 0, it will be 0 length
        - this also applies ofc. to when either is 0 for the rest of the grid,
        as there are no chars to be represented
        - if i = 1 and j = 1, dp[i][j] will be 1, if it the substring of both 
        text1 and text2 is equal to the same character, else it is 0
        - however, now lets consider some general point after, some point i, j > 0
        - if i, j > 0:
            - dp[i][j] = dp[i - 1][j - 1] + 1, if text1[i] == text2[j] (they're the same char
            so adding the new char means the longest subsequence length increases by 1, when adding it)
            - if its not the same char, then dp[i][j] = Max(dp[i - 1][j], dp[i][j - 1]) when building up
    C: 
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        // loop through the top row to set the max subseq length to 1,
        // conditionally
        for (int j = 0; j < dp[0].length; j++)  {
            if (text1.charAt(0) == text2.charAt(j)) {
                dp[0][j] = 1;
            }
            else if(j > 0){
                dp[0][j] = Math.max(0, dp[0][j-1]);
            }
        }
        // similarly loop through left most column to set max subseq length to 1, 
        // dep. on conditions!
        for (int i = 0; i < dp.length; i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }
            else if(i > 0) {
                dp[i][0] = Math.max(dp[i-1][0], 0);
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[text1.length()-1][text2.length()-1];
    }
}