class Solution {
    int numRows;
    int numCols;
    HashSet<Pair<Integer, Integer>> path = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        // P:
        /**
         * We are given an input word, and  a grid of letters
         * If we can start from a letter, and going from that position to
         * another cell, either adjacent or horizontal, is it possible to 
         * find a sequence of moves, where the letters from the sequence spell the word?
         * NOTE we can't use the same box twice
         */
        // E:
        /**
         * Given the first example, given an input word "ABCCED", we can construct that word, 
         * if we start at the top left, and proceed the following sequence:
         * RIGHT RIGHT, DOWN, DOWN, LEFT.
         */
        // D: 
        /** Since we can't use the same box twice, we may want to keep track of visited boxes
         * so we'll use a HashSet. We may also want to use a stack or queue in a DFS or BFS approach
         * May need to also keep track of level in case, though pointer should suffice,
         * if handled correctly
         */
        // A: 
        /**
         * Idea here is to use one of our favorite search algs, BFS or DFS. Leaning towards DFS
         * We'll probably want to keep a pointer of some sort, to keep track of what letter we're on
         * in the string, that will increment and decrement depending on the if we are making progress
         * or moving back if we decide to backtrack
         * First, we'll want to loop through the entire grid and select for the possible first letter
         * nodes and add it to a Stack
         * Picking any one of these nodes, perhaps through, we start with the stack and proceed
         * and check the letters
         * May need to keep track of depth of search in stack, so I might use a map to do so
         * 
         * while stack is not empty
         *   currentPosition = stack.pop()
         *    - if pointer > word.length() return true;
         *    - if word.charAt(pointer) == current.letter:
         *        - pointer++
         *    - else:
         *        - pointer--
         *    - checkAllNeighbors and add to Stack
         * if we exit while loop and not find the word, return false
         * checkAllNeighbors(stack, word, grid, row, col, pointer)
         *    int[][] directions ={ {-1, 0}, {0, -1}, {1, 0}, {0, 1} }
         *    for (newRow, newCol in directions)
         *      if next letter in word not equal to letter at grid position
         *         dont add to the stack
         *      else 
         *         add position to stack
         */

        numRows = board.length;
        numCols = board[0].length;

        // loop through and look for potential start spots
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if(depthFirstSearch(board, word, i, j, 0)) {
                    return true;
                }      
            }
        }
        return false;
    }

    public boolean depthFirstSearch(char[][] board, String word, int row, int col, int position) {
        
        if (position == word.length()) return true;

        if (row < 0 || col < 0 || row >= numRows || col >= numCols ||
            board[row][col] != word.charAt(position) || 
            path.contains(new Pair<>(row, col))) return false;

        int[][] positions = { 
            {-1, 0}, 
            {0, -1},
            {1, 0},
            {0, 1}
        };

        path.add(new Pair<>(row, col));
        boolean result = false;
        for(int[] newPos : positions) {
            int newRow = row + newPos[0];
            int newCol = col + newPos[1];
            result = result || depthFirstSearch(board, word, newRow, newCol, position + 1);    
        }
        path.remove(new Pair<>(row, col));
        return result;
    }
}