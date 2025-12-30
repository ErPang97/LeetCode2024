class Solution {
    /**
    P:
        - given:
            - String s
            - String t
        - return:
            - an int NUMBER of DISTINCT
            subsequencies of s, which equal t

        - recall a subsequence:
            - it is a sequence constructed from 
            an original sequence (in this case String s)
            that is obtained only by deleting characters
            but not changing the relative order
    E:
    - Example 1: we want to obtain String t = "rabbit"
        - we have a String s = "rabbbit"
        - this example is pretty clear
            - the subsequences in this case differ
            by which two b's are being used 
    - Example 2 is also pretty straightforward to understand
    D:
    - given that one idea that comes to mind is using a search
    algo, we could use a Set() or Map() to keep track
    of visited locations
    - with a graph approach, we could also define an adjacency list
    A:
    - the first idea that came to mind was to perhaps use some sort
    of search algorithm:
    idea 1:
        - int count = 0
        - for each letter:
                - DFS(indexOfSubsequence, String s, String t) 
        - DFS (indexOfSubsequence, String s, String t): // this can probably just be a recurisve fn.
            - if indexOfSubsequence == t.length and currentLetter of s 
            is equal to the final letter of t:
                - count +=1 
            - for each neighbor of letter:
                if (neighbor is the next letter in subsequence)
                    - DFS (indexOfSubsequence+1,  String s, String t)
            
    C:

     */
    public int numDistinct(String s, String t) {
        // we store a size 2 array (i, j) which
        // represents the indices i in s and j in t
        // we use this to keep track if we used a pair of
        // indices already
        Map<List<Integer>, Integer> cache = new HashMap<>();
        
        return dfs(s, t, 0, 0, cache);
    }

    public int dfs(String s, String t, int i, int j, Map<List<Integer>, Integer> cache) { 
        if (s.length() - i < t.length() - j) {
            return 0;
        }
        if (j == t.length()) {
            return 1;
        }
        if (i == s.length()) {
            return 0;
        }

        List<Integer> position = new ArrayList<>(2);
        position.add(i);
        position.add(j);
        if (cache.containsKey(position)) {
            return cache.get(position);
        }

        if (s.charAt(i) == t.charAt(j)) {
            cache.put(position, 
                    dfs(s, t, i + 1, j + 1, cache) + dfs(s, t, i + 1, j, cache)
                );
        } else {
            cache.put(position, 
                    dfs(s, t, i + 1, j, cache)
                );
        }
        return cache.get(position);
    }

}