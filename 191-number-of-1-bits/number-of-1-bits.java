class Solution {
    public int hammingWeight(int n) {
        // P: count number of 1's in the bit-rep of n
        // E: from the problem description
        // D: none
        // A:
        // - do integer division, and count how many divisions
        // it takes for value to equal 1 r 0
        // the case where n is 1, however, return 1
        // C:

        if(n == 1){
            return 1;
        }

        int count = 0;
        while (n != 0) {
            if((n & 1) == 1){
                count++;
            }
            n = n >> 1;
        }

        return count;
    }
}