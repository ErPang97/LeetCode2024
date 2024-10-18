class Solution {
    public int findMin(int[] nums) {
        // P: a sorted array is rotated, some amount of times
        // and we want to find the minimum after its rotated
        // notice: rotating an array { a[0], a[1], ..., a[n-1]} one time
        // results in the following array: 
        // { a[n-1], a[0] ,..., a[n-2]} }
        // - we note that nums in array are unique
        // - other interesting observations:
        /*
         *   - we know that if the array is rotated if
         *      nums[left] > nums[right]
         *   - if nums[right] >= nums[left], we know its strictly increasing
         */
        // E: 
        // D: none extra
        // A: Binary Search!
        /*
        *    initiatilize three pointers, right, left and mid
            init. min = nums[0]
            while(left <= right)
                - if nums[left] < nums[right]
                    implies strict increasing so, 
                    check next if left < min and update min if so
                    - left = mid
                - calc mid point = (left + right)/2
                    update min if nums[mid] < min
                - if nums[mid] >= nums[left]
                    - again implies sorted order
                    so want to search the other half
                    - left = mid+1
                - else
                    - not sorted order and therefore a decrease when
                    incrementing index in array, so 
                    minimum is somewhere in between
                    - right = mid - 1
            return minimum
        */

        int left = 0;
        int right = nums.length-1;
        int mid = (left+right)/2;
        int min = nums[0];

        while(left <= right) {

            if(nums[left] < nums[right]) {
                // implies portion in sorted order
                // so check if nums[left] is new min
                if(nums[left] < min) {
                    min = Math.min(nums[left], min);
                    break;
                }
            }

            mid = (left+right)/2;
            if(nums[mid] < min) {
                min = Math.min(nums[mid], min);
            }

            if(nums[mid] >= nums[left]){
                // implies still increasing so update left to mid
                // search right side
                left = mid+1;
            } else  {
                // nums[mid] < nums[left], so implies not strictly 
                // increasing and therefore minimum somewhere in between 
                // left portion of array 
                right = mid - 1;
            }

        }
        return min;
    }
}