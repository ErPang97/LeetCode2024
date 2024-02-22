class Solution {
    public int[] countBits(int n) {
        // Problem (good)
        // Examples
        // Data Structures: Array of Length n + 1
        int[] ans = new int[n+1];

        // Algorithms
        // 1. for loop to iterate from 0 to n, inclusive
        // 2. count number of one's', using shift operator
        // 3. loop through bits of the number, by right shifting
        // and & with 1, to see if result is 1, if it is
        // increment a count by 1

        for(int i = 0; i <= n; i++){
            int current = i;
            int count = 0;
            while(current != 0){
                // if current & 1 equals 1, means right most bit
                if((current & 1) == 1){
                    count++;
                }
                current = current >> 1;
            }
            ans[i] = count;
        }
        return ans;
    }
}