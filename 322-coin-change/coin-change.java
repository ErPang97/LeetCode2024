class Solution {
    /**
    P:
        - we have a list of coins representing the possible
        denominations usable, and a final amount of money
            - we have infinite number of each coin!
        - we want to find the least number of coins needed to make the combo
        or, if there isn't any possible, return -1
        
    E:

    D:
        - 
    A:
        - this problem is a DP problem if we consider the following subproblem:
            - given some number k <= amount, we want to find the minimum amount of coins needed
            to give that value
            - ofc, if there exists a coin value equal to k, then the answer is 1
            - however, if not, then the minimum number of coins needed will be the minimum number needed 
            from any of the min number of coins of i = k - j, where j represents any of the coin values
            + 1
                - if non exist, then ofc, minimum number is 0

        - dp = populate from 1 to amount, the min number of coins
        being the amount + 1
        - dp[0] = 0// base case, no zero coin amount
        - for i = 1 to i = amount:
            for j in coins:
                if i - j >= 0 // a valid index
        -           dp[i] = min (dp[i], 1 + dp[i-c])
        - return dp[amount]  if dp[amount] != amount+1 else -1
            
    C:
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }

        for (int i = 1; i < amount+1; i++) {
            for (int coin: coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                } 
            }
        }

        if (dp[amount] != amount+1) {
            return dp[amount];
        } else {
            return -1;
        }
    }

}