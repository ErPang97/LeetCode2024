class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // P:
        /**
         * - we want to return the list of all possible subsets of 
         *   nums
         * - recall, that given a set S:
         *    - the empty set, is a member of the subsets of S
         *    - every member of the set S, is a subset of S as well
         *    - any unique combination of the members of S, of lengths 1 to the
         *      length of S is also a subset of S
         *    - cannot contain duplicates
         */
        // E: The first example is a good clear one, [1,2,3]
        /**
         *  - once again, the empty set is a subset
         *  - each individual element, [1], [2], [3] is a subset
         *  - any combo of the two, namely [1, 2], [1, 3] and [2, 3] are all subsets
         *  - finally the set itself, [1, 2, 3] is a subset
         */
        // D: since we want to return a list, we'll only use a list
        // A:
        /**
         *  - Brainstorming: We'll definitely have to loop through the array for sure
         *  - idea 1:
         *      -(iterate through the list and recursively build up the array)
         *      - for i in nums:
         *          - add [i] to the list
         *          -  recursion: copy the list, and iterate through the remaining elements of the list
         *          -   add to the copy of the list, i 
         *          -   copy the list 
         *          -   call the recursion on this copy
         */
        // C:
        ArrayList<List<Integer>> subsets = new ArrayList<>();
        ArrayList<Integer> emptySet = new ArrayList<>(); // generating the empty set as a subset
        generateSubsets(subsets, emptySet, 0, nums);
        return subsets;
    }
    public void generateSubsets(List<List<Integer>> subsets, ArrayList<Integer> subset, int index, int[] nums){
        subsets.add(subset);
        for(int i = index; i < nums.length; i++) {
            ArrayList<Integer> copy = copy(subset);
            copy.add(nums[i]);
            generateSubsets(subsets, copy, i+1, nums);
        }
    }

    public ArrayList<Integer> copy(List<Integer> array) {
        ArrayList<Integer> copy = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            copy.add(array.get(i));
        }
        return copy;
    }

}