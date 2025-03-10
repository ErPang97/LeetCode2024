class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // P:
        /*
         * Similar to the first subsets problem, except now
         * there's duplicates. Once again order does not matter, so
         * [2,1,1] for instance is the same as [1,1,2] and [1,2,1]
         */
        // E: for the example [1,2,2] we have the following subsets 
        /*
         * subsets of size 0: []
         * subsets of size 1: [1], [2]
         * subsets of size 2: [1, 2], [2, 2]
         * subsets of size 3: [1, 2, 2]
         */
        // D: just an ArrayList 
        // A:
        /*
         * idea: we'll want to do recursion, and also to help prevent
         * duplicates, we'll keep track of the last number used
         * so as to prevent creating multiple of the same candidate when
         * generating the candidate subsets
         * - note we'll need to sort the nums array
         *
         * List<Int> empty = new ArrayList 
         * List<List<Int>> subsets = new ArrayList 
         * call generateSubsetsRecursion(empty, subsets, 0, nums)
         *
         * generateSubsetsRecursion(List subset, List<List> subsets, int index, int[] nums)
         *     subsets.add(subset)
         *     int lastUsed = -11 
         *     for (int i = index; i < nums.length; i++)
         *         if (nums[i] == lastUsed) continue
         *         List<Int> copy = copy(subset)
         *         copy.add(nums[i])
         *         generateSubsetsRecursion(copy, subsets, index++, nums)
         */
         Arrays.sort(nums); 
         List<Integer> emptySet = new ArrayList<>();
         List<List<Integer>> subsets = new ArrayList<>();
         generateSubsetsRecursion(emptySet, subsets, 0, nums);

         return subsets;
    }

    public void generateSubsetsRecursion(List<Integer> subset, List<List<Integer>> subsets, int index, int[] nums){
        subsets.add(subset);
        int lastUsed = -11; // set to -11, as it can never be that num
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == lastUsed) continue;
            ArrayList<Integer> copy = copy(subset);
            copy.add(nums[i]);
            lastUsed = nums[i];
            generateSubsetsRecursion(copy, subsets, i+1, nums);
        }
    }

    public ArrayList<Integer> copy(List<Integer> array) {
        ArrayList<Integer> copy = new ArrayList<>();
        for(Integer i : array){
            copy.add(i);
        }
        return copy;
    } 

}