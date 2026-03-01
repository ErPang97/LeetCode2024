class Solution {
    /**
    P:
        - given int[] nums
            - has length: int n
        - return:
            - an int[] ans 
                - length: 2n
                - ans[i] = nums[i]
                - ans[i + n] == nums[i]
                for 0 <= i < n (0-indexed)
    E:
        - examples make sense
    D: 
        - no extra necessary except a new array
        of length 2n
    A:
        - create a new int[] ans of length 2n
        - for i in range(n)
            ans[i] = nums[i]
            ans[i+n] = nums[i]
     */
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2*n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i+n] = nums[i];
        }
        return ans;
    }
}