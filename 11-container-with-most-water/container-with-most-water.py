class Solution:
    """
    P:
    - Given:
        - a List of int -> height
            - n vertical lines where the two endpoints (basically
            the bottom and the top), are (i, 0) and (i, height[i])
    - Want: 
        - find two lines that together with the x-axis form a container
        such that the container contains the most water
        - RECALL: the max area of water a container can hold is
            AREA = length * height
            - we can calculate length by taking the ABS(right - left)
            - but height, is the smaller of the two lines
    - Return the MAX amount of water a container can store given
    the conditions set  
    - Constraints:
        - n == height.length
        - 2 <= n <= 10^5
        - 0 <= height[i] <= 10^4
    E:
    - the examples are pretty clear
    D:
    - none extra needed at the moment
    A:
    - Simplest idea by far is Brute Force:
        - use two pointers
        - max_area = 0
        - iterate through left up to some range(len(height)-1) (left pointer can't be the end of course)
            - iterate from right = left + 1 up to the len(height)
                - area = (right - left) * min(height[left], height[right])
                - max_area = max(max_area, area)
        - however, this is clearly O(n^^2)
    - How can we improve upon this?
        - What if instead, we update our pointers based on observations
            - What is the observation to make?
            - HINT USED: Consider updating which pointer points to the shorter 
            of the two lines. Naturally, we often should get larger area by looking
            for the tallest lines
            - in the event of ties, update right first, as increasing width 
            also likely increases volume
            - keep iterating until right no longer valid (out of bounds)
            - keep iterating left as well, until its at the end as well 
            - AFTER TAKING A LOOK AT PREVIOUS SOLN...
                - kind of greedily assume first max area is the widest, so place
                both pointers to the left most and right most respectively
                - calc area, and then compare ofc to current_max, replacing if greater
                - then, decrement right pointer if its shorter than left, and
                increment left if its shorter than right
                - stop the loop when right <= left
        - left = 0
        - right = 1
        - max_area = (right - left) * min(height[left], height[right])
        - while (right < len(height)):
            - current_area = (right - left) * min(height[left], height[right])
            - if current_area > max_area:
                - max_area = max(current_area, max_area)    
    C:
    """
    def maxArea(self, height: List[int]) -> int:
        left = 0
        right = len(height) - 1
        max_area = (right - left) * min(height[left], height[right])
        while left < right:
            current_area = (right - left) * min(height[left], height[right])
            if current_area > max_area:
                max_area = max(current_area, max_area)
            # update smaller pointer of the two, breaking tie by decrementing right
            if height[right] <= height[left]:
                right -= 1
            elif height[left] < height[right]:
                left += 1
        return max_area