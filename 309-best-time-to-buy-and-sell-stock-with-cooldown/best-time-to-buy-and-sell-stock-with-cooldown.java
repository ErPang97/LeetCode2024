class Solution {
    /**
    P:
    - given an int array prices
        - prices[i] = price of stock on i-th day
    - find max profit you can achieve (an int ofc)
    - can complete as many transactions as we wish
        - buy and sell stock multiple times
        - however, after selling, you cannot buy on the next day
        (cooldown!)
        - you must sell the stock before buying again
    E:
    -
    D:
    -
    A:
    -
    C:
     */

    /**
    - this map is used to store the ith day and the decisions that can be made
    - saving states essentially, based on previous decisions
    - map the max pofit that can be made
     */
    private Map<String, Integer> dp = new HashMap<>();
    
    /**
    - we search the decision tree to find the maxprofit
     */
    public int maxProfit(int[] prices) {
        return dfs(0, true, prices);
    }
    
    /**
    - DFS is used to do a search through the decision tree
     */
    private int dfs(int i, boolean buying, int[] prices) {
        if (i >= prices.length) {
            return 0;
        }
        
        
        String key = i + "-" + buying;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int cooldown = dfs(i + 1, buying, prices);
        if (buying) {
            int buy = dfs(i + 1, false, prices) - prices[i];
            dp.put(key, Math.max(buy, cooldown));
        } else {
            int sell = dfs(i + 2, true, prices) + prices[i];
            dp.put(key, Math.max(sell, cooldown));
        }

        return dp.get(key);
    }
}