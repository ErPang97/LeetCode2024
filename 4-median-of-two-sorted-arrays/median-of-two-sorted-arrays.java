class Solution {
    /**
    P:
        - given two int arrays:
            nums1
            nums2
        - return the median (a double looks like) 
        - this is because if the array length is even
        then the median is the sum of the middle two values divided by 2
        - if odd, then the median is simply the middle value
    E:
        - the examples make sense
    D:
        - N/A
    A:
        - the easy solution would be to create a new merged array and then simply find the 
        midle term
        - however this would be at least O(m+n)
        - we need to reduce this to O(log (m+n))
        - Binary Search approach?
            - 
    C:
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // A is the smaller of the two
        int[] A = nums1;
        int[] B = nums2;

        // calc. total elements and the half index
        int total = A.length + B.length;
        int half = (total + 1)/2;

        // swap the two so that A is shorter
        if (B.length < A.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }


        int left = 0;
        int right = A.length;
        while (left <= right) {
            int i = (left + right) / 2; // A 
            int j = half - i; // j starts at 0, and i does as well; B
            
            int ALeft;
            int ARight;
            if (i > 0)
                ALeft = A[i - 1];
            else { 
                ALeft = Integer.MIN_VALUE;
            }

            if (i < A.length){
                ARight = A[i];
            } else {
                ARight = Integer.MAX_VALUE; 
            }

            int BLeft;
            int BRight;

            if (j > 0)
                BLeft = B[j - 1]; 
            else 
                BLeft = Integer.MIN_VALUE;

            if (j < B.length)
                BRight = B[j];
            else 
                BRight = Integer.MAX_VALUE;

            // partition is correct
            if (ALeft <= BRight && BLeft <= ARight) {
                // odd number of values
                if (total %2 != 0) {
                    return Math.max(ALeft, BLeft);
                }
                // even length
                return (Math.max(ALeft, BLeft) +  Math.min(ARight, BRight)) / 2.0;
            }
            else if (ALeft > BRight) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        return -1;
    }
}