public class Solution {
    public bool ContainsNearbyDuplicate(int[] nums, int k) {
        // P: 
        /** 
        *   We're again looking for duplicates, but we want to see 
        *   if the indices of the two numbers are less than the value k
        */
        // E: 
        /**
        *   For the first example, [1,2,3,1], k = 3
        *   The values at i = 0, and i = 3 are the same.
        *   If we subtract, 3 - 0 <= k = 3, so this is true s
        */
        // D: Could use a map maybe, but don't know if needed yet
        // A:
        /**
        *   Brainstorming: Brute force would just have two loops checking each number
        *   and then comparing the indices, however that would be O(n^2) time
        *   so... very inefficient
        *   
        *   Instead we'll take a sliding window approach, keeping into account
        *   that the window cannot be greater than size k. 
        *
        *   1st Idea: 
        *       - Have an outer loop that starts at i = 0 to the length of nums - 1 - k
        *       -   Have an inner loop that starts at j = i + 1 up to k exclusive
        *       -       check any duplicates, in this range
        */
        // C:

        for (int i = 0; i < nums.Length; i++) {
            for(int j = i+1; (j-i)<=k; j++){
                if(j > nums.Length-1) break; 
                // Console.WriteLine(i +  ", " + j);
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;

    }
}