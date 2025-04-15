class Solution {
    /**
    P:
        - given: an array nums, which consists of integers
        - finding: the subarray that gives the largest product of the integers 
        in subarray
    E:
        - examples are pretty clear, but we'll take a look at 1:
            subarrays of size:
                1: [2], [3], [-2], [4] -> largest is 4
                2: [2,3]=6, [3,-2]=-6, [-2,4]=-8
                3: [2,3,-2]=-12, [3,-2,4]= -24
                4: [2,3,-2,4]=-48
    D:
        - likely arrays, possibly
    A:
        - what is the appropriate subproblem...
            - idea!
                - what if we use memoization to save precomputed subarray calcs
                - essentially for some subarray that ends in k, we know that 
                the largest product subarray is either nums[k] itself, or the max of
                any of the previous subarray products in k-1 multiplied by the integer value at k
                - if we use memoization, we don't need to keep computing the values in the previous ints

            - oh no! Time-limit exceeded on a 4 cases...
                - need to cut-down on cases where it wouldn't be possible then somehow
                - if we save only the max and min of the prev (k-1), rather than all products
                - the next max at k will be the max of nums[k] * either the min or max, depending on sign, or nums[k] 
                itself

        // original idea:

        init map: index -> list of subarray products
        put in map 0 -> [nums[0]]
        overall_max = nums[0]

        for i in range(1, len(nums)):
            prev_subproblems = map.get(i-1)
            init new_subproblems = [nums[i]]
            int sub_max = nums[i]
            
            for j in prev_subproblems:
                product = j * nums[i]
                sub_max = Math.max(sub_max, product)
                list_new_subproblems.add(product)
            put in map i - > new_subproblems
            overall_max = Math.max(sub_max, overall_max)

        return overall_max

        // new idea:

        int currentMax, currentMin = 1
        int result = nums[0]

        for num in nums:
            int temp = currentMax * num
            currentMax = max among [product, currentMax*num, currentMin*num]
            currentMin = similar but min of [product, temp (prev. stored max*num), currentMin*num]
            result = Math.max(res, currentMax)

        return result
    C:
     */
    public int maxProduct(int[] nums) {
    
        int overallMax = nums[0];
        int currentMax = 1;
        int currentMin = 1;

        for (int i : nums) {
            int temp = i * currentMax;
            currentMax = Math.max (Math.max(i, currentMax*i), currentMin*i);
            currentMin = Math.min (Math.min(i, currentMin*i), temp);
            overallMax = Math.max(overallMax, currentMax); 
        }
        
        return overallMax;
    }
}