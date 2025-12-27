class Solution {
    /**
    P:
    - given: an int[][] matrix
        - dimensions: int m rows by int n columns
    - return: an int length of the longest increasing
      path in the matrix
    - path rules:
        - from any cell, we can move either in 4 
          directions: left, right, up and down
          only
        - by increasing path we mean...:
            - it appears that from a starting point
              any following cell, must contain a 
              value greater than the last
        - the longest increasing path is simply
          the longest valid path of increasing 
          digits 
        - so we want the length of that path
    E:
    - the examples are pretty clear:
        ie: example 1: [1, 2, 6, 9] is the clear longest path
    D:
    - an idea does look like graph traversal so maybe a queue or stack
    - aditionally a set to keep track of visited locations
    A:
    - a probably brute force idea is to iterate through all cells, and 
      from each cell, do a DFS to find the longest path, and compute the length
      of that path
        - this would likely be O((n*m)^2 * |E|)
        - and |E| is 4 * m * n, as each cell could have up to 4 edges each
          which is not ideal!
    - how can we use dynamic programming? It's clear that by using DFS, 
      we'd likely be revisiting many nodes and edges over and over, but if we had
      visited them once, we most likely have found the longest increasing
      path FROM that point
        - so, what if we use memoization, to store the longest path from
          each point?
    C:
     */
    
    int ROWS;
    int COLS;
    Map<List<Integer>, Integer> dpCache;

    public int longestIncreasingPath(int[][] matrix) {
        ROWS = matrix.length;
        COLS = matrix[0].length;
        dpCache = new HashMap<>(); // here we store (r, c) -> LIP from that point

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                dfs(row, col, -1, matrix); // every cell is atleast 0, so guaranteed any first value >= -1
            }
        }

        int result = 1;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                List<Integer> pos = new ArrayList<>();
                pos.add(row);
                pos.add(col);

                result = Math.max(result, dpCache.get(pos));
            }
        }
        return result;
    }

    public int dfs(int row, int col, int prevVal, int[][] matrix) {
        if (row < 0 || row == ROWS ||
            col < 0 || col == COLS ||
            matrix[row][col] <= prevVal) {
            
            return 0;

        }
        List<Integer> pos = new ArrayList<>();
        pos.add(row);
        pos.add(col);

        if (dpCache.containsKey(pos)) {
            return dpCache.get(pos);
        }

        int result = 1;
        // position below current
        result = Math.max(result, 1 + dfs(row + 1, col, matrix[row][col], matrix));
        // position above
        result = Math.max(result, 1 + dfs(row - 1, col, matrix[row][col], matrix));
        // position to the right
        result = Math.max(result, 1 + dfs(row, col + 1, matrix[row][col], matrix));
        // position to the left
        result = Math.max(result, 1 + dfs(row, col - 1, matrix[row][col], matrix));
        // store into cache
        dpCache.put(pos, result);
        return result;
    }
}