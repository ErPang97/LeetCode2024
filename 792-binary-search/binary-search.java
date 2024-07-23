class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high)/2;
        
        while (low <= high) {
            if(nums[mid] == target){
                return mid;
            } else if (nums[low] == target){
                return low;
            } else if (nums[high] == target) {
                return high;
            }
            else {
                if(nums[mid] > target){
                    high = mid - 1;
                } // search the lower half
                else if (nums[mid] < target) {
                    low = mid + 1;
                } // search upper half
                mid = (low + high)/2; // update mid point
            }
        }

        return -1;


    }
}