class Solution {
    public boolean isValid(String s) {
        // P: 
        // E:
        // D: Stack! maybe also ArrayList for storing unused closed brackets
        // A: 
        /* Principal idea:
           Loop
           Open bracket is added to stack
           Closed bracket, we check if it matches top stack, 
           and if so, pop it; else, add to ArrayList
           If at end of loop, stack is empty, return True
           Else, False
         */
        // C:

        ArrayList<Character> unused = new ArrayList<>();
        Stack <Character> openBrackets = new Stack<>();
        System.out.println(openBrackets.size());

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{'|| c =='['){
                openBrackets.add(c);
            } else if(openBrackets.size() != 0) {
                if(c == ')') {
                    if(openBrackets.peek() == '('){
                        openBrackets.pop();
                    } else{
                        unused.add(c);
                        return false;
                    }
                } else if(c == '}') {
                    if(openBrackets.peek() == '{'){
                        openBrackets.pop();
                    } else{
                        unused.add(c);
                        return false;
                    }
                } else if(c == ']') {
                    if(openBrackets.peek() == '['){
                        openBrackets.pop();
                    }  else{
                        unused.add(c);
                        return false;
                    }
                }
            } else{
                unused.add(c);
                return false;
            }
        }

        if(openBrackets.size() == 0){
            return true;
        }
        return false;

    }
}