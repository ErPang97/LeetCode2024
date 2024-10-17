class Solution {
    public List<String> generateParenthesis(int n) {
        // P: - We know: there will always be n of "(" and ")"
        //      so we just need to figure out the different schemes
        //      that are valid.
        // E: 
        // D: - stack
        // A: - backtracking problem?
        /*   - general idea is to build up the solution 
         *   - keep count of how many open parantheses
         *   and closed parantheses 
         *   - build recursively until number of closed
         *   and open = 3
         *       - if numClosed and numOpen == n
         *            - build solution and add to returnList
         *       - if numOpen < 3
         *            - add "(" to stack
         *            - backtrack/ recurse with numOpen + 1
         *            - pop stack 
         *       - if numClosed < numOpen:
         *            - add ")"
         *            - backtrack/recurse with numClosed + 1
         *            - pop stack
         */
        // C:

        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        result = backTrack(n, 0, 0, 
                stack, result);
        return result;
    }

    public List<String> backTrack(int n, int numOpen, int numClosed, 
            Stack<String> stack, List<String> result) {

        if(numOpen == numClosed && numOpen == n && numClosed == n) {
            result.add(String.join("", stack));
        }

        if(numOpen < n){
            stack.add("(");
            backTrack(n, numOpen+1, numClosed, stack, result);
            stack.pop();
        }

        if(numClosed < numOpen) {
            stack.add(")");
            backTrack(n, numOpen, numClosed+1, stack, result);
            stack.pop();
        }

        return result;
    }
}