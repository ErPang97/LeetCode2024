class Solution {

    private int rows;
    private int cols;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        /**
            P:
                _ We're given an m by n grid and heights[r][c],
                where r, c is row and col index
                - It rains a lot on the island and rain water
                can travel from any cell r,c to any adjacent cell
                given that the adjacent cell has height, less than
                or equal to the hiehgt at that
                - we want to find a list of all the cells, from which
                rain water can flow to both Pacific and Atlantic oceans
                - this means that for that cell
                it is either directly adjacent to one or
                both of the oceans, or there exists a path for water to flow
                from that cell to both oceans 
         */
         /**
            E: for the first example, it makes sense
         */
         /**
            D: should just need ArrayLists
         */
         /**
            A: 
            Brainstorm:
            - we want to keep track if the cell can reach both oceans
            - we could keep running BFS or DFS until a path from that cell
            to both oceans is found, but this sounds quite time expensive
            - in fact, doing this for every cell is too expensive, O((N*M)^2)
            so how to cut this?
            - if we try running from the coasts, maybe we can look for all the cells
            that it can reach, then look for the cells that both can reach
            - this time, we look for adjacent cells with heights greater than or
            equal to the height at the current cell
            - we could use two sets, one for all cells that can reach
            pacific coast, and one for all cells that can reach atlantic coast
            - then we iterate in both sets, and see where the intersection of the 
            two sets is
          */
        // C:

        HashSet<List<Integer>> atlantic;
        HashSet<List<Integer>> pacific;
        atlantic = new HashSet<>();
        pacific = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();
        rows = heights.length;
        cols = heights[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ArrayList<Integer> position = new ArrayList<>();
                position.add(i);
                position.add(j);
                if (j == cols - 1 || i == rows - 1) {
                    depthFirstSearch(position, heights, atlantic);
                }
                if (i == 0 || j == 0) {
                    depthFirstSearch(position, heights, pacific);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ArrayList<Integer> position = new ArrayList<>();
                position.add(i);
                position.add(j);
                if (pacific.contains(position) && atlantic.contains(position)) {
                    result.add(position);
                }
            }
        }

        
        return result;
    }

    public void depthFirstSearch(List<Integer> position, int[][] heights, HashSet<List<Integer>> visited){
        if (visited.contains(position)) return;
        visited.add(position);
        int row = position.get(0);
        int col = position.get(1);

        int[][] directions = new int[][] {
            {1, 0}, {0, 1},
            {-1, 0}, {0, -1}
        };

        for (int[] direction : directions) {
            // check adjacent positions
            int newRow = position.get(0) + direction[0];
            int newCol = position.get(1) + direction[1];
            // see if it falls in bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                ArrayList<Integer> next = new ArrayList<>();
                next.add(newRow);
                next.add(newCol);
                // only add if not visited yet, and if the height at new position is greater than current
                if (!visited.contains(next) && heights[newRow][newCol] >= heights[row][col]) {
                    depthFirstSearch(next, heights, visited);
                }
            }
        }
    }
}