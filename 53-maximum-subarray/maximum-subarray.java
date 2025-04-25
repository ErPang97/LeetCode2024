class Solution {
    public int maxSubArray(int[] nums) {
        /**
        P:
            - simply put, we want to find a subarray of the given array, whose
            sum is the max. however, we want to return the SUM of the numbers
        E:
            - examples are easy to follow but we'll use example 3 as the target:
            given nums = [5,4,-1,7,8] we know we can get subarrays of size 1 to size 5:
            size 1: [5],[4],[-1],[7],[8] -> max is 8
            size 2: [5,4], [4,-1], [-1,7], [7,8] -> max is 15
            size 3: [5,4,-1], [4,-1,7], [-1,7,8] -> max is 14
            size 4: [5,4,-1,7], [4,-1,7,8] ->18
            size 5: [5,4,-1,7,8] -> 23
            - so max is 23
        D:
            - we may need to use dictionary for possible memoization
        A:
            - we can approach this problem with DP, and a Greedy approach
            first, what is the appropriate subproblem?
            - given all subarrays that end at index k, we know that 
            the max sum up to that point, that we can obtain from this will be 
            between the max sum at k-1 + the value at k, versus k itself

            alg:
            init current_max = nums[0]
            init int[] sub_max = int[len(nums)] 

            for i=1 to i=len(nums)-1
                current_max = Math.max(sub_max[i-1]+nums[i], nums[i])
            return max in sub_max
        C:
         */
        
        int currentMax = nums[0];
        int[] subMax = new int[nums.length];
        subMax[0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            subMax[i] = Math.max(subMax[i-1] + nums[i], nums[i]);
            currentMax = Math.max(currentMax, subMax[i]);
        }
        return currentMax;
    }
}