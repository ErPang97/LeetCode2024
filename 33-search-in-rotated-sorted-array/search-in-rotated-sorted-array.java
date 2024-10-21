class Solution {
    public int search(int[] nums, int target) {
        // P:
        // E:
        // D:
        // A:
        /**
         * - alg should be a relatively straightforward Binary Search
         * - we initialize two pointers as usual, a left and right pointer
         * - we calculate a mid = (left+right)/2, and if left < mid, then
         *  that portion of the array is strictly increasing, and therfore
         *  sorted
         * - we know also, if right > mid, similarly, that portion is strictly 
         * increasing, and therefore a sorted half
         *  ALG:
         *  while(left <= right)
         * - if the nums[mid] == target; return mid
         * - else, check if the value, is within the range of the sorted half,
         * - or unsorted half
         *      - if so, reduce search to the sorted half portion
         *      - else, search the other half
         */
        // C:

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left+right)/2;

            if(nums[mid] == target) {
                return mid;
            } 
            if(nums[left] == target){
                return left;
            }
            if(nums[right] == target){
                return right;
            }

            if(nums[left] < nums[mid]) {
                if(target < nums[mid] && target >= nums[left]) {
                    // search the left sorted half if target in between this
                    // range
                    right = mid - 1;
                } else {
                    // search the right half
                    left = mid + 1;
                }
            } else {
                if(target <= nums[right] && target > nums[mid]){
                    // search the right sorted half if so
                    left = mid + 1;
                } else {
                    // search the left half
                    right = mid - 1;
                }
            }
        }
        return -1;


    }
}