class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // P: 
        /** 
         * A similar problem to combinations, except order
         * does matter, so for instance, [1,2,3] is not the same as [1,3,2]
         * Unlike in the combos problem. We need all possible orderings of these 
         * numbers. 
         * And we cannot re-use numbers
         * Also, noting that the size of the permutations is always equal to nums.length
         */
        // E:   
        /**
         * [1,2,3]:
         * for all that start with 1:
         * [1,2,3], [1,3,2]
         * for all that start with 2:
         * [2,1,3], [2,3,1]
         * for all that start with 3:
         * [3,1,2], [3,2,1]
         */
        // D:
        /**
         * Of course we're returning a List, so ArrayList is my first choice
         * Wondering if using a Set, to keep track of used numbers is a bad idea 
         * though that will add to SpaceComplexity for sure
         */
        // A:
        /**
         * Brainstorm:
         * Like in the combinations problem, we will likely use recursion to 
         * build up our permutations
         * void buildPermutations(nums, listOfPermutations, candidate)
         *       if candidate.size() == nums.length:
         *            listOfPermutations.add(candidate)
         *            return
         *       for i in nums:
         *            if !candidate.contains(i):
         *                 newCandidate = candidate.copy()
         *                 newCandidate.add(i)
         *                 buildPermutations(..., newCandidate)     
         */
         // C:
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        buildPermutations(nums, permutations, candidate);
        return permutations;
    }

    public void buildPermutations(int[] nums, List<List<Integer>> permutations, List<Integer> candidate) {
        if(candidate.size() == nums.length) {
            permutations.add(candidate);
            return; // end the recursion
        }
        for(int i : nums) {
            if (!candidate.contains(i)) { // asterisk: might be too slow O(n)
                ArrayList<Integer> copyCandidate = copy(candidate);
                copyCandidate.add(i);
                buildPermutations(nums, permutations, copyCandidate);
            }
        }
    }

    public ArrayList<Integer> copy(List<Integer> candidate) {
        ArrayList<Integer> copy = new ArrayList<>();
        for(Integer i: candidate) {
            copy.add(i);
        }
        return copy;
    }


}