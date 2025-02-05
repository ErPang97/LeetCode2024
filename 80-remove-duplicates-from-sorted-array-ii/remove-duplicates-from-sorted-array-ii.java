class Solution {
    public int removeDuplicates(int[] nums) {
        // P:
        /**
         * - nums is always at least of length 1
         * - and nums is an integer within some range - 10^4 to 10^4
         * - non-decreasing sorted order, so duplicates will appear one
         *   after the other
         */
        // E:
        // D:   must not utilize more than O(1) extra space
        // A:
        /**
         * - Two Pointers Hint! 
         * - if the array is at least length >= 2 we can say that
         *   the first two no matter what are fine.  
         *   (technically 1 also works but trivial)
         * - we have a left pointer and a right pointer
         *     - the right pointer will iterate through the entire array
         *     - the left pointer will essentially be used to keep track where
         *     - the number needs to be swapped
         */
        // C:
        int left = 0;
        int right = 0;

        while (right < nums.length){
            int count = 1;
            // when duplicates are found, we use r to increment further to
            // to help count how many duplicates there are
            while(right + 1 < nums.length && nums[right] == nums[right+1]) {
                right+=1;
                count+=1;
            }

            // we start swapping the value at index left, 
            // by the value at index right, when its no longer a duplicate
            // we know the count is at least 1, but we also don't want to 
            // keep more than 2
            for(int i = 0; i < Math.min(2, count); i++) {
                nums[left] = nums[right];
                left +=1;
            }

            right+=1;
        }

        return left;
    }
}