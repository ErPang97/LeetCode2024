class Solution {
    /**
    P:
    - given two arrays:
        gas
            - gas[i] -> the amount of gas at ith station
        cost
            - cost[i] -> cost of gas to travel from ith to (i+1)th station
    - we start with 0 gas
    - return: 
        - the starting station index, if it is possible to travel around once clockwise
            - essentially we pick a starting index, and if it works from there, we're good
            - we need to be able to get back to that starting index for this to work
            - only one unique solution, if exists
        - else return -1
    - some constraints!
        - we know n is the gas.length and cost.length
        - n must be at least length 1, but no more than 10^5
        - gas[i] and cost[i] <= 10^4, and >= 0, so its possible
        for some squares to be 0 cost, or give 0 gas!
    E:
    - seem okay to follow!
    D:
    - none at the moment
    A:
    - first, let's see try to figure out the brute force way:
        - for currentIndex in range(0, n):
            - set initial gasAmount = gas[currentIndexVal] // fill with current indices gas val amount
            - while loopIndex not equals currentIndex:
                loopIndex += 1
                if loopIndex == n:
                    loopIndex = 0 (means we reached first index)
                costToCurrentStation = cost[loopIndex]
                gasAmount -= costToCurrentStation
                if (gasAmount < 0) // we know we can't reach this station from the last station
                    break
                if (loopIndex == currentIndex)
                    return loopIndex // we know we reached the end
                gasAmount += gas[loopIndex] // assuming fine, we fill up with the gas amount at this station
        - return -1
    - this seems to be O(n^2) and not the most efficeint, as we may end up looking at every index 
    before we find the solution
    - can we improve this?
        - yes! a hint:
            - if we start at some index a, and we end up stuck on another index b, we know we can't
            reach past b from starting from any index in between
        - maybe use two pointers?
        - we use a left pointer and right pointer to explore, but update left pointer to stuck index + 1
        and right pointer to left + 1
        - if left > lastIndex, stop loop

    - okay after looking at solution, we can use the sum of gas[i] and -cost[i]
    to judge if a position can be reached or not. This works, likely due to the cummulative properties
    of addition, so it doesn't matter if we consider the subtraction of the cost, at the very beginning 
    or end
    C:
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // impossible if total gas possible is less than the total cost of the trip
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }

        int total = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            // we calculate the sum of the cost to get to this index, 
            // subtracted from the gas gained at this point
            // if when doing so, total is < 0, we cannot proceed to this point,
            // following the current path
            // so reset total and start 
            total += (gas[i] - cost[i]);
            if (total < 0) {
                total = 0;
                startIndex = i + 1;
            }
        }

        return startIndex;
    }
}