class Solution {
    public boolean containsDuplicate(int[] nums) {
        // P: if duplicates exist, return true, else return false
        // E: [1, 2, 3, 1], true because there's two ones
        // D: Map, that would keep count of each number
        // A: loop through map, and if int is a key in map
        // increment that value
        // else, insert that (key = that number, value = 1)
        // then, simply, can check the length of the keys array
        // Code!

        HashMap<Integer, Integer> duplicates = new HashMap<>();
        for(int i = 0; i < nums.length; i++) { 
            // keeping track of occurrences of each int
            if(duplicates.containsKey(nums[i])) { // update and increment
                duplicates.replace(nums[i], duplicates.get(nums[i]) + 1);
            } else {
                duplicates.put(nums[i], 1);
            }
        }
        
        if(duplicates.size() != nums.length){
            return true;
        }
        return false;

    }
}