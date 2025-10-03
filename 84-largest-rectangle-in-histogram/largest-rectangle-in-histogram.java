class Solution {
    /**
    P:
    - given: an int[] heights
        - each index i, represents a bar of width 1 and height heights[i]
    - return: area of largest rectangle
    E:
    - the examples make sense
    D:
    - a stack
    A:
    -
    C:
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>(); // stack[0] = index, stack[1] = height

        // iterate through the heights array
        for(int i = 0; i < heights.length; i++) {
            int h = heights[i];

            // the start index is just i at first
            // don't know if can extend backwards yet
            int start = i;

            // while the stack isn't empty, and the height we just
            // reached is less than that of the top of the stack
            // we have to pop the current stack, and check max possible
            // area, and extend start index of current height backwards
            while (!stack.isEmpty() && stack.peek()[1] > h){
                int[] indexHeight = stack.pop();
                int index = indexHeight[0];
                int height = indexHeight[1];
                maxArea = Math.max(maxArea, height * (i - index));
                start = index;
            }
            // adding start index that we pushed all the way backwards
            stack.push(new int[]{start, h});
        }

        // when we reach end of histogram, we 
        // need to consider the remaining candidates
        // (they extend all the way to end of histogram)
        while (!stack.isEmpty()) {
            int[] indexHeight = stack.pop();
            int index = indexHeight[0];
            int height = indexHeight[1];
            // height is whats stored in stack, width extends from start to end
            maxArea = Math.max(maxArea, height* (heights.length - index));
        }
    
        return maxArea;
    }
}