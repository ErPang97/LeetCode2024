class Solution {
    /**
    P:
        - we're given a grid, a square (n by n) matrix 
            - grid[i][j] = elevation at point (i, j)
        - it starts raining at time t = 0, and continues
        to rain. At any time, t, any cell with elevation
        <= t is submerged/reachable
        - one can swim from a square to another 4 directionally
        adjacent square iff the elevation of both squares are 
        individually at most t
            - can swim infinite distances, but we must stay within
            boundaries of the grid
        - we want to find the minimum time to reach the bottom
        right square (n - 1, n - 1), if we start at the top
        left square
    E:
        Example 1:
            - at time t = 0, we cannot swim anywhere, as neither
            the square below (0, 0) or to the right is submerged
            - at time t = 1, we can swim only to the bottom square
            - at time t = 2, we can reach either the top right square
            or the bottom square
            - finally, at time t = 3, we can reach the bottom right square
            from either the bottom left square or the top right square
        Example 2: 
            - from the top left square, we cannot really move anywhere
            except left to right, while t slowly increases
            - once we hit that (1, 4) where the elevation is 5, we can't
            go anywhere until t = 16, as the next lowest elevation point adjacent
            to any of the squares we visited, is 16
            - this will be ofc. reachable once we hit t = 16
            - at that point, we the rest of the path (right to left to (2, 0) then down to (4, 0) followed
            by right to (4, 4)), is swimmable so we can swim across, noting we can swim infiinte distances
    D:
        - maybe a stack approach to DFS
        - set for visited nodes?
    A:
        - an idea would be to use DFS to try and reach the goal state since we know where we start and end
        - however, some key challenges are that we need to make sure that as we proceed down the DFS, we 
        make sure we only go down nodes that are reachable (t >= grid[i][j])
        - ALGO: 
            t = 0
            while (t < n^2)  (noting that max elevation is n^2)   O(n^2)
            - start at i = 0, j = 0 and add to Stack and Visited Set
            - while (stack is not empty)  O(n^2)
                - pop from stack -> current
                - if current is solution
                    - return t 
                - for each neighbor of current (4)
                    - if neighbor not in visited
                        - if neighbor.elevation <= t
                            - add to stack 
            - t+=1
        - can we improve this? This is clearly O(n^4)
        - Dijkstra's Algo!
    C: 
     */ 
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = { 
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        List<Integer> path = new ArrayList<>();
        path.add(grid[0][0]); // time/max-height
        path.add(0); // row = 0
        path.add(0); // column = 0

        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> Integer.compare(a.get(0),b.get(0))
            );
        minHeap.add(path);

        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> start = new ArrayList<>();
        start.add(0);
        start.add(0);
        visited.add(start);

        while (!minHeap.isEmpty()) {
            List<Integer> current = minHeap.poll();
            int time = current.get(0);
            int row = current.get(1);
            int col = current.get(2);
            List<Integer> currentLocation = new ArrayList<>();
            currentLocation.add(row);
            currentLocation.add(col);

            if (row == n - 1 && col == n - 1) {
                return time;
            } 
            for (int[] direction : directions) {
                int dRow = direction[0];
                int dCol = direction[1];

                int neighborRow = row + dRow;
                int neighborCol  = col + dCol;

                List<Integer> neighbor = new ArrayList<>();
                neighbor.add(neighborRow);
                neighbor.add(neighborCol);
                if (neighborRow < 0 || neighborCol < 0 
                    || neighborRow == n || neighborCol == n
                    || visited.contains(neighbor)) {
                    continue;
                }
                visited.add(neighbor);
                List<Integer> nextPart = new ArrayList<>(); 
                nextPart.add(Math.max(time, grid[neighborRow][neighborCol]));
                nextPart.add(neighborRow);
                nextPart.add(neighborCol);

                minHeap.add(nextPart);
            }
        }
        return -1;
    }
}