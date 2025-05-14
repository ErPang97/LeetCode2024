class Solution {
    /**
    P:
    - given: a string s
        - contains only '(', ')' and '*'
    - return: boolean, if s is valid
        - if it contains a '(', must be corresponding ')'
        - vice versa
        - corresponding '(' must go before ')'
        - '*' can be a ')' a '(' or an empty '""'
    E:
    - pretty easy to understand
    D: 
    - none 
    A:
        - We create two variables, leftMin and leftMax, 
        which represents, given the wildcards, the minimum
        and maximum open parantheses 
        - we do the following opps to the variables:
            - if c is an open '(', we increment both vars
            - if c is a closed ')', we decrement both vars.
            - where they differ is when c is '*'
                - we decrement leftMin which means we chose ')'
                - we also increment leftMax which means we chose '('
            - if in the loop, leftMax < 0, return False
            as this signifies an impossible situation
            - if leftMin < 0, reset to 0, as we want min to remain 0
        - return leftMin == 0
    C:
     */
    public boolean checkValidString(String s) {
        int leftMin = 0, leftMax = 0;

        for (char c: s.toCharArray()) {
            if (c == '(') {
                leftMin = leftMin+1; 
                leftMax = leftMax+1;
            } else if(c == ')') {
                leftMin = leftMin-1;
                leftMax = leftMax-1;
            } else {
                leftMin = leftMin-1;
                leftMax = leftMax+1;
            }
            if (leftMax < 0) {
                return false;
            }
            if (leftMin < 0) { // consider s = (*)( !!
                leftMin = 0;
            }
        }

        return (leftMin == 0);
    }
}