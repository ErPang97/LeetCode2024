class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // map that stores integer to its index
        HashMap<Integer, Integer> intToLastOccurrence = new HashMap<>();
        for(int right = 0; right < nums.length; right++){
            int integer = nums[right]; // the current integer stored at index right
            // left index, is the last occurrence of this integer, 
            // if it was found before
            if(intToLastOccurrence.containsKey(integer) 
                && right - intToLastOccurrence.get(integer) <= k){
                return true;
            }
            intToLastOccurrence.put(integer, right);
        }
        return false;
    }
}