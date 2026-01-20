class Solution {
    /**
    P:
        - Given: 
                a String s
                a String p - a pattern
        - Return:
                boolean true if s matches the pattern
                false otherwise  
        - we know:
            '.' means matches a single character
            '*' means zero or more of the preceding element
    E:
        - the examples make sense:
            E1 makes sense to be false as the pattern 'a' simply
            expects one a, but s="aa"
            E2 makes sense as 'a*' means there is at least 0 or more
            'a' in s, which is true for s="aa"
            E3 also makes sense as '.' means any character, and
            '*' means zero or more of any character, which is satisfied
            by s='ab'
    D:
        - 
    A:
        - we have two pointers between s and p: 
            int i, int j = 0, 0 
        - if p[j+1] = '*' that means the char p[j] can be repeated 0 or more times
        - make a decision: 
            - do i elect to add p[j] or continue? 
                - check, does adding p[j] match the char s[i]
                - if so, update i++ and recursively compare (i, j)
                - if not, if we have a '*' we will want to update 
                j to j + 2, if not, update to j + 1
    C:
        - 
     */
    public boolean isMatch(String s, String p) {
        // TOP-DOWN Memoization
        return dfs(0, 0, s, p);
    }

    // brute force solution
    public boolean dfs(int i, int j, String s, String p) {
        // base case:
        // when both i and j are greater than the lengths, we know we have exhausted 
        // both chars in s and p
        if (i >= s.length() && j >= p.length()) {
            return true;
        }
        if (j >= p.length()) { // we went through all chars in pattern and no longer can match items in s
            return false;
        }

        // first char of each string matches ^
        boolean match = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
        // is the following char a '*'?
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return dfs(i, j + 2, s, p) ||       // don't use the '*'
                match && dfs(i + 1, j, s, p);  // use the '*'
        }
        if (match) {
            return dfs(i + 1, j + 1, s, p);
        }

        return false;
        
    }
}