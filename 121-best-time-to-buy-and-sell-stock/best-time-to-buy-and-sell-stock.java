class Solution {
    public int maxProfit(int[] prices) {
        // P: important note, can't buy after selling! 
        // E: 
        // D: just the input array
        // A: profit on any given day, given purchase price
        // is profit[i] - purchase price
        // we compare that to a maxProfit value we have, and if its greater
        // we change maxProfit
        // C:
        int maxProfit = 0;

        int purchasePrice = prices[0]; 
        // set to the first day, the lowest
        // purchase price
        for(int i = 1; i < prices.length; i++) {

            int currentProfit = prices[i] - purchasePrice;
            if(currentProfit > maxProfit) {
                maxProfit = currentProfit;
            } else if(currentProfit < 0) { // new minimum found
                purchasePrice = prices[i];
            }
            
        }

        return maxProfit;
    }
}