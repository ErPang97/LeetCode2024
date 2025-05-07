class Solution {
    /**
    P:
        - given: an integer array nums
        - return: a boolean which is true if we can reach the last index, and false otherwise
    E:
        - we'll take a look at the examples given:
        - for example 1:
            - it is true. Ie: we can go from index 0 (which has value 2) and take 1 step to index 1 (which
            has a value of 3) and take 3 steps to the last index
        - for example 2:
            - we cannot as the last index being 4, there's no path we can take, that leads, as no matter
            what, we'll up on index 3, which has a value of 0
    D:

    A:
        - some observations! We cannot reach the last index, 
        if we end up on a zero, so that's a consideration!
        - the brute force method would be to check every possible pathway.
        For example, we can start at index 0, and explore each point that it can visit
        if it can reach the end from there, return true, else 
        continue and start at the second position, again seeing each point it can visit
        - we'll want to keep in mind, whether or not each point is reachable from the first
        point and to do that, we can start with index 0 and store the points it can reach
        - then, we simply loop and check whether or not a given point is reachable from the first index 0
        - to do that, we can keep track, starting at 0, all the points reachable from 0,
        then as we continue in the loop, see if the index is in the reachable set. If not, ignore.
        If it is, add the points that are reachable from that index, to the reachable set.
        - eventually, if when adding points, we find the last index, return true
        else return false after the loop

        UPDATE: nvm, we can keep it simple by just seeing the furthest point reachable as we
        loop through the indices, keeping in mind that we know the end is unreachable
        if the current index loop is greater than the furthest
    C:
     */
    public boolean canJump(int[] nums) {

        int furthest = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // we encountered a point we can't reach
            if (i > furthest) {
                return false;
            }
            int furthestFromHere = i + nums[i];
            if (furthestFromHere >= nums.length-1){
                return true;
            }
            // update furthest if current furthest is greater
            if (furthestFromHere > furthest) {
                furthest = furthestFromHere;
            }
        }

        if (furthest >= nums.length-1){
            return true;
        }
        else {
            return false;
        }
    }
}