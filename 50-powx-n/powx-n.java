class Solution {
    /**
    P:
    - calculate x^n, given:
        - a double x
        - an int n
    - return x^n, a double
    E:
    - examples are clear
    D:
    
    A:
    - use recursion instead of brute force multiplyin x against
      itself n times
    C:

     */
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        double res = helper(x, Math.abs((long) n));
        // if n is positive or 0, return the result, else, return 1/res 
        // (representing positive and negative powers!)
        return (n >= 0) ? res : 1 / res;
    }

    private double helper(double x, long n) {
        if (n == 0) {
            return 1;
        }
        // recursively calculate the value of x ^ (n/2), for even values
        // if not, multiply by itself
        double half = helper(x, n / 2); 
        return (n % 2 == 0) ? half * half : x * half * half;
    }
}