class Solution {
    /**
    P:
    - given: an integer array: nums
        - at index i, nums[i] represents the max spots number of steps one can
        jump to at that point
    - return the minimum number of jumps to reach the last index
    - similar to Jump Game I, but rather than returning if possible, we want
    to return min jumps needed to get to the end
    - also we know its possibe to reach the end
    E:
    - given the examples, its not too difficult to understand
    D:
    - we can likely use BFS or DFS as one possible way to do this, so we'd need
    a Set to handle that, but lets see first
    - arrays of course
    A:
    - one possibe way we can approach this is via Decision Trees, where we proceed 
    down each possible route from the first index, and return the jumps taken once
    once we reach the end
    - particularly using BFS could essentially guarantee this, but would this be efficient?
        - given the possible branch sizes, and that each node could potentially reach any other following
        edge... could be O(n^2)
    - however, how else can we solve this? possibly more efficiently?
    - what if starting from the first index, we examine all the nodes it can reach, and greedily choose
    the next largest number it can reach? or ofc, if its the last index already
    - we'll use a while loop, and count the steps taken
    - int steps = 0
    - while (i != n-1) 
        argMax = i
        - for (j in range nums[i])
            - if (nums[i+j] > nums[argMax])
                argMax  = i+j
        i = argMax
        steps +=1
    - return steps

    - UPDATE after NeetCode soln:
        - rather than looking for argmax, simply just look for furthest
        reachable from the current index, and use a BFS window to explore
        another window
    C:
     */
    public int jump(int[] nums) {
        int steps = 0;
        int left = 0;
        int right = 0;
        int n = nums.length;
        while (right < n-1) {
            int farthest = 0;
            for (int j = left; j <= right; j++) {
                farthest = Math.max(farthest, j + nums[j]);
            }
            left = right+1;
            right = farthest;
            steps+=1;
        }
        return steps;
    }
}