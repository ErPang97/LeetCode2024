class Solution {
    /**
    P:
        - given: ant int[] nums
            - representing balloons indexed in this array from i = 0 to i = n - 1
            - each balloon is labeled with a number on it nums[i]
        - if the i-th balloon is burst, we get:
            - nums[i - 1] * nums[i] * nums[i + 1]
                - if i - 1 or i + 1 is out of bounds, pretend they are valued 1
        - want to return: an int MAXIMUM coins we can collect by bursting balloons
          wisely 
    E:
        - question was confusing initially, but it makes sense with the examples
    D:
        - could maybe do a DFS approach to help if we were to define nodes
          containing the balloons popped which would could maybe use visited sets
          and an adjacency list perhaps
    A:
        - if we did a graph approach to this problem, via search, my main idea was
        to keep a running total, and just progress down the search tree
        - we would need to of course create the possible nodes and the edges
        but this also seems uneccessarily adding space
        - however, without additional space, we could perhaps use two pointers 
        i and j
        - Backtracking? Using this actually, is not efficient
        
        -- SOLUTION: 
        - Our DP subproblem is what if the balloon b, where b is 
          an int in the indices of nums, is the last balloon we pop
        - we want to consider then, the subarrays to the left
          and right of it
    C:
        - 
     */
    public int maxCoins(int[] nums) {
        // generate an array list rep, and append 1 
        // to either side O(n) time and additional space
        int n = nums.length;
        List<Integer> balloons = new ArrayList<>(n + 2);
        balloons.add(1);
        for (int num : nums) {
            balloons.add(num);
        }
        balloons.add(1);
        

        int[][] dpCache = new int[n + 2][n + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dpCache[i][j] = -1;
            }
        }

        return dfs(1, balloons.size()-2, dpCache, balloons);
    }

    public int dfs(int left, int right, int[][] dpCache, List<Integer> balloons) {
        if (left > right) {
            return 0;
        }
        if (dpCache[left][right] != -1) {
            return dpCache[left][right];
        }
        dpCache[left][right] = 0;
        for (int i = left; i < right + 1; i++) {
            // calculate the number of coins possible, popping this value at i
            // recall, we use pointers to keep track of what values are next to
            // this balloon
            int coins = balloons.get(left - 1) * balloons.get(i) * balloons.get(right + 1);
            // dfs to explore then, the left and right subarrays 
            coins += dfs(left, i - 1, dpCache, balloons) + dfs(i + 1, right, dpCache, balloons);
            dpCache[left][right] = Math.max(dpCache[left][right], coins);
        }
        
        return dpCache[left][right];
    }
}