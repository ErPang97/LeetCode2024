class Solution {
    /**
    P:
        - given an int[] nums
        - return the majority element
            - this appears more than floor(n/2) times
    E:
        - the examples make sense:
            ie: [3, 2, 3]
                - 3 appears twice while 2 only appears once, so 3 is returned
            ie: [2, 2, 1, 1, 1, 2, 2]
                - 2 appears 4 times, and 1 appears 3 times, so 2 is returned
    D:
        - an O(n) space solution would use a HashMap to map the counts of each
    A:
        - an O(n) space solution, as stated, would use a map
        - we would iterate through the array and update the counts
        - however, can we accomplish this without using extra space?
            - we may be able if we sorted?
            - but without sorting which takes n log n?
    C:
     */
    public int majorityElement(int[] nums) {
        // // Brute Force Algo:
        // Map<Integer, Integer> map = new HashMap<>();
        // for (int num : nums) {
        //     if (map.containsKey(num)) {
        //         map.put(num, map.get(num) + 1);
        //     } else {
        //         map.put(num, 1);
        //     }
        // }

        // int max = Integer.MIN_VALUE;
        // int maxCount = 0;
        // for (int key : map.keySet()) {
        //     int currentCount = map.get(key);
        //     if (currentCount > maxCount) {
        //         maxCount = currentCount;
        //         max = key;
        //     }
        // }
        // return max;

        // Boyer-Moore Algorithm
        int candidate = Integer.MAX_VALUE;
        int count = 1;
        for (int num : nums) {
            if (num != candidate) {
                count--;
                if (count == 0) {
                    candidate = num;
                    count = 1;
                }
            } else {
                count++;
            }
        }
        return candidate;
    }
}