class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            // AND to get the carry-over
            // Left Shift to move it to the left by 1 bit
            // as the carry-over doesn't add to the same bit
            int carry = (a & b) << 1;

            // XOR to get the result of adding the two bits together
            // ie: 1+1 = 0, 0+1 = 1, 1+0 = 0
            a ^= b;

            // set b equal to the carry-over
            b = carry;
        }
        return a;
    }
}