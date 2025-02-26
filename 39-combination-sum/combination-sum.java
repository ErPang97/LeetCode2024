class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // P: 
        /**
            - essentially, we're given a list of numbers, candidates[]
            - lets say of size n
            - we want to find, any combination of the numbers in candidates
            - such that the sum of the numbers in the are equal
            - to the target
            - we can use any number multiple times
        */
        // E:
        /**
            - [2, 3, 6, 7] and the target is 7:
                - subsets of size 1: [7]
                - subsets of size 2: none
                - subsets of size 3: [2, 2, 3]
                - subsets of size 4: none
                - subsets of size 5: none
                - subsets of size 6: none
                - subsets of size 7; none
         */
        // D: Arrays should be okay!
        // A:
        /**
                - we will likely use recursion here
                - thought process is to first create an empty list which is just a list of numbers
                - then we pass this list into a function, as well as the target
                - for number in candidate:
                    - check current sum of array list and if equal to target, add this number
                    ADDENDUM: add an index parameter
                    to the return list
                    - if current sum is greater than target, or size of the list is greater than target, we don't pass this list further
                    - if current sum is less than target, pass it through for recursion
                - problem 1: how to prevent non-unique combinations! perhaps, we add an index parameter
                to our recursion function, so that it knows it can only add the number its currently
                at in candidate, and anyhting passed it but not before
         */
        // C:
        List<List<Integer>> uniqueCombinations = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        generateCombinations(candidates, target, uniqueCombinations, current, 0);
        return uniqueCombinations;
    }

    public void generateCombinations(int[] candidates, int target, List<List<Integer>> uniqueCombinations, ArrayList<Integer> current, int index){ 
        if(sum(current) == target){
            uniqueCombinations.add(current);
        } else if(sum(current) > target || current.size() >= target) {
            return; // end the recursion on this branch if so
        }
        for(int i = index; i < candidates.length; i++){
            ArrayList<Integer> copy = copy(current);
            copy.add(candidates[i]);
            generateCombinations(candidates, target, uniqueCombinations, copy, i);
        }
    }

    public int sum(List<Integer> list) {
        int sum = 0;
        for(int i: list){
            sum+= i;
        }
        return sum;
    }

    public ArrayList<Integer> copy(ArrayList<Integer> toCopy){
        ArrayList<Integer> copy = new ArrayList<>();
        for(int i: toCopy){
            copy.add(i);
        }
        return copy;
    }

}