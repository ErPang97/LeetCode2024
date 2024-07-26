class Solution {
    public int singleNumber(int[] nums) {
        // P:
        // E:
        // D: no extra I believe
        // A: use bit-wise operations
        // X-OR operation; this will cancel
        // the numbers that are the same
        // and leave the single one that isn't
        // C:

        int lastNumber = 0;
        for(int current : nums) {
            lastNumber ^= current;
        }
        return lastNumber;

    }
}