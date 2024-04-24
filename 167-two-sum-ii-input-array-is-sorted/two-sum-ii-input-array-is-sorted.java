class Solution {
    public int[] twoSum(int[] numbers, int target) {
        /**
         * P: return the two indices (i, j), in which nums[i] + nums[j] = target
         * E: 
         * D: no extra DS, just a pointer for first and last
         * A: - loop through array, and store first pointer as number[i = 0], and 
         *    - perform binarySearch to find second index, if contained, return the value
         * C: 
         * 
         */

         int [] indices = new int[2];
         int i = 0;
         int j = 1;
         for (i = 0; i < numbers.length; i++){
            if(i < numbers.length){
                j = Arrays.binarySearch(numbers, i+1, 
                                        numbers.length, 
                                        target-numbers[i]);
            }
            if(j > 0){
                indices[0] = i+1;
                indices[1] = j+1;
                break;
            }
         }
         return indices;
    }
}