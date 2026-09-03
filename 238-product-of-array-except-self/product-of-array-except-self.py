class Solution:
    """
    P: 
    - given:
        - List of int - nums
    - want:
        - List of int - answer
            - answer[i] product of all elements of nums except nums[i]
    - constraints: 
        - No Division operation
        - must run in O(n) -> linear time
        - 2 <= nums.length <= 10^5
        - -30 <= nums[i] <= 30
        - answer[i] can fit in 32-bit int
        - only O(1) extra space complexity? Optional follow-up
    E:
    - the examples make sense
    D:
    - before thinking about the follow-up (constant-space complexity)...
    - how can we avoid doing repeat calculations
    - could we use an extra list or map, to keep track of the multiplied
    values before the index, and another to keep trcak of the values after the
    index?
    ie: for example 1-> nums = [1, 2, 3, 4]
        - before would be:
            before = [1, 1, 2, 6]
            after = [24, 12, 4, 1]
            - where we use 1 if its the first number
    A:
    - APPROACH 1: the brute force method would be O(n^2)
        - init result list
        - for i in range(len(nums)):
            val = nums[i]
            for j in range(len(nums)):
                - if i == j:
                    continue
                val *= nums[j]
            append val to result
        return result
    - how can we linearize this? 
    - again, another option was to calculate a total,
    and divide, but the division operator is not allowed...
    - APPROACH 2: can we use two extra lists? A before list and an after list:
        - init result
        - init before
        - for i in range(len(nums)):
            if i == 0:
                before.append(1) # we use 1 for the very first value
            else:
                before.append(before[len(before) - 1] * nums[i-1]) # multiply the previous value
        - init after
        - for i in range(len(nums)-1, -1):
            if i == len(nums)-1:
                after.append(1)
            else:
                after.append(after[len(after) - 1] * nums[i + 1])
        - flip after
        - for i in range(len(nums)):
            result.append(before[i] * after[i])
    - APPROACH 2 works! (However, it is O(n) space complexity as we're creating
    two arrays of equal length to the input)
    - regarding the follow up now...
        - to reduce too much extra space,
        we can actually utilize the results array immediately
        - first, calculate the before product as done prior
    C:
    """
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        result = []
        # before = [] # stores the product of the values before index, (except if first value where it is 1)
        # for i in range(len(nums)):
        #     if i == 0:
        #         before.append(1)
        #     else:
        #         before.append(before[len(before) - 1] * nums[i - 1])
        # after = [] # stores the product of the values after index (except last value, where it is 1 (a placeholder))
        # for i in range(len(nums)-1, -1, -1):
        #     if i == len(nums) - 1:
        #         after.append(1)
        #     else:
        #         after.append(after[len(after) - 1] * nums[i + 1])
        # after.reverse() # reverse the after list
        # for i in range(len(nums)):
        #     result.append(before[i] * after[i])

        before = 1
        for i in range(len(nums)):
            if i == 0:
                result.append(before)
            else:
                before *= nums[i - 1]
                result.append(before)
        after = 1
        for i in range(len(nums)-1, -1, -1):
            if i == len(nums)-1:
                result[i] *= after
            else:
                after *= nums[i + 1]
                result[i] *= after
        return result
