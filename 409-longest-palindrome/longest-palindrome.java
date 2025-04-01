class Solution {
    public int longestPalindrome(String s) {
        /**
        P: given a string s, what is the longest palindrome
        we can build with the chars in s
        - return the length of that
         */
        /**
        E: the examples probided are pretty good
         */
        /**
        D: maybe a hash map of some sort
         */
        /**
        A: 
        - use a hash map to count the occurrences of all the letters
        - for i in occurrences:
            if i is even:
                max_len += i
            else:
                max_len += i-1 , add i - 1, if odd, then one won't be matched
        - return max_len, or max_len+1 if max_len is odd, as it ensures that we do have
        at least one more of the number that made this odd, to add back as the center
         */
        // C:

        int maxLength = 0;
        HashMap<Character, Integer> letterToCccurrences = new HashMap<>();
        for (char c: s.toCharArray()) {
            if (letterToCccurrences.containsKey(c)) {
                letterToCccurrences.put(c, letterToCccurrences.get(c) + 1);
            } else {
                letterToCccurrences.put(c, 1);
            }
        }

        ArrayList<Integer> occurrences = new ArrayList<>();
        for (Character c : letterToCccurrences.keySet()){
            occurrences.add(letterToCccurrences.get(c));
        }

        occurrences.sort(Comparator.naturalOrder());

        boolean hasOddFrequency = false;
        for (int freq : occurrences) {
            // Check is the frequency is even
            if ((freq % 2) == 0) {
                maxLength += freq;
            } else {
                // If the frequency is odd, one occurrence of the
                // character will remain without a match
                maxLength += freq - 1;
                hasOddFrequency = true;
            }
        }
        // If hasOddFrequency is true, we have at least one unmatched
        // character to make the center of an odd length palindrome.
        if (hasOddFrequency) return maxLength + 1;

        return maxLength;
    }
}