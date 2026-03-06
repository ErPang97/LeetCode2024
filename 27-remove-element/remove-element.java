class Solution {
    /**
    P:
        - given an int[] nums
        - given int val
        - want:
            - remove all occurrences of val in nums
            - return the NUMBER OF ELEMENTS which are NOT EQUAL to val
            - want to modify nums IN-PLACE (no new arrays!)
    E:
        - examples are pretty clear
    D:
        - no extra DS
    A:
        - occ = 0
        - sort nums 
        - for current in nums:
            - if current == val:
                - increment occ
    C:
     */
    public int removeElement(int[] nums, int val) {
        // Arrays.sort(nums);
        int occ = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i] = Integer.MAX_VALUE;
                occ++;
            }
        }
        Arrays.sort(nums);
        int k = nums.length-occ; // we know k is the total count not val
        // int j = 0; // right pointer to swap end values
        // for (int i = 0; i < k; i++) {
        //     if (nums[i] == val && j < occ) {
        //         // swap the last digits with the current position
        //         int temp = nums[k - 1 + j];
        //         nums[j] = val;
        //         nums[i] = temp;
        //         j++;
        //     }
        // }

        return k;
    }
}