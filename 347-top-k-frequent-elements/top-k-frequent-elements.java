class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // P: Given an array of nums, find the k-most frequent 
        // elements
        // E: 
        // D: 
        // -- HashMap perhaps, for nums to count of nums
        // -- TreeMap for count to nums 
        // -- Arrays ofc. array of size k
        // A: 
        // -- sort array of nums
        // -- loop through nums and map num to count
        // -- create a list of the hashmap keys
        // --
        // -- k values to array

        HashMap<Integer, Integer> numsCount = new HashMap<>();

        // sort nums and get counts of values in array
        for(int i = 0; i < nums.length; i++){
            if(numsCount.containsKey(nums[i])){
                int count = numsCount.get(nums[i]) + 1;
                numsCount.replace(nums[i], count);
            }
            else {
                numsCount.put(nums[i], 1);
            }
        }
        List<Integer> list = new ArrayList(numsCount.keySet());
        // lambda expression, comparator for sorting keys a and b
        // based on values
        list.sort(
                (a, b) -> numsCount.get(b) - numsCount.get(a)
            );
        int[] kMostFrequent = new int[k];
        for(int i = 0; i < k; i++){
            kMostFrequent[i] = list.get(i);
        }
        return kMostFrequent;
    }
}