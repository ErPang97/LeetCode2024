class Solution {
    /**
    P:
        - we're given a sequence of nums, and we simply want to return the length of the
        longest increasing subsequence
        - recall a subsequence is one where, we simply can delete elements without changing the
        original order 
        - we know also that nums length is no larger than 2500
    E:
        - the examples are pretty clear to follow
    D:
        - perhaps a map? to keep track of previous largest in sequence, given a certain index k
    A:
        - the BF way would be to of course, check each start/end index k, and
        just proceed and see the longest increasing subsequence from that point (or to)
        - this would of course be potentially O(n^2)
        - how can we improve upon this? How can we formulate as a DP problem?
        
        - lets consider a subproblem for an index k
        - if we consider a subsequence ending at index k:
            - we know that given the subsequences that ended at any of the indices i =
            1 to k-1, if we know the length of subsequence, and the value at that point, the max
            at this point will be the max any of those +1, if nums[k] is greater than nums[i]
        
        - we consider the following algo:
            init map from int to sublength
            map.put(1) = 1
            for i in range (1, n):
                - currentMax = map.get(0)
                - for key in map int to sublength:
                    if nums[i] > nums[key]
                        currentMax = max(currentMax, map.get(key))
                map.put(i) = currentMax
    C:
     */
    public int lengthOfLIS(int[] nums) {
        int longest = 1;
        // keeps track of the longest subsequence that ends at that integer
        Map<Integer, Integer> indexToLongest = new HashMap<>();
        indexToLongest.put(0, longest);
        for (int i = 1; i < nums.length; i++) {
            int currentMax = 0;
            for (int j = 0; j < i; j++) {
                int prevMax = indexToLongest.get(j);
                if (nums[i] > nums[j]) {
                    currentMax = Math.max(currentMax, prevMax);
                }
            }
            indexToLongest.put(i, currentMax+1);
            longest = Math.max(indexToLongest.get(i), longest);
        }
        return longest;
    }
}