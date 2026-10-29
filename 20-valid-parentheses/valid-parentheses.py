class Solution:
    """
    P:
        - Given:
            - s -> a string that contains '(', ')', '{', '}', '[' and ']'
        - Want:
            - to return a bool that states the input string is valid:
                - open brackets must be closed by the same type of brackets
                - open brackets must be closed in the correct order
                - every close bracket has a corresponding open bracket of the
                same type
        - Observations:
            - any open bracket, must be followed by a corresponding closed bracket
                - if not, than, it cannot be closed
            - by correct order, we mean, the last open bracket, must be followed by
            a corresponding closed bracket
                - if we remove that, the next open bracket should be next to a closed bracket
                - and so on.
        - Constraints:
            - 1 <= s.length <= 10^4
            - s consists of '()[]{}'
    E:
        - the examples are clear
    D:
        - likely, a stack should be sufficient 
            - LIFO DS -> the first to be removed is the top (last) element
    A:
        - init a stack
        - for c in s:
            - if open bracket, add c to the stack
            - if it's a closed bracket, check if the top is a corresponding
            open bracket, and pop from stack
                - if not, return False (cannot close all brackets in order)
        - return true if stack is empty, false otherwise
    C:
    """
    def isValid(self, s: str) -> bool:
        stack = [] # recall in python, pop() removes last item in list, if no index specified
        for c in s:
            if c in ['(', '[', '{']:
                stack.append(c) 
            else: # a closed bracket
                if len(stack) == 0: # no corresponding open bracket, os return False
                    return False
                top = stack[len(stack)-1]
                if top == '(' and c == ')' or top == '[' and c == ']' or top == '{' and c == '}':
                    stack.pop()
                else:
                    return False
        
        if len(stack) == 0:
            return True
        else:
            return False



        