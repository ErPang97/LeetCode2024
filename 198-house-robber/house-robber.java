class Solution {
    public int rob(int[] nums) {
        // P:
        /**
         * Goal: find the MAX amount of money possible
         * that we can rob
         *
         * Main constraint: if I rob house i, I cannot rob houses 
         * i - 1, or i + 1, as that will trigger an alarm
         *
         * Our array nums, is a list of numbers, where i represents the
         * house, and nums[i] represents the amount of money
         * that can be stolen from each house
         *
         * Essentially, we're trying to find a good sequence of houses
         * that we can steal from, that will result in maximum profit
         *
         * Effectively, we can break this down into subproblems, for a given
         * index k, that represents the last house we rob from, whats the maximum
         * amount of money possible that can be stolen, that leads to house k, 
         * without alerting the police, meaning, we should for sure check any index
         * i < k, is a valid place to steal from
         */
        // E:
        /**
         * Given the examples! IE: example 1 makes sense because the only possible 
         * options are to just rob one of the houses individiually ofc, or 
         * start at house 0 and go to either house 2 or house 3,
         * or start at house 1, and go to house 3. But the max, is the sequence from
         * house 0 to house 2
         */
        // D:
        /**
         * Should only really need Arrays to do
         */
        // A:
        /**
         * def rob(nums):
         *    List maxAmountToHouse = [0] * len(nums)
         *    for i in range(length(nums)):
         *        candidateValsToHouse = [maxAmountToHouse[j] for j in range(0, i) && canRobHouse(i, j)]
         *        maxAmountToHouse[i] = nums[i] + max{candidateValsToHouse}
         *    return max{maxAmountToHouse, 0}
         *
         * def canRobHouse(i, j): # where i is the house that we end at, given we want to rob house j
         *    return j != i + 1 || j != i - 1
         */
        // C:

        int maxAmount = 0;
        List<Integer> maxAmountToHouse = new ArrayList<>();

        // populating the array with 0's to the length of nums
        for (int i = 0; i < nums.length; i++) {
            maxAmountToHouse.add(0);
        }

        for (int i = 0; i < nums.length; i++) {
            List<Integer> candidateValsToHouse = new ArrayList<>();

            // given the house i, we want to look for all the houses that can be robbed,
            // if we end at house i, and we want to get the maximum amount of money that 
            // can be stolen from said house
            for (int j = 0; j < i; j++) {
                if (canRobHouse(i, j)){
                    candidateValsToHouse.add(maxAmountToHouse.get(j));
                }
            }

            // the new max at house i, will be the value at nums[i] plus
            // the max amount given the possible candidates that lead to this
            // house
            int maxInCandidates = 0;
            for (int val : candidateValsToHouse) {
                maxInCandidates = Math.max(maxInCandidates, val);
            }

            maxAmountToHouse.set(i, nums[i] + maxInCandidates);
        }

        for (int i : maxAmountToHouse) {
            maxAmount = Math.max(i, maxAmount);
        }

        return maxAmount;
    }

    /**
     * @param i - the house that we are comparing j to 
     * @param j - the house that we are looking to rob
     */
    public boolean canRobHouse(int i, int j) {
        return j != i + 1 &&j != i - 1;
    }
}