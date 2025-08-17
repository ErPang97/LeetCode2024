class Solution {
    /**
    P:
        - we are given an 2D int array named matrix
        - if an element is 0, we simply, modify in place, 
        its entire row and column to 0        
    E:
        - the examples make sense
    D:
        - maybe just to keep track of where the original zeros were
        we could use a set 
    A:
        - a perhaps, Brute Force way of doing this would be to first
        iterate through the entire array to search for 0's
        - if one is found, add its coordinates to a set
        - then, we can iterate through the set to find what rows
        and columns need to be changed to 0
        - however, what if we want to reduce the space complexity?
        - what if we use 2 arrays called row, and column, each of length
        respective to the matrix (row is matrix.length, col is of matrix[0].length)
        - we can simply keep track if those columns need to be changed
    C:
     */
    public void setZeroes(int[][] matrix) {
        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];

        // iterate through the matrix values, 
        // and set the row and col that need to be changed to 0
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        // iterate and replace, whenever a 
        // row or column is to be replaced
        for (int r = 0; r < rows.length; r++) {
            for (int c = 0; c < cols.length; c++) {
                if (rows[r]==1 || cols[c]==1) {
                    matrix[r][c] = 0;
                }
            }
        }
    }


}