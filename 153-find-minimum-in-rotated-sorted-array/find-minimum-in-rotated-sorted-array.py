class Solution:
    """
    P:
    - Given: 
        - nums -> a list storing int-types
            - it was sorted in ascending order
            - however, it was ROTATED 
                - meaning we shifted elements to the right, and those
                at the end were re-appended to the beginning
    - Want:
        - want to find the minimum int 
    - Constraints:
        - the algorithm must run in O(log n) time
            - meaning, we need to cut the search space in half after an observation
                - likely a Binary Search of some variety
        - 1 <= n <= 5000
        - -5000 <= nums[i] <= 5000
        - nums are unique
        - nums is sorted and rotated anywhere between 1 and n times
            - n times of course, resulting in the original array but we do not know how many rotations
    E:
        - the examples are pretty clear
            - Ex1. [3, 4, 5, 1, 2]
                - its original array was [1, 2, 3, 4 , 5]
                - rotating 3 times, results in any index being shifted to the right 3 times
                    - in the event an index was moved to a location greater than the length, it was
                    then first shifted to the front of the array, and continued to shift from there
    D:
        - none at the moment comes to mind
    A:
        -BRUTE FORCE: the obvious brute force algorithm which fails the time complexity check, would be to simply
        iterate and find the minimum
        - however, can we do better? What obvservations can we make, to reduce the search space?
            - since we want time complexity to be O(log n), we know that we'll try to aim for binary search style
            because we want to cut the search space in half
            - the problem however, is that the left and right index points to values that are not necessarily
            in ascending order, due to the rotation
                - what if we compare the values still? 
                    - if nums at left < nums at right:
                        - this means the window we're in, is in sorted order
                            - if we start at the left and right most indices, then left must be the min
                    - if nums at left > nums at right:
                        - we know our window is not in sorted order and of course this is because it is rotated
                - how do we narrow our search space?
                    - can we also observe the midpoint? 3 cases I think of (so far)
                        - if nums at left < nums at mid < nums at right:
                            - clearly in sorted order, so minimum should be the left
                            number itself
                        - if nums at left > nums at mid, nums at right > nums at mid
                            - not in sorted order, but mid is small
                                - likely the minimum must be either midpoint itself
                                or to the left of mid
                        - if nums at left < nums at mid, nums at right < nums at mid
                            - it must be that the minimum is in the right of mid, as 
                            at some point between mid and nums at right, the value drops to the minimum
                - we have an idea how to cut the search space, but now how do we know with certainty, that we 
                found the minimum
        - Now to put together an algorithm:
            - init left and right = 0, len(nums) - 1
            - while left < right:
                - mid = (left + right)//2
                - if nums[left] < nums[mid] < nums[right]
                    - min is the left number, so return nums[left]
                - elif nums[left] > nums[mid] and nums[right] > nums[mid] 
                    - min is somewhere in between left and mid including possibly mid itself
                    so update right to mid
                - elif nums[left] < nums[mid] and nums[right] < nums[mid]
                    - min is somwhere in between mid and right, so update left pointer
                    to mid + 1
    C:
    """
    def findMin(self, nums: list[int]) -> int:
        left = 0
        right = len(nums)-1
        min_val = 5000
        while left <= right:
            mid = (right+left)//2
            if nums[left] < nums[right]:
                 # strict increasing, so update min val if less than current observed
                 min_val = min(min_val, nums[left])
                 break

            min_val = min(min_val, nums[mid])
            if nums[mid] >= nums[left]:
                # implies still strictly increasing in this window, and because we don't know if left is min,
                # search the right side
                left = mid+1
            else:
                # nums[mid] < nums[left], so this window is not strictly increasing
                # therefore minimum somewhere in between 
                # in the left portion of array 
                right = mid - 1

        return min_val