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
            if max_len = 0:
                max_len += 1
            else:
                max_len += i / 2 // simply just
        - return max_len
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

        if (occurrences.size() == 1) {
            return occurrences.get(0);
        } 

        for (int c : occurrences) {
            if (maxLength %2 == 0) {
                maxLength += c;
            } else {
                maxLength += (c/2) * 2;
            }
        }

        return maxLength;
    }
}