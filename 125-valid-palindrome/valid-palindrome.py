class Solution:
    """
    P:
    - Given:
        - a str -> s
    - Want:
        - a boolean return:
            - True if s is a palindrome, False otherwise
            - note that this means removing all non-alpha chars!
    E:
    -   the examples make sense
    D:
    -   no extr DS's needed
    A:
        Approach 1:
        - remove any non-alpha chars, and join together with no spaces
        - lowercase the resulting string
        - iterate through half of the string and compare the end and beginning strings
            - if any char is not the same return False
        - return True
    C:
    """
    def isPalindrome(self, s: str) -> bool:
        preprocess = []
        for char in s:
            char = char.lower()
            if str.isalnum(char):
                preprocess.append(char)
        
        str_preprocess = ''.join(preprocess)
        for i in range(len(str_preprocess)//2):
            if str_preprocess[i] != str_preprocess[len(str_preprocess) - i - 1]:
                return False

        return True
        
            