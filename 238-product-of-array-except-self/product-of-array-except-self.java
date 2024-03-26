class Solution {
    public int[] productExceptSelf(int[] nums) {
        // P
        // E
        // D: 2 arrays: one for prefixProducts, 
        //              one for suffixProducts
        // A: loop through nums array
        // -- calculate prefix product of i
        // ---- prefix prod of i is always prefixProduct of i-1 * nums[i]
        // -- calculate suffix product of i
        // ---- suffix prod of i is always suffixProduct of i + 1 * nums[i]
        // -- calculate answer[i]
        // C:
        int[] prefixProducts = new int[nums.length];
        int[] suffixProducts = new int[nums.length];
        int[] answers = new int[nums.length];

        
        for(int i = 0; i < nums.length; i++){
            // handle prefixes
            int prefixProduct;
            if(i == 0){
                prefixProduct = 1;
            } 
            else{
                prefixProduct =
                    nums[i-1] * prefixProducts[i-1];
            }
            prefixProducts[i] = prefixProduct;

            // handle suffixes
            int suffixProduct;
            int reverse = nums.length - i - 1;
            if(reverse == nums.length-1){
                suffixProduct = 1;
            }
            else {
                suffixProduct =
                    nums[reverse+1] * suffixProducts[reverse+1];
            }
            suffixProducts[reverse] = suffixProduct;
        }

        for(int i = 0; i < nums.length; i++){
            answers[i] = prefixProducts[i] * suffixProducts[i];
        }

        return answers;
    }
}