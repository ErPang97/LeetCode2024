class Solution {
    public int[] countBits(int n) {
        // P: generate an array of length n+1
        // where array[i] == number of 1's in binary of i
        // E: n = 2 -> [0, 1, 1]
        // - i = 0 -> 0 in binary
        // - i = 1 -> 1 in binary
        // - i = 2 -> 10 in binary
        // D: just an array should be okay
        // A: we use dynamic programming
        // - there's a pattern:
        // base case:
        // - result[0] = 0
        // - result[1] = 1
        // for n >= 2:
        // if n is even: result[x] = result[x/2]
        // if n is odd: result[x] = result[x/2]+1
        // C:    

        int[] result = new int[n+1];

        if(result.length == 1){
            result[0] = 0;
            return result;
        }

        // base cases:
        result[0] = 0;
        result[1] = 1;

        // now for i >= 2
        for(int i = 2; i < result.length; i++){
            
            // when i is even:
            if(i % 2 == 0){
                result[i] = result[i/2];
            }
            // odd case:
            else {
                result[i] = result[i/2]+1;
            }

        }

        return result;

    }

}