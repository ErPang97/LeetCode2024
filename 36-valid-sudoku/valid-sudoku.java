class Solution {
    public boolean isValidSudoku(char[][] board) {
        // P: determine if the given board is a valid
        // in sudoku
        // E: is given
        // D: HashMap to keep track of counts
        // A: 
        // C:
        for(int i = 0; i <= 8; i++){ // iterate through all possible rows/col
            if (!validRow(i, board) || !validColumn(i, board)){
                System.out.println("not valid in row or col");
                return false;
            } 
        }
        for(int i = 0; i <= 8; i+=3){
            for(int j = 0; j <= 8; j+=3){
                if(!validInNineByNine(i, j, board)){
                    System.out.println("not valid in box");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validRow(int row, char[][] board) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int j = 0; j <= 8; j++){ // iterate through the row
            char current = board[row][j];
            int count = 0;
            if(current != '.'){
                if(counts.get(current) != null){
                    count = counts.get(current);
                    count++;
                    counts.replace(current, count);
                    if (count > 1) return false;
                } else{
                    counts.put(current, 1);
                }
            }
        }
        return true;
    }

    public boolean validColumn(int col, char[][] board){
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int j = 0; j <= 8; j++){ // iterate through the column
            char current = board[j][col];
            int count = 0;
            if(current != '.'){
                if(counts.get(current) != null){
                    count = counts.get(current);
                    count++;
                    counts.replace(current, count);
                    if (count > 1) return false;
                } else{
                    counts.put(current, 1);
                }
            }
        }
        return true;
    }


    public boolean validInNineByNine(int row, int col, char[][] board) {

        int[] leftOrTop = {0, 1, 2};
        int[] middle = {3, 4, 5};
        int[] rightOrBottom = {6,7,8};

        int[] rowToIterate;
        int[] colToIterate;

        // determine what row to iterate on
        if(0 <= row && row <= 2){
            rowToIterate = leftOrTop;
        } else if (3 <= row && row <= 5){
            rowToIterate = middle;
        } else {
            rowToIterate = rightOrBottom;
        }

        // determine what col to iterate on
        if(0 <= col && col <= 2){
            colToIterate = leftOrTop;
        } else if (3 <= col && col <= 5){
            colToIterate = middle;
        } else {
            colToIterate = rightOrBottom;
        }

        HashMap<Character, Integer> counts = new HashMap<>();
        int count = 0;
        for (int i: rowToIterate){ // iterate through row
            for(int j : colToIterate){ // iterate through column
                char current = board[i][j];
                count = 0;
                if(current != '.'){
                    if(counts.get(current) != null){
                        count = counts.get(current);
                        count++;
                        counts.replace(current, count);
                        if (count > 1) return false;
                    } else{
                        counts.put(current, 1);
                    }
                }
            }
        }

        return true;
    }

}