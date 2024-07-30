class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // P: we're given an array of integers,
        // and we want to return a List of all 
        // possible size 3 lists, such that
        // nums[i] + nums[j] + nums[k] == 0
        // and i != j != k
        // E: 
        // D: ArrayLists, 
        // A: 
        // - first, sort the input array
        // - i = 0 to len(nums) - 3  (loop for first int in sum)
        // - keep track of seen values for i, as we don't want repeat soln's
        // -- inner loop over will involve two pointers
        // --- initialize j = i+1, k = nums.length-1;
        // --- check sum of all three
        // --- if this sum > 0, decrement k
        // --- if sum < 0, increment j
        // --- append to ArrayList [i, j, k] if satisfies sum == 0
        // --- if at some point, j == k, continue outer loop
        // C:

        // sort the input array
        Arrays.sort(nums);
        
        List<List<Integer>> threeSum = new ArrayList<>();

        for(int i = 0; i < nums.length - 2; i++){
            
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            int k = nums.length-1;
            int j = i+1;

            while(j < k){

                int sum = nums[i] + nums[j] + nums[k];
                // System.out.println(sum);

                if(sum == 0){
                    ArrayList<Integer> triple = new ArrayList<>();
                    triple.add(nums[i]);
                    triple.add(nums[j]);
                    triple.add(nums[k]);
                    triple.sort(null);
                    threeSum.add(triple);
                    
                    j++;
                    while(nums[j] == nums[j-1] && j < k){
                        j++;
                    }
                    

                } else if(sum > 0){
                    k--;
                } else if(sum < 0){
                    j++;
                }
            

            }
        }

        return threeSum;

    }
}