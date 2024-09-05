class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // P:
        // E:
        // D: none extra
        // A:
        /**
            - use two pointers, one starting at the 0 index (int i)
            - other starting at the length - 1 index (int j)
            - while (i < j){
                - calculate sum of the ints that they point to
                - compare to target
                - if sum < target
                    - increment i
                - else if sum > target
                    - increment j
                - else
                    - return [i, j] array
            }
         */
        // C:

        int i = 0;
        int j = numbers.length - 1;

        int[] ans = new int[2];

        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if(sum < target){
                i++;
            } else if(sum > target){
                j--;
            } else {
                ans[0] = i+1;
                ans[1] = j+1;
                break;
            }
        }

        return ans;
    }
}