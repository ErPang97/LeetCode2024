class Solution {
    /**
    P:
    - given a string s:
        - consists of words and spaces
    - return length of last word in string
        - word is a maximal substring, only non-space characters
    E:
    - pretty clear overall!
    D:
    - none needed necessarily
    A:
    - we can use perhaps a two pointer solution,
    or really just pay attention to when its not a space!
    - int maxLength = 0
    - int currentLength = 0
    - for char in s:
        - if (char == space) 
            - maxLength = Math.max(maxLength, currentLength) 
            // see if last made string is greater than current max
            - currentLength = 0 // reset current string to 0
        - else
            currentLength++
    C:
     */
    public int lengthOfLastWord(String s) {
        int lastLength = 0, currentLength = 0;
        for (char c: s.toCharArray()) {
            if (c == ' ') {
                if (currentLength != 0)lastLength = currentLength;
                currentLength = 0;
            } else {
                currentLength++;
            }
        }
        // in the event that the last char not a space!
        if (currentLength != 0)lastLength = currentLength;
        return lastLength;
    }
}