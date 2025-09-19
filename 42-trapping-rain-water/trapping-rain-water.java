class Solution {
    /**
    P:
        - given n number of non-negative integers   
            - they represent an elevation map
        - (maybe think of like a list of length n called height, where each index 
        in the list represents a box of width 1. The height of the box i is given by 
        the value height[i])
        - given our box arrangement, we want to determine the number of
        units of water that would be trapped if it were to rain
    E:
    - the examples are clear
    D:
    - could use O(3*n) space to explicitly keep track of troughs
    A:
    - using two pointers, we can try and find some sort of open and close section
    - to clarify, our left pointer would represent the left edge of a block that would
    trap water, and the right pointer would represent the right edge of that same block
    - starting from the left and incrementing the right, if height[left] <= height[right] at
    some point, we know that together, they form a trough that water can be trapped in
    - now how to find how much water?
        - if they are right next to each other, obviously no water can be filled from this point
        - if they are one away, then the max water would be the height of the larger side minus the
        difference between the two, minus the height of the block in between them
        - if they are two away, we can do a similar calculation to the block in the middle from before, 
        but do it twice for the blocks in between and sum those two up
        - now for some general away, we would just need to find the sum of all the blocks in between:
            - in that case, I think we can just keep updating the left pointer until we reach the right
    -
    C:
     */
    public int trap(int[] height) {
        
        // base case
        if (height.length == 0) {
            return 0;
        }

        // set left and right pointer to ends of the array
        int left = 0;
        int right = height.length - 1;
        // for storing the current max height to left, 
        // and right respectively from a pointer
        int leftMaxHeight = height[left];
        int rightMaxHeight = height[right];

        int result = 0;

        while (left < right) {
            // we update the smaller of the two maxima
            if (leftMaxHeight < rightMaxHeight) {
                left += 1;
                leftMaxHeight = Math.max(leftMaxHeight, height[left]);
                // calculate the amount of water to be added
                result += leftMaxHeight - height[left];
            }
            else {
                right -= 1;
                rightMaxHeight = Math.max(rightMaxHeight, height[right]);
                // calculate the amount of water to be added
                result += rightMaxHeight - height[right];
            } 
        }


        return result;
    }
}