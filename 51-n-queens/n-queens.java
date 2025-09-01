class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        // we will represent the board as a list of max-length n, 
        // in which for an index i, corresponds to the column
        // queen in row i is found in
        // list of all possible solutions
        List<List<Integer>> possible = new ArrayList<>();
        // a queue of board states
        Queue<List<Integer>> frontier = new LinkedList<>();
        // generate n boards, where for each new board
        // place one queen, in the 0-th row, in the i-th column
        for(int i = 0; i < n; i++) { 
            List<Integer> newBoard = new ArrayList<>();
            newBoard.add(i);
            frontier.add(newBoard);
        }

        while(!frontier.isEmpty()) {
            List<Integer> node = frontier.poll();
            if (node.size() == n) {
                possible.add(node);
            }
            List<Integer> validPlacements = nQueensHelper(n, node);
            for (int nextPosition : validPlacements) {
                List<Integer> newState = new ArrayList<>();
                // copy to dest state, from node (src)
                for (int i = 0; i < node.size(); i++) {
                    newState.add(node.get(i));
                }
                
                newState.add(nextPosition);
                frontier.add(newState);
            }
        }


        List<List<String>> possibleStr = new ArrayList<>();
        for (List<Integer> solution : possible) {
            List<String> solutionString = new ArrayList<>();
            // i corresponds to the row #
            for (int i = 0; i < solution.size(); i++){
                int column = solution.get(i);
                StringBuilder currentSolution = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if(j == column) {
                        currentSolution.append("Q");
                    } else {
                        currentSolution.append(".");
                    }
                }
                solutionString.add(currentSolution.toString());
            }
            possibleStr.add(solutionString);
        }

        return possibleStr;
    }

    public boolean nQueensValid(List<Integer> board) {
        int i = 0;  // left queen row (as in on list)
        int j = 1;  // right queen row (relative placement on list)
        int n = board.size();

        // iterate through and make sure, 
        // are there any queens on particular spots?
        while(i < n - 1) {
            int leftValue = board.get(i);  // column value of left queen
            int rightValue = board.get(j);  // column value of right queen
            if (board.get(i) == board.get(j)) // check for same column
                return false;
            // check if along diagonals
            if(Math.abs(j - i) == Math.abs(rightValue - leftValue))
                return false;
            j += 1;  // increase right 
            if (j == n) { 
                // now increase left and reset j to 1 above i
                i += 1;
                j = i + 1;
            }
        }
        return true;
    }


    /*
    Given a current board state
    generate an iterable of valid placements for the next queen.
    - int n : An integer size of board (rows, columns)
    - List : A list representing the current state of the board
    - return an list containing valid placements for next row, 
      given current board
    */
    public List<Integer> nQueensHelper(int n, List<Integer> board){
        List<Integer> valid = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if (board.contains(i)) {
                continue; // skip if queen on column i, given current board
            }
            board.add(i);
            if (nQueensValid(board)) {
                valid.add(i); // add the column i, if valid
            } 
            board.remove(board.size() - 1); //backtrack
        }
        return valid;
    }

}