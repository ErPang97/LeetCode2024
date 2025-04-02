class Solution {

    private int rows;
    private int cols;

    public void solve(char[][] board) {
        /**
        P:
            - we want to capture all regions that are surrounded
            - all regions are connected 'O' cells.
            - we can only capture regions if none of the members are 
            on the edge of the board
        E:
            - the examples are pretty clear, the interior region
            for example 1 is captured because it is not touching 
            the edge of the board
            - meanwhile the bottom region is, so it is certainly ignored
        D: 
            - some sort of queue or stack possibly for BFS/DFS respectively
        A:
            - IDEA 1:
                - start at any of the O's, and if after searching all of its
                connected neighbors, no region touching the border is found, 
                then capture all the nodes in the region
            - IDEA 2:
                - if we did a union find to connect all connnected components
                we could find the connected components that have an edge node
            - IDEA 3:
                - loop through the edge nodes, and add those 'O' to a set
                - do BFS/DFS from those edge nodes, and add those that are connected
                to it, to a set
                - finally, loop through all the nodes, and any of those 'O' nodes
                not in the set must be a captured region


            depthFirstSearch(int[] current, Set visited)
                - check all four positions and do DFS on 
                each of the four positions that are valid
                - if current not in visited
                    visited.add(position)
                    nonSurroundedNodes.add(position)
                
        C:
        */

        HashSet<List<Integer>> nonSurrounded = new HashSet<>();

        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O'  && (i == 0 || i == rows-1 || j == 0 || j == cols - 1)){ //on the edge 
                    ArrayList<Integer> edge = new ArrayList<>();
                    edge.add(i);
                    edge.add(j);
                    nonSurrounded.add(edge);
                    depthFirstSearch(nonSurrounded, edge, board);
                } 
            }
        }

        System.out.println(nonSurrounded);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O'){ // any region
                    ArrayList<Integer> pos = new ArrayList<>();
                    pos.add(i);
                    pos.add(j);
                    if (!nonSurrounded.contains(pos)) {
                        board[i][j] = 'X';
                    }
                } 
            }
        }

    }

    public void depthFirstSearch(HashSet<List<Integer>> nonSurrounded, List<Integer> current, char[][] board) {
        if (!nonSurrounded.contains(current)) nonSurrounded.add(current);
        int[][] directions = new int[][]{
            {1, 0}, {-1, 0},
            {0, 1}, {0, -1}
        };
        for (int[] direction : directions) {
            int row = current.get(0) + direction[0];
            int col = current.get(1) + direction[1];

            ArrayList<Integer> next = new ArrayList<>();
            next.add(row);
            next.add(col);
            if (!nonSurrounded.contains(next) && row >= 0 && row < rows 
                && col >= 0 && col < cols && board[row][col] == 'O') {
                depthFirstSearch(nonSurrounded, next, board);
            }
        }
    }
}