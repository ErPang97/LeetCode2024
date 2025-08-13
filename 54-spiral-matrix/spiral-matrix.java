class Solution {

    /**
    P: 
        - given an m-by-n matrix int
        - we simply want to return a list of all the elements
        in spiral order
        - this means starting from the top, then go down the right most column
        - to the bottom, 
    E:
        - the problem is easy to understand
    D:
        - just an array to store the values
    A:
        - a general idea is to consider the matrix, as consisting of concentric squares
        where once we print the outer square, going around the values, then we do the same
        again for the interior square

        index for top row
            loop through top row
                add to arr
            loop through rigth column
                add to arr
            loop through bottom row
                add to arr
            loop through left row (from bottom to just under the top row)
                add to arr
            
            update what is the top, bottom, left and right by offsetting all by 1

    C:
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int left = 0, right = matrix[0].length;
        int top = 0, bottom = matrix.length;

        while (left < right && top < bottom) {
            // iterate through top row
            for (int i = left; i < right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;
            // iterate through right column
            for (int i = top; i < bottom; i++) {
                spiral.add(matrix[i][right - 1]);
            }
            right--;
            // if top condition broken, exit
            if (!(left < right && top < bottom)) {
                break;
            }
            // iterate through bottom row, right to left
            for (int i = right - 1; i >= left; i--) {
                spiral.add(matrix[bottom - 1][i]);
            }
            bottom--;
            // iterate through left column, bottom to top
            for (int i = bottom - 1; i >= top; i--) {
                spiral.add(matrix[i][left]);
            }
            left++;
        }

        return spiral;
    }
}