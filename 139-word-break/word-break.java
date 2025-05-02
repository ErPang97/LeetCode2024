class Solution {
    /**
    P: 
    - In: 
        - s - a string 
        - wordDict - a list of words
    - Out:
        - boolean whether or not s can be divided into a sequence of one or more words
        given the dictionary
    - words can be used multiple times
    - words must be present in dictionary
    E:
        - was confusing at first but all 3 examples make sense now
    D:
        - a list perhaps, or map for memoization purposes
    A:
        - so the brute force method would be to try every single subsequences as possible. 
        Start with the largest, being a word that contains all chars of s and if they
        do return true. Then, try all possible splits with two segments, then three segments, and so on until 
        we split at every letter. This is inefficient however, so we consider how DP may be used.

        - At a basic level, a string of length 1, we know that there will be 0 segments, so 
        we just check whether or not the string is in the dictionary and return true or false if not possible
        - At a length 2, the string itself may be in the wordDict, or the two individual chars may be in the wordDict
        - At length 3, we continue. We can check the string itself, or we can segment once at two different places,
        and see if the resulting words are, or we can segment twice after each char and see.
        ...
        - and so on.

        - Let's now consider, substrings of s:
            - given a substring s that ends in k, we want to build up and check all the sequences from k-1
            and simply see if adding the char at k makes it valid or not, if not continue.
        - to accomplish this, we may want to keep track of where the last valid splits occurred as we build up
            - for instance lets take ur example of "catsanddog"
            at index 6 we can get:
                - "cats" "and"
                   and
                - "cat" "sand"
                - so we know the valid splits were at index 3 and index 2
                - and we know for index 6 adding in either case, will make index 6 a valid last split
                adding the next valid splits to 6
                - ideally, we may want to keep multiple lists for when its valid as in the case above
        alg:
        Map from Int(the index) to List of valid splits Lists
        k = s.length
        for i in range(k) 
            map index k to a new ArrayList of ArrayLists with index 0 as the first
        for i in range(1, k)
            map.get(i-1) -> ArrayList of valid splits for i-1
            for list in validSplitList:
                lastValid = lastValid.get(lastValid.size()-1)
                if s.subString(lastValid, i) is in wordDict:
                    list.add(i)
                    if i == s.length()-1 
                        return true
        
        return false

        ADD: struggled to find a valid solutino on my own, so ref'd NeetCode
        - overall have the general idea, but for sure makes more sense to use DFS to pursue finding
        the next valid subsequence
    C:  

     */

    private HashSet<String> wordSet;
    private Boolean[] memo;
    private int t;

    public boolean wordBreak(String s, List<String> wordDict) {

        // convert the given wordDict to a set
        wordSet = new HashSet<>(wordDict);

        // create a new array for memoization, with length of string s
        // initially null
        memo = new Boolean[s.length()];

        // store max wordLength in wordDict
        t = 0;
        for (int i = 0; i < wordDict.size(); i++) {
            t = Math.max(t, wordDict.get(i).length());
        }
        
        return dfs(s, 0);
    }

    // DFS helper function
    private boolean dfs(String s, int i) {

        // we reached a DFS point where we successfully
        // found a split with the last index was part of a valid word
        // given previous splits
        if (i == s.length()) {
            return true;
        }
        // initially null as it uses Boolean object
        if (memo[i] != null) {
            return memo[i];
        }

        // given a substring that starts at j, check any substrings of size up to
        // the maxlength word, if its in the wordSet. If it is, proceed down
        // DFS starting at j+1, and set memo[i] = true, and return true
        for (int j = i; j < Math.min(i + t, s.length()); j++) {
            if (wordSet.contains(s.substring(i, j + 1))) {
                if (dfs(s, j + 1)) {
                    memo[i] = true;
                    return true;
                }
            }
        }
        memo[i] = false;
        return false;
    }
}