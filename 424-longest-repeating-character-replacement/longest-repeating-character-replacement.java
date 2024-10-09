class Solution {
    public int characterReplacement(String s, int k) {
        // P:
        // E:
        // D: HashMap
        // A: Principle idea is to iterate through a window (substring)
        //    and keep track of the most popular character. WindowLength-count
        //    of most popular character, is the amount of replacements needed
        //    for a repeating character substring. If that number is greater than
        //    k, we know that that substring is invalid, so we update the pointers.
        /*
        *   Declare a HashMap characterCounts
        *   Declare a variable maxLength = 0
        *   Iterate through the string, with right pointer
        *   and they're start at the beginning
        *       - first check the s.charAt(right), and update its count
        *       - find the current most frequent character in substring
        *       - then, check if valid substring (windowLength-count <= k)
        *           -   if so: continue with right pointer updates and update maxLength
        *           -   else:  update left pointer and decrement count of char
        *                       at left, and keep doing so until valid
        *       - 
        *                   
        */ 
        // C:

        HashMap<Character, Integer> charCounts = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLength = 1;
        int windowLength = 0;
        char rightChar = s.charAt(right);
        char leftChar = s.charAt(left);

        int mostRepeated = 0;

        while(right < s.length()) {
            rightChar = s.charAt(right);
            leftChar = s.charAt(left);


            // update char counts
            if(charCounts.get(rightChar) == null) {
                charCounts.put(rightChar, 1);
            } else {
                charCounts.replace(rightChar, charCounts.get(rightChar) + 1);
            }

            
            // find most repeated char
            mostRepeated = Math.max(charCounts.get(rightChar), mostRepeated);

            windowLength = right-left + 1;
            

            // check if valid substring, meaning number of different chars.
            // from the most repeated char in substring, has to be <= k
            // keep updating if not valid
            while (windowLength - mostRepeated > k) {
                charCounts.replace(leftChar, charCounts.get(leftChar)-1);
                left++;
                windowLength = right-left + 1;
            } 

            maxLength = Math.max(windowLength, maxLength);
            right++;
            
        }

        return maxLength;

    }
}