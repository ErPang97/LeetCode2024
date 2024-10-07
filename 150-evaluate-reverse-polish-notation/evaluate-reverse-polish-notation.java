class Solution {
    public int evalRPN(String[] tokens) {
        //P"
        //E:
        //D: Stack
        //A:
        /*
         * read through the tokens, and append any
         number that appears, to the stack
         if we encounter an operator:
            pop two values from stack
            - first val is the second number in the operation
            - second val is the first number in the operation
            - eval. first num (operator) second num
            - append value to the stack and continue
         */
        //C:
        String operators = "+-*/";

        Stack<String> vals = new Stack<>();
        for(String token: tokens){
            if(operators.contains(token)){
                int secondVal = Integer.parseInt(vals.pop());
                int firstVal = Integer.parseInt(vals.pop());
                int afterOp = 0;
                if(token.equals("+")){
                    afterOp = firstVal + secondVal;
                } else if(token.equals("-")) {
                    afterOp = firstVal - secondVal;
                } else if(token.equals("*")){
                    afterOp = firstVal * secondVal;
                } else if(token.equals("/")){
                    afterOp = firstVal / secondVal;
                }
                vals.add(""+afterOp);
            } else {
                vals.add(token);
            }
        }

        return Integer.parseInt(vals.pop());
    }
}