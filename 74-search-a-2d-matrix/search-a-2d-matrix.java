class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // P:
        // E:
        // D: none 
        // A:
            /**
            - general idea, use the beginning and end indices
            to help narrow down which row, the target should be 
            found in (a binary search of the row index)
            - then, once thats known, do a binary search on 
            the specified row
            - pseucode:
                - use minRow and maxRow, which will be firstRow initially, 
                and maxRow 
                - get midPoint row = (lastRow + firstRow)/2
                - if(midPoint[0] < target < midPoint[width-1]) its in this row
                - else if(target > midRow[0] && target > midRow[width-1]) 
                    - update minRow to be midRow + 1
                - else if(target < midRow[0] && target < midRow[width-1])
                    - update maxRow to be midRow - 1
             */
        // C: 

        int minRowIndex = 0;
        int maxRowIndex = matrix.length - 1;

        int width = matrix[0].length;

        int targetRowIndex = 0;

        while(minRowIndex <= maxRowIndex){

            int midRowIndex = (minRowIndex + maxRowIndex)/2;
            if(matrix[midRowIndex][0] <= target 
                    && matrix[midRowIndex][width - 1] >= target) {
                // we know its on the mid row
                targetRowIndex = midRowIndex;
                break;
            }
            else if(target > matrix[midRowIndex][0]
                    && target > matrix[midRowIndex][width - 1]){
                // its not on mid row and greater, so update min, to midRow
                minRowIndex = midRowIndex + 1;
            }
            else if(target < matrix[midRowIndex][0]
                    && target < matrix[midRowIndex][width - 1]){
                // its not on mid row and less, so update max, to midRow
                maxRowIndex = midRowIndex - 1;
            }
        }

        int[] searchRow = matrix[targetRowIndex];

        int min = 0;
        int max = width-1;
        while(min <= max){

            int mid = (min + max)/2;
            if(target == searchRow[mid]){
                return true;
            } else if(target > searchRow[mid]){
                min = mid + 1;
            } else if(target < searchRow[mid]) {
                max = mid - 1;
            }
        }
        
        return false;
    }
}