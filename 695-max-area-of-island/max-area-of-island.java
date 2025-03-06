class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // P: given a grid, where 1's represent land, find the 
        // largest island, which is the island with the most connected
        // 1's (horizontal or vertical)
        // If we consider all 1's as nodes, in a graph, and each island is
        // its own connected component, we are essentially, trying to find
        // the largest connected component
        // E: the example has an island mass of size 6
        // D: If we're doing DFS, then we would use queues of some sort
        /**
         * - we can also design our own union-find and somehow do that
         */
        // A: 
        /**
         * Brainstorm:
         * - we can use DFS for sure, to find each individual island
         * - we can also for sure do UnionFind to do this
         * - but, how do we count the size of the islands? 
         * - one option I was thinking, is with DFS, we can possibly
         *   keep track of when we're currently finding a continuous island
         *   we keep an int size, that increments every time we find a new 1
         *   from the current node we're looking at, and we also keep track 
         *   of a current max.
         *   Once an island is complete and there are no other adjacent 
         *   spots to look for, then we compare the current size to the max
         *   and replace, if bigger
         *  Algo:
         *  - DepthFirstSearch
         *    int currentSize = 0;
         *    int maxSize = 0;
         *    init an Empty Set of SeenSpots
         *    iterate through the grid, row then column
         *      check if grid[row][column] == 1 && !seenSpots.contains(grid[row][col])
         *         call dfsIslandArea(new ArrayList (row,col))
         *           checkDirectionsForIsland(current) 
         *               - adds any nonSeenSpots to the stack, that are above
         *               below, to the left and to the right of the currentSpot
         *        max = Math.max(currentSize, maxSize)
         *    dfsIslandArea(new ArrayList (row, col)) 
         *       check if position is valid:
         *           return 0 if not
         *       if it is, recursively calculate the area of the island
         */

        int currentSize = 0;
        int maxSize = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        HashSet<ArrayList<Integer>> seen = new HashSet<>();

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                ArrayList<Integer> position = new ArrayList<>();
                position.add(i);
                position.add(j);
                currentSize = depthFirstSearchIslandArea(grid, i, j, seen);
                maxSize = Math.max(maxSize, currentSize);
            }
        }
        return maxSize;
    }

    public int depthFirstSearchIslandArea(int[][] grid, int row, int col, HashSet<ArrayList<Integer>> seen){
        ArrayList<Integer> position = new ArrayList<>();
        position.add(row);
        position.add(col);
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols 
            || seen.contains(position) || grid[row][col] == 0) {
            return 0;
        }
        seen.add(position);
        return (1 + depthFirstSearchIslandArea(grid, row-1, col, seen) // above
            + depthFirstSearchIslandArea(grid, row+1, col, seen) // below
            + depthFirstSearchIslandArea(grid, row, col - 1, seen) // left
            + depthFirstSearchIslandArea(grid, row, col + 1, seen)); // right
    }

}