class Solution {
    public int[] twoSum(int[] nums, int target) {
        // P: return [i, j] where nums[i] + nums[j] == target
        // E: from the description
        // D: can use a HashTable,
        // that maps value to, target-value to index?
        // A: idea is to map as key the target-value to the target 
        // which is the index of the value
        // iterate through and if a pair is found, return the pair
        // C:

        HashMap<Integer, Integer> diffToIndex = new HashMap<>();
        int[] indexArray = new int[2];
        // key = target - nums[i], value = i
        for(int i = 0; i < nums.length; i++){
            if(!diffToIndex.containsKey(nums[i])) {
                diffToIndex.put(target-nums[i], i);
            } else {
                indexArray[0] = diffToIndex.get(nums[i]); // saves index of the first val
                indexArray[1] = i;  // save index of the second value
                break;
            }
        }
        return indexArray;
    }
}