class Solution {
    public int rob(int[] nums) {
        // P: 
        /**
         * The problem is similar to the House Robber I, 
         * except now, the first and last houses are neighbors,
         * in a cycle.
         * As mentioned by the hint, this problem can be degenerated into
         * the House Robber problem if we consider only robbing 
         * from House 1 to House n - 1, or House 2 to House n,
         * bypassing the requirement that we cannot rob from
         * House 1 and House n simultaneously.
         */
        // E:
        /**
         * IF we rob from house 1, we cannot have robbed from house 3
         * from example 1
         * or in a general case, house = nums.length -1.
         */
        // D:
        /**
         * Should only need an Array, but we'll think on it!
         */
        // A:
        /**
         * SubProblem is, given we stop robbing at house k, what is
         * the maximum amount of money we can rob, up to and including
         * at that point. IT will always be the value at the house k +
         * the max amount from any valid sequence of robs to this house.
         * 
         * We also subdivide this and effectively do this twice, house 1 to house n-1
         * and house 2 to house n. 
         * Then we compare the max between the two.
         *
         * Algo:
         *   init ArrayList maxToHouseNmin1 // starts at n = 1 ends at n-1
         *   init Arraylist maxToHouseN // starts at n = 2 ends at n
         *   populate both to size n - 1, with 0's
         *   for i in range(0, n - 1):
        *      candidatesToPointNmin1 = [maxToHouseNmin1 for j in (0, i)]
         *      candidatesToPointN = [maxToHouseN[j] for j in (0, i)] 
         *      maxToHouseNMin1[i] = nums[i] + max{candidatesToPointNmin1}
         *      maxToHouseN[i] = nums[i + 1] + max{candidatesToPointN}
         *   return which is greater between maxToHouseN and maxToHouseNMin1
         */
        // C:
        int n = nums.length;
        if(n == 1) return nums[0];
        ArrayList<Integer> maxToHouseNMin1 = new ArrayList<>();
        ArrayList<Integer> maxToHouseN = new ArrayList<>();

        // populate the two arrays
        for (int i = 0; i < n - 1; i++){
            maxToHouseNMin1.add(0);
            maxToHouseN.add(0);
        }

        for (int i = 0; i < n - 1; i++){
            // index is i
            ArrayList<Integer> candidatesToNMin1 = new ArrayList<>();

            // for this index is i + 1
            ArrayList<Integer> candidatesToN = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                // only add if j not next to i
                if (j != i - 1)
                    candidatesToNMin1.add(maxToHouseNMin1.get(j));

                // only add if j not next to i + 1
                if (j != i)
                    candidatesToN.add(maxToHouseN.get(j));
            }

            int maxInNMin1 = 0;
            int maxInN = 0;
            for (int j = 0; j < candidatesToNMin1.size(); j++) {
                maxInNMin1 = Math.max(maxInNMin1, candidatesToNMin1.get(j));
                maxInN = Math.max(maxInN, candidatesToN.get(j));
            }

            maxToHouseNMin1.set(i, nums[i] + maxInNMin1);
            maxToHouseN.set(i, nums[i+1] + maxInN);
        }


        int maxInNMin1 = 0;
        int maxInN = 0;
        for (int j = 0; j < maxToHouseNMin1.size(); j++) {
            maxInNMin1 = Math.max(maxInNMin1, maxToHouseNMin1.get(j));
            maxInN = Math.max(maxInN, maxToHouseN.get(j));
        }

        return Math.max(maxInNMin1, maxInN);
    }
}