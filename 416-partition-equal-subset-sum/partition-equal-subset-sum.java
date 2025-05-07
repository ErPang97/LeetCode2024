class Solution {
    /**
    P:
    - given: an array of nums
    - return: if we can split the array into two subsets, such that
    the sum is equal/ or false otherwise
    - recall a subset doesn't have to be a continuous sequence!
    E:
    - generally okay to understand! for the first one, the number
    11 by itself can be in one subset, while the remaining numbers can
    be in a different subset 1+5+5 = 11
    D: 
    - possibly a map for memoization purposes,
    A: 
    - as this is a DP problem, lets consider possible subproblems
    - important cases: 
        - when length is 1, we already know its false as you cannot split this
        into two subsets where the sum is equal; ie [1] and the [] clearly don't have the same sum
        - when length is 2, the only possible option is
        does nums[0] == nums[1], and if so return true, otherwise return false
        as you can only have two subsets of size 1 or one of size 2, and the other of size 0, which 
        again doesn't give us anything
        - when sum of nums % 2 != 0, we know we can return false
            - this is because in order for it to be possible to split into two even
            subsets, the this implies that the two subset sums are equal, and 2 * any number
            is even
    - we should generally be able to approach this by recursion, searching for subarrays that
    result in sum of nums/2
    - now let's consider a subarray nums[0:k], which has length k
    C:
     */
    public boolean canPartition(int[] nums) {
        
        int sumOfNums = 0;
        for (int i : nums) {
            sumOfNums += i;
        }
        // check that the sum is even or not
        if (sumOfNums%2!=0) {
            return false;
        }

        // is used to store all the potential sums we can get
        Set<Integer> dp = new HashSet<>();
        dp.add(0);
        int target = sumOfNums/2;

        // loop and at each index
        // we make the decision of whether or not to include
        // the current nums[i] in sum or just ignore
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> nextDp = new HashSet<>();
            for (int t : dp) {
                if(t + nums[i] == target) return true;
                nextDp.add(t + nums[i]); // add sum of previous and current
                nextDp.add(t); // add the previous as well (chose not to add current num as a value)
            }
            dp = nextDp; // update dp to that nextDp
        }

        if (dp.contains(target)) return true;

        return false;
    }

}