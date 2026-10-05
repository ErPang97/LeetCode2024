class Solution:
    """
    P: 
    - Given:
        - array called nums, sorted in ascending order
        - target -> integer
    - Want:
        - search target in nums
        - if target exists, then return its index
        else return -1
    - Constraints:
        1 <= nums.length <= 10^4
    E:
    - examples are clear
    D:

    A:
        - a classic algo:
        - while left < right: 
            - mid = (right - left) // 2
            - if nums[mid] == target:
                return mid
            - elif nums[mid] > target: # every value above mid is guaranteed to be greater than target as its already sorted
                                       # so update right to mid, to ignore that half of the search space
                right = mid
            - else:
                left = mid             # similarly, target must be greater than mid, so update to ignore the left side
        - return -1 
    C:
    """
    def search(self, nums: list[int], target: int) -> int:
        left = 0
        right = len(nums)-1
        while left <= right:
            mid = (right + left)//2
            if nums[mid] == target:
                return mid
            elif nums[mid] > target:
                right = mid - 1 # ignore any value to the right of mid, as its greater than target
            else:
                left = mid + 1 # ignore any value to the left of mid as its greater than target

        return -1 # if all else fails return -1