class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // P:
        // E:
        /**
         */
        // D: only arrays needed
        /**
         */
        // A:
        /**
         *  A classic sort of backtracking problem
         *  We'll use recursions to add candidates without using repeats
         *  to get to target
         *  
         *  Algo:
         *  solutions = emptyList
         *  currentCandidate = emptyList
         *  recursiveSolutions(solutions, index, target, currentCandidate)
         *       for i in candidates:
         *            if sum(currentCandidate) == target:
         *                 solutions.add(currentCandidate)
         *            else if sum(currentCandidate) > target 
         *                 return
         *            else: 
         *                 copyCandidate = copy(currentCandidate)
         *                 recursiveSolutions(solutions, index++, target, copyCandidate)
         *
         * ADDENDUM: there's still an issue of duplicate combinations due to
         * repeated numbers, so how to handle??
         * - store in sorted, natural order, and take advantage of ArrayList.equals() method?
         * ADDENDUM TWO: Time Complexity is an issue!
         * - tried sorting the candidate array first, that didn't help much alone
         * - maybe try using a set? and then readding those elements back in an ArrayList?
         */
        // C:

        List<List<Integer>> solutions = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        Arrays.sort(candidates); // sort the candidates array
        generateAndAddSolutions(solutions, 0, candidates, target, candidate);
        return solutions;
    }

    public void generateAndAddSolutions(List<List<Integer>> solutions, int index, int[] candidates, int target, List<Integer> candidate) {
        int size = candidates.length;
        int sum = sum(candidate);
        if(sum == target) {
            solutions.add(candidate);
        } else if(sum > target) {
            return;
        }
        int prev = -1;
        for(int i = index; i < size; i++) {
            if(prev == candidates[i]) continue; // ignore previously used elements
            List<Integer> copyCandidate = copyCandidate(candidate);
            copyCandidate.add(candidates[i]);
            generateAndAddSolutions(solutions, i+1, candidates, target, copyCandidate);
            prev = candidates[i];
        }
    }
    
    /**
     * helper function for copying an ArrayList
     */
    public List<Integer> copyCandidate(List<Integer> candidate){
        ArrayList<Integer> copyCandidate = new ArrayList<>();
        for(int i = 0; i < candidate.size(); i++) {
            copyCandidate.add(candidate.get(i));
        }
        return copyCandidate;
    }

    public int sum (List<Integer> candidate) {
        int sum = 0;
        for(int i = 0; i < candidate.size(); i++) {
            sum += candidate.get(i);
        }
        return sum;
    }

}