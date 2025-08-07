class Solution {
    /**
    P:
        - given two strings:
            - word1
            - word2
        - return an int:
            - min # of operations required to convert word1 to word2
        - allowed operations:
            - char insert
            - char delete
            - char replace
    E:
        - examples make sense!
    D:

    A:
        - how would we approach this Brute Force?
        - an ideal thing to check is:
            - whether the lengths of the two strings are the same 
            (would tell if need to delete or replace)
            - whether the relative order of letters is from word2,
            in word1 are the same (help see if something needs to be changed or not)
        - AN EXTENSION OF LONGEST COMMON SUBSEQUENCE!
            - once we know the longest common subsequence, it comes
            down to adusting this so it equals the other string
    C:
     */
    public int minDistance(String word1, String word2) {

        // 2D DP problem where the dimensions are the lengths of the two words
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // the last row/col index corr. to the case of we consider the
        // 0 length substring of the other word; the amount of changes will
        // simply be the length of the substring
        for (int j = 0; j <= word2.length(); j++) {
            dp[word1.length()][j] = word2.length() - j;
        }
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][word2.length()] = word1.length() - i;
        }

        // we work backwards, we consider the substring of word1,
        // starting from the end word1[i:len(word1)] , and determine
        // the min. changes to get word2[j:len(word2)]

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j],
                                   Math.min(dp[i][j + 1], dp[i + 1][j + 1]));
                }
            }
        }
        
        return dp[0][0];
    }
}