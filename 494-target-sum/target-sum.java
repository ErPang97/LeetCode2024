class Solution {
    /**
    P:
        - given a list of int, nums and an int target value
        - before each int, we can concat a '+' or a '-'
        meaning add or subtract that number to a total, 
        starting with 0, ofc.
        - we want to return the int number of
        how many different ways we can concat the
        '+' or '-' resulting in the target
    E:
        - the examples generally make sense
    D:
        -
    A:
        - the Brute Force way, may be to approach this using a tree like method
        - first, start with two branches, which is a plus and minus 
        in front of the first number
        - then, proceed down the depth of the tree, trying either + or -, and
        of course any time a result is the target. Once we reach the max depth
        (the length of the nums), we count each result to see which is the target
        - ofc. this would have a time complexity of perhaps, 2^n which is not efficient

        - how to improve? 
        - we can cache results, and use recursion
    C:
     */
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> dp = new HashMap<>();
        return backtrack(nums, target, 0, 0, dp);
    }

    public int backtrack(int[] nums, int target, int index, int currentSum, Map<String, Integer> dp) {
        // int[] currentKey = new int[]{index, currentSum};
        String currentKey = index+"+"+currentSum;
        // we use caching to avoid recomputing sums already seen
        if (dp.containsKey(currentKey)){
            return dp.get(currentKey);
        }

        // at end of array, we check if the current sum is the target and simply return 1 if so
        if (index == nums.length) {
            if(currentSum == target) return 1;
            else return 0;
        }

        dp.put(
            currentKey,
            backtrack(nums, target, index+1, currentSum + nums[index], dp)
            + backtrack(nums, target, index+1, currentSum - nums[index], dp)
        );

        return dp.get(currentKey);
    }
}