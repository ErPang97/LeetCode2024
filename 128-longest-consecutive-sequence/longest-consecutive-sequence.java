class Solution {
    public int longestConsecutive(int[] nums) {
        // P
        // E
        // D: some sort of HashSet to keep track?
        // A: 
            /**
             * 1. Convert List to Set
             * 2. Loop through set, and check if the number
             *    starts a sequence (is num - 1 in set?); keep length of each sequence
             *    - if so: check if next number exists in set, and keep count
             *    - if no, move to next number, check if length>currMax and reset
             *    return maxLength
             */
        // C: 

        // initialize the numsSet and add each num from nums to numsSet
        HashSet<Integer> numsSet = new HashSet<>();
        for(int num : nums){
            numsSet.add(num);
        }

        // iterate through nums again
        // keep track of lengths of sequences, and 
        // the maxSequenceLength
        int currentLength = 1;
        int maxLength = 0;
        for(int num: nums) {
            
            // when the number starts a new sequence
            if(!numsSet.contains(num-1)){
                currentLength = 1;
                int nextInSequence = num+1;
                // update currentLength and nextSequence when nextSequence is found
                while(numsSet.contains(nextInSequence)){
                    currentLength++;
                    nextInSequence++;
                }
                // update maxLength
                if(currentLength > maxLength) maxLength = currentLength;
            } else { // if numsSet contains the number, continue to next iteration
                continue;
            }

        }

        return maxLength;
    }
}