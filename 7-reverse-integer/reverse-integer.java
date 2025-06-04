class Solution {
    public int reverse(int x) {
        // these are the MIN and MAX int's possible
        final int MIN = -2147483648; // -2^31
        final int MAX = 2147483647;  // 2^31 - 1

        int res = 0;
        while (x != 0) {
            // we find the digit at the current place in the number
            // and save it
            int digit = x % 10;
            // we divide the number by 10, effectively removing the digit
            x /= 10;

            // keeping track of when result is within 10 of MAX or MIN
            if (res > MAX / 10 || (res == MAX / 10 && digit > MAX % 10))
                return 0;
            if (res < MIN / 10 || (res == MIN / 10 && digit < MIN % 10))
                return 0;

            res = (res * 10) + digit;
        }

        return res;
    }
}