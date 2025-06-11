class Solution {
    /**
    P:
    - given an int[] array coins
        - coins[i] = one possible denomination of a coin
    - given an int amount
        - total amount of money
    - want to return the number of combinations that can make up
    that amount
    - return 0 if not possible
    E:
    - 
    D:
    -
    A:
    - we use a decision tree approach to this
    - to prevent duplicates, lets take the first example: [1,2,5]
        - lets say going down the '1' route, we are free to choose any coin
        - if we go down the '2' route, we cannot choose any 1
        - finally, for the '5' route, we can only choose 5
    - now we expand this to a grid idea where we have a top row that considers any
    amount, 0 <= m <= amount
        - the dp solution is to consider the possible ways to get some amount m, given
        the coins we are allowed to choose from 
        - each row of the 2d dp represents the coins we can choose, for instance 
        (the top row can be 1, which can choose any coin including 1, the second row 
        2, can choose 2 or 5, and the bottom row 5, can only choose 5)
        - the dp solution for some arbitrary point i, a where i in coins and a <= amount
            - dp[i][a] = dp[i + 1][a] + dp[i][a - coins[i]];
            - the dp[i+1][a] considers the possible ways given you cannot choose coin value i
            - dp[i][a - coins[i]] then considers the possible ways, after we subtract the current
            coin value (we build from the bottom up!)
    C:
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        Arrays.sort(coins);
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int a = 0; a <= amount; a++) {
                if (a >= coins[i]) { // ignore when current coin value greater than a, as its impossible!
                    dp[i][a] = dp[i + 1][a];
                    dp[i][a] += dp[i][a - coins[i]];
                }
            }
        }

        return dp[0][amount];
    }
}