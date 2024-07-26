class Solution {
    public int removeDuplicates(int[] nums) {
        // P:
        // E:
        // D: maybe
        // a map or set to keep track of seen values
        // A: in a for loop, we have another pointer, that
        // points to the index to be replaced
        // initially its set to the first index;
        // as we loop through the array, check
        // if value in the set
        // - if it is, do nothing
        // - if it is not, add to the set
        // - replace the value pointed at by the index-to-replace pointer
        // - and increment the index-to-replace pointer

        int k = 0;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(!seen.contains(nums[i])) {
                seen.add(nums[i]);
                nums[k] = nums[i];
                k++;
            }
        }
        return k;

    }
}