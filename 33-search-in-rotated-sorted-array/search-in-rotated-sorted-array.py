class Solution:
    """
    P:
    - Given:
        - a list[int] called nums
            - sorted in ascending order
            - however, it has been rotated k times (to the left)
            - this means, that we shifted the values in the array to 
            the left any at the beginning of the array, if it was pushed out of bounds
            goes to the end of the array pushed left from there, however many times
            remaining
        - an int target 
            - the number we're looking for
    - Want:
        - to return the index of 'target' or -1 if it is not found
    - Constraints:  
        - algo must be O(log n) in time complexity
        - 1 <= nums.length <= 5000, so never a non-empty array
        - 10^4 <= nums[i] <= 10^4
        - nums vals are unique
        - -10^4 <= target <= 10^4
    E:
        - the examples are pretty clear
    D:
        - none in particular comes to mind at the moment
    A:
        - an obvious Brute force approach would be to simply iterate
        through the loop and find the target, which would obviously be 
        O(n) - linear time
        - however, we want to achieve a time-complexity of O(log n)
        which requires us to cut the search space in half after every iteration
        - this of course, leans heavily into a variant of binary search as the approach
        - a standard binary search would not work necessarily because of the rotation of the array
        - we need to instead, figure out what observation can we make about the mid point
        and the left and right most indices
            - at any point in the iteration, we'll modify a left and right pointer as usual
            - if the nums[left] <= nums[right] -> this is a strictly increasing window
            - if the nums[right] < nums[left] -> this is not an increasing window
            - now, what about in relation to target?
                - let's use midpoint as our comparison to the target
                - if nums[mid] == target return the index mid
                - if not however, we should compare target to mid
                - if target > mid, and we're in an increasing window, then, the target must be in the right side, or bust
                - if target < mid, and we're in an increasing window, then the target has to be on the left side, or bust
                - if we're not in an increasing window... we need to consider the potential windows and decide whether to
                continue searching left or right side:
                    - if target < nums[right]:
                        - we should search right
                    - else 
                        - search left
    C:
    """
    def search(self, nums: list[int], target: int) -> int:
        left = 0
        right = len(nums)-1
        mid = (left + right)//2
        
        while left <= right:
            mid = (left + right)//2 # compute midpoint
            mid = (left + right)//2 # compute midpoint
            if nums[mid] == target:
                return mid
            if nums[left] == target:
                return left
            if nums[right] == target:
                return right
            if target == nums[mid]:
                return mid

            if nums[left] < nums[mid] :
                if target < nums[mid] and target >= nums[left]: 
                    # search the left sorted half if target in between this
                    # range 
                    right = mid - 1
                else: # target > nums[mid] or target < nums[left]
                    # search the right half
                    left = mid + 1     
            else:
                if target <= nums[right] and target > nums[mid]:
                    # search the right sorted half if in this range
                    left = mid + 1
                else: # target > nums[right] or target <= nums[mid]
                    # search the left half
                    right = mid - 1

        return -1 