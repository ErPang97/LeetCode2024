class Solution:
    """
    P:
    - given:
        - array of int -> prices
            - prices[i] -> price of a stock on the ith day
    - want:
        - to maximize our profit choosing a single day to buy one stock
        and a different day to sell that stock
        - return an int -> max_profit
    E:
    - example 1 makes sense, you can buy on day 2 (price = 1) and sell on day 5 (where price = 6)
        - so total profit is 6-1 = 5
    - example 2 also makes sense as given any day that you purchase a stock, the remaining days
    the stock price goes down, so there is no profit to be made (can't sell a stock at a lower price
    and get a return)
    D:
    - no DS are necessary here
    A:
    - BRUTE FORCE APPROACH
        - the easiest approach here is to use a double for loop
        - init max_profit = 0
        - for i in range(len(prices)):
            - for j in range(i+1, len(prices)):
                profit = prices[i] - prices[j]
                if profit > max_profit update max_profit
        - we pretty much compare prices for each index i, to all other indices
        j greater than i
        - however, this is clearly O(n^^2) worse-case scenario
        - can we improve upon this?
    - IDEA 1: 
        - we need to limit the search to values that make sense and we do not want to 
        have to compare all possible pairs 
        - what if we use two pointers and use observations to make a judgement on when to
        update the pointer
            - in order to increase profit, we must either decrease the purchase price
            or increase the selling price
                - however, this is not a sorted array
            - what happens if we increment left pointer when profit is negative
            - decrement right pointer when profit is positive 
                - explanation:
                    - when profit is negative we want to decrease our purchasing price first
                    to see if we can find effectively a minimum price
                    - when profit is positive, we want to check and see if there is another
                    number higher than the value we just potentially "sold" at
        - however, there is an important edge case to consider here, 
            - suppose our profit BEGINS positive
            - we end up updating our right pointer without considering the possiblity
            that the right most value is the MAX yet somewhere in the middle there is
            another minimum
            - How do we adjust for this?
    - IDEA 2:
        - Sliding Window:
        - rather than starting at the left most and right most initially, we have to consider that
        the min and max could be in the middle somewhere and can be missed if using the update scheme above
        - start left = 0 and right = left + 1 instead
        - the observation to make:
            - we again compare profit to max profit
            - if we find a positive profit just keep updating the right pointer
            - HOWEVER, if we find a negative profit, we already know that the left-most purchase
            day we already tried the maximum given the range up to where the right pointer is
            - THERE could be a day later where we find a higher profit, but the fact we found a
            negative profit indicates there's a point where we can buy the stock for EVEN CHEAPER
            - that later date where we could have made a profit at the i-th index, is GUARANTEED to 
            be less than if we purchase it at the later date
    C:
    """
    def maxProfit(self, prices: list[int]) -> int:
        max_profit = 0
        left = 0
        right = left+1
        if len(prices) == 1:
            return max_profit
        while left < len(prices):
            profit = prices[right] - prices[left]
            if profit > max_profit:
                max_profit = profit
            
            if profit < 0: # we already exhausted the best selling point, given the range [left,right]
                left = right

            if right < len(prices)-1: # keep updating right
                right += 1
            else: # we exhausted all possible right values, so update left
                left += 1
        return max_profit