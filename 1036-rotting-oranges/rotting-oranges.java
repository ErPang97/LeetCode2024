class Solution {

    private int rows;
    private int cols;

    public int orangesRotting(int[][] grid) {
        // P:
        /**
         * We are given an m x n grid, and each cell either
         * is empty, has a fresh orange or has a rotten orange.
         * Every minute, any adjacent orange, (up, down, left, right)
         * to a rotten orange, becomes rotten. So all adjacent spots
         * are possibly turned to rotten
         * If all oranges can be rotten, we return the minimum
         * time it takes, else return -1
         */
        // E:
        /**
         * The examples are pretty clear
         */
        // D:
        /**
         * Since we're checking adjacents, we will want
         * to do, a search, likely BFS, so we'll use 
         * a Queue maybe, though we also want to keep track 
         * of total time elapsed, as an int.
         * We also will want to likely a BFS with mutltiple 
         * sources, so we somehow need to keep track of the level
         * of search from each source; maybe map each queue to 
         * the depth?
         */
        // A:
        /**
         * init visited 
         * init distanceMap
         * init queue
         * level = 0
         * General Idea:
         * for i in range(m):
         *    for j in range(n):
         *       if (grid[i][j] == 2):
         *          add new int[]{i, j} to Queue
         *          add new int[] to Map with level
         * 
         * while(queue not empty):
         *    current = queue.pop
         *    set nums at current to 2 // making orange rotten
         *    if current not in visited
         *       add to visited
         *       level = distanceMap.get(current)
         *       check the 4 directions from current
         *          add to queue if it is == 1
         *          add level+1 to distanceMap from each of the 4 directions that are valid
         *  once done, check if there are any 1's left in the grid O(m*n), but is there a faster way?
         */
        // C:
        
        rows = grid.length;
        cols = grid[0].length;

        HashSet<int[]> visited = new HashSet<>();
        HashMap<int[], Integer> distanceMap = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();

        int level = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    int[] pos = new int[]{i, j};
                    distanceMap.put(pos, level);
                    queue.add(pos);
                }
            }
        }


        while(!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            if(!visited.contains(currentPosition)) {
                visited.add(currentPosition);
                level = distanceMap.get(currentPosition);
                int x = currentPosition[0];
                int y = currentPosition[1];
                addNonVisitedOrangesToQueue(queue, distanceMap, x, y, grid, level);
            }
        }

        for (int i = 0 ; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        // the min minutes should be the maximum value in the map
        int max = 0;
        for (int distance : distanceMap.values()) {
            max = Math.max(max, distance);
        }

        return max;
    }

    public void addNonVisitedOrangesToQueue(Queue<int[]> queue, HashMap<int[], Integer> distanceMap, int x, int y, int[][] grid, int level) {

        int[][] positions = new int[][]{
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1}
        };

        for(int[] pos: positions) {
            int newX = x + pos[0];
            int newY = y + pos[1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                if (grid[newX][newY] == 1){
                    grid[newX][newY] = 2;
                    int[] newPosition = new int[]{newX, newY};
                    queue.add(newPosition);
                    distanceMap.put(newPosition, level+1);
                }
            }
        }


    }

}