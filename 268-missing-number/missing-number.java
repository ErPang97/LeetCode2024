class Solution {
    public int missingNumber(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int max = nums[0];
        for(int i = 0; i < nums.length; i++){
            count.put(nums[i], 1);
            if(nums[i] > max){
                max = nums[i];
            }
        }
        int returnValue = 0;
        for(int i = 0; i <= nums.length; i++){
            if(!count.containsKey(i)){
                returnValue = i;
                break;
            }
        }
        return returnValue;
    }
}