class Solution {
    /**
    P:
    - given:
        - an int[] nums
        - an int k, sliding widow size
            - this window moves from the left to the right
        - we can only see the k numbers that are in the window
        - at each iteration, the sliding window moves right by one position
    - return:
        - the max sliding window
        - this is in itself an int[]
        where of size, the number of sliding windows
        - at each index, represents a particular sliding window
        - the value at each index, is the maximum value number of
        the sliding window
    E:
    - examples are pretty clear: example 1:
    Window position                Max      Window index
    ---------------               -----     ------------
    [1  3  -1] -3  5  3  6  7      3             0
    1 [3  -1  -3] 5  3  6  7       3             1
    1  3 [-1  -3  5] 3  6  7       5             2
    1  3  -1 [-3  5  3] 6  7       5             3
    1  3  -1  -3 [5  3  6] 7       6             4
    1  3  -1  -3  5 [3  6  7]      7             5
    D: just arrays likely?
    A:
    - regardless of the approach, we likely want to 
    determine what the number of sliding windows there are
    - this should be easy to calculate as it is simply the difference
    between the nums array length and the value of the k-size
    - Brute Force Algorithm:
        - a brute force algorithm would be to simply
        have an outer loop iterate
        from the the initial end point of the sliding window
        - then, have an interior loop, that starts at the beginning
        of the sliding window
        - at the end of the inner loop iteration, store the max value
        in an array that keeps track of the max values
        - however this is in O(n^2) time complexity
    - How to optimize algorithm?
        - can we keep track of a running maximum as 
        we iterate through the list?
        - if we start with the very first sliding window
        we can easily find its max in O(k) time
        - from there, subsequent sliding windows, will have the 
        same max value or better, unless, the integer that is dropped
        from the left side, was the max; then we have to make sure 
        to see if that max still exists
        - we would only update the max based on the right most integer
        however, so as soon as that max is dropped -> NOT TRUE, it could be
        that the max is the number in a middle of a window 
    C:
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxSlidingWindow = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int left = 0, right = 0;

        while (right < nums.length) {
            // pop smaller values from deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.removeLast();
            }
            deque.addLast(right);

            // remove left val from window
            if (left > deque.peekFirst()) {
                deque.removeFirst();
            }

            if ((right + 1) >= k) {
                maxSlidingWindow[left] = nums[deque.peekFirst()];
                left++;
            }
            right++;
        }
        return maxSlidingWindow;
    }
}