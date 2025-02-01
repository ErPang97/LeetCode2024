class Solution {
    public int majorityElement(int[] nums) {
        // P:
        // E:
        // D: - HashMap for O(n) space solution
        // A:
        /**
        - the easiest method would just be to 
        map each number in nums to a counts HashMAp
        and find the number that returns the largest key
         */
        // C: 

        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int i: nums){
            if(counts.get(i) != null){
                counts.replace(i, counts.get(i)+1);
            } else {
                counts.put(i, 1);
            }
        }

        int majority = nums[0];
        for(int i: nums) {
            if(counts.get(i) > counts.get(majority)){
                majority = i;
            }
        }

        return majority;
    }
}