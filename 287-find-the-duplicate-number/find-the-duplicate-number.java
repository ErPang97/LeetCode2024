class Solution {
    public int findDuplicate(int[] nums) {
        // P:
        // E:
        // D: no extra DS
        // A: 
        /* BF method is to use two pointers, a left pointer that goes from
             i = 0 to i = nums.length - 2, and a right pointer
             that goes from i + 1, to nums.length - 1
             simply return the nums[i] when nums[i] == nums[j]
        // non-brute force (sort) method:
        - create a new array of the same size, but is sorted
        - then simply iterate through the array, checking for when 
        nums[i] == nums[i+1]
        */
        // C:

        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]]; // moves twice as the slow pointer
        } while (slow != fast);

        int slowTwo = 0;
        while(true) {
            slow = nums[slow]; // both move at the same speed
            slowTwo = nums[slowTwo]; // and both equidistant to start of cycle
            if(slow == slowTwo) return slow;
        }

        // follow up:
        /**
        - to prove that a dupicate must exist, we first recall that the size of nums, is n + 1. We try the contradiction, and attempt to put n + 1 unique integers, given the restriction that all the ints are in the range [1,n]. This must mean that there are at least n + 1 unique integers in the range from [1,n]. However, using the formula to calculate the number of integers, in a closed range [b,a], we know that the number of integers in that range is b-a + 1. Using that formula on the range [1,n] we get n-1 + 1, or n unique integers in the range. Therefore, since n < n + 1, we know that one of the numbers must be duplicate.
        - 
         */
    }
}