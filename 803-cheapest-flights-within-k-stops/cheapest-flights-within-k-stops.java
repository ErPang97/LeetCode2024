class Solution {
    /**
    P:
    - given: array called flights: 
        - flights[i] - [from_i, to_i, price_i]
        - essentially a weighted graph where the edge weight is the cost of the flight
    - given: int src - source
             int dst - destination
             int k - at most k stops
    - return: cheapest price from src to dst, and k or -1, if no such route
    E:
    - 
    D:
    - just arrays
    A:
    - we'll use a Bellman-Ford implementation
    C:
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        // set the price to the max possible int value
        for(int i = 0; i < n; i++) {
            prices[i] = Integer.MAX_VALUE;
        }
        prices[src] = 0;

        for (int i = 0; i < k + 1; i++) {
            // copy the current snapshot of prices
            int[] tmpPrices = Arrays.copyOf(prices, prices.length);

            for(int[] sdp: flights) {
                int currSrc = sdp[0];
                int currDst = sdp[1];
                int currPrice = sdp[2];
                // when the current source price is maxInt
                // we ignore, as we haven't found a path currSrc to this yet
                if (prices[currSrc] == Integer.MAX_VALUE) {
                    continue;
                }
                // if price at currSrc isn't max_val, then
                // we check if original price[currSrc] + currPrice 
                // is less than the tmpPrices[currDst]
                // we adjust tmpPrices[currDst]
                if (prices[currSrc] + currPrice < tmpPrices[currDst]) {
                    tmpPrices[currDst] = prices[currSrc] + currPrice;
                }
            }
            // update prices to tmpPrices
            prices = tmpPrices;

        }
        
        if (prices[dst] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return prices[dst];
        }
    }
}