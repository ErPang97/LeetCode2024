class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // P:
        /**
            - want to merge nums1 and nums2 together
            modifying nums1 in-place
         */
        // E
        // D
        // A:
        /**
        - idea is to iterate through nums1 and nums2
            - we have two pointers, one that points to current
            number in nums1, and same for nums2
            - we start from the end at both
            while(i >= 0)
            - if(nums1[firstPtr] > nums2[secondPtr])
                - place nums1[i] = nums1[firstPtr]
                - update secondPtr--
            - else if nums2[secondPtr] <= nums1[firstPtr]
                - place nums2[i] = nums1[firstPtr]
                - update firstPtr--
         */
        // C:

        int firstPtr = m - 1;
        int secondPtr = n - 1;
        int right = m + n - 1;

        while(secondPtr >= 0){
            if(firstPtr >= 0 && nums1[firstPtr] > nums2[secondPtr]){
                nums1[right] = nums1[firstPtr];
                firstPtr--;
            } else {
                nums1[right] = nums2[secondPtr];
                secondPtr--;
            }   
            right--;
        }
    }
}