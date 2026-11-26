class KthLargest:
    """
    P:
        - Given:
            - an integer k
            - a class called KthLargest
            - two methods associated with the class:
                - add
                    - takes one parameter, val, which is the test score to be added
                - __init__ constructor method with int k as one parameter
                    and nums -> initial list of numbers
        - Want:
            - to implement this class, which maintains a stream of test csores
            and returns the kth highest AFTER a new score has been submitted
                - basically the kth element in a sorted list of all scores
                when the value is submitted
            - when calling add, it should return the kth highest val

        - Constraints:
            -  0 <= nums.length <= 10^4
            - 1 <= k <= nums.length + 1
            - -10^4 <= nums[i] <= 10^4
            - -10^4 <= val <= 10^4

    E: 
        - the examples are pretty clear
    
    D:
        - no extra needed at the moment

    A:
        - Simplest Idea:
            - after every call to add, sort the list
            and return the kth largest element

        - PriorityQueue/Heap Idea:
            - simply maintain instead of a sorted list, a priorityqueue
            of size k, and simply return the top element
    C:
    """

    def __init__(self, k: int, nums: list[int]):
        self.k = k
        # self.nums = sorted(nums) # pre-sort the list        
        self.priority_queue = []
        nums = sorted(nums)
        for i in range(len(nums)):
            heapq.heappush(self.priority_queue, nums[i])

        while len(self.priority_queue) > self.k:
            heapq.heappop(self.priority_queue)

    def add(self, val: int) -> int:
        # Sorted List approach:
        # self.nums.append(val)
        # self.nums.sort()
        # return self.nums[len(self.nums) - self.k]


        # Priority Queue Approach:
        heapq.heappush(self.priority_queue, val)

        # in the event adding goes over size k
        while len(self.priority_queue) > self.k:
            heapq.heappop(self.priority_queue)

        return self.priority_queue[0]



# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)