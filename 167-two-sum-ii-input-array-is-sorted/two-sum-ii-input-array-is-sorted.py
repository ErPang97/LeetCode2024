class Solution:
    """
    P:
    - given:
        - a list[int] -> numbers
            - sorted in increasing order (non-decreasing order)
    - want:
        - two numbers s.t. they add up to the target number
            - numbers[index_1] + numbers[index_2] = target
            - 1 <= index_1 <= index_2 <= numbers.length
        - return a list[int] -> result = [index_1, index_2]
    - constraints:
        - exactly one solution guaranteed
        - can only use constant extra space (no extra data structures that vary with input length)
        - 2 <= numbers.length <= 3*10^4
        - -1000 <= numbers[i] <= 1000
        - -1000 <= target <= 1000
    E:
    - the examples are relatively clear
    D:
    - no extra DS that vary with input length
    - an array maybe sufficient enough
    A:
    - the Brute Force approach is relatively straightforward:
        - for index_1 in indices of numbers:
            - for index_2 in indices of numbers > index:
                - if numbers[index_1] + numbers[index_2] == target:
                    - return index_1, index_2
    - however, this is clearly O(n^2), can we improve upon this?
    - we can't use a map 
    - can we use two pointers somehow instead?
    - IDEA 1: what if we keep two pointers and update based on an observation
        - we can try from the left and the right most values
        - check if the values add up to target
        - if they do, return the indices
        - else, check if they are less than or greater than the target
            - if less than target, we want to increase our sum
                - so update left pointer
            - however, if not, we want to decrease our sum
                - so update right pointer
        - keep iterating while left < right

        - init: left, right = 0, len(target)-1
        - while left < right:
            - sum_of_vals = numbers[left] + numbers[right]
            - if sum_of_vals == target:
                - return left, right
            - elif sum_of_vals < target: # if less than target, we want to 
                                         # increase our sum value, so increment
                                         # smaller value
                - left += 1
            else:                        # therefore it must be greater so we need to decrement the sum 
                - right -= 1
        - ADDENDUM (we're 1-indexed so before returing, add +1 to both left and right)
    C:
    """
    def twoSum(self, numbers: list[int], target: int) -> list[int]:
        left, right = 0, len(numbers)-1
        while left < right:
            sum_of_vals = numbers[left] + numbers[right]
            if sum_of_vals == target:
                return left + 1,right + 1
            elif sum_of_vals < target:
                left += 1
            else:
                right -= 1
        return None # failsafe no answer found