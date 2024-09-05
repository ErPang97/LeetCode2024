class Solution {
    public int maxArea(int[] height) {
        // P: find the max volume possible
        /**
            - we can choose any two lines from n different lines
            - in the height array, and they will serve as the
            - the edges of the container
            - we can calculate the volume
            - find the max "volume" of liquid
         */
        // E: 
        // D: none?
        // A: 
        /**
            - we will use two pointers
            - left and right
            - remember, area = base * height
            - base = right - left
            - height = min(height[right], height[left])
            - while left < right:
                - calculate area and compare to currentMax
                - if its greater, then replace max
                - then, increment/decrement, left/right
            - return maxArea
         */
        // C:

        int maxArea = 0;
        int left = 0;
        int right = height.length-1;

        while(left < right){
            int base = right-left;
            int minHeight = Math.min(height[left], height[right]);
            int area = base * minHeight;
            if(area > maxArea){
                maxArea = area;
            }
            if(minHeight == height[left]){
                left++;
            } else if(minHeight == height[right]){
                right--;
            }
        }
        return maxArea;
    }
}