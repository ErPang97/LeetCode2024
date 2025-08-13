class Solution {
    /**
    P:
    - given: a 2D array that represents a matrix
    - return: none! we want to rotate an array by 90 degrees in place
    E:
    - the examples make sense
    D:
    - no extra needed 
    A:
    - analyzing, it seems for a general matrix A, 
   and following the 90 degree rotation, the resultant matrix, A'
   the following seems to hold true

   A_(i,j) = A'_(j, n - 1 - j)
    - however, it is difficult to accomplish without making a new array
    as we need to keep track of the elements being replaced and rotated.

    - what if we try to rotate corners? and work from outside in?
    - as in, once we take care of the outer corners, we offset by 1 
    and rotate the values in those positions, continuing to do so, until we have taken 
    care of all values in the outer row. then, we move into the interior, and repeat the process for
    the inner squares
    C:
     */
    public void rotate(int[][] matrix) {
        int l = 0;
        int r = matrix.length - 1;

        while ( l < r ) {
            for(int i = 0; i < r - l; i++) {
                int top = l;
                int bottom = r;
                 //save the topleft
                int topLeft = matrix[top][l + i];

                //move bottom left into top left
                matrix[top][l + i] = matrix[bottom - i][l];

                // move bottom right into bottom left
                matrix[bottom - i][l] = matrix[bottom][r - i];

                // move top right into bottom right
                matrix[bottom][r - i] = matrix[top + i][r];

                // move top left into top right
                matrix[top + i][r] = topLeft;

            }
            r--;
            l++;
        }
    }
}