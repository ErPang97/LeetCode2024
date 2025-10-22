class Solution {
    /**
    P:
        - given:
            s: a string of length m
            t: a string of length n
        - return:
            a minimum window SubString 
            of s, s.t. every character 
            in t is included in the window
            (assuming it exists! if it doesn't
            return empty string "")
    E:
        - the examples make sense:
        - NOTE: can include potential chars
        not found in t 
        - NOTE2: must include the correct
        number as well
    D:
        - An idea is to store the counts
        of each char in t 
        - so will likely need a Map of
        some sort
    A:
        - Brute Force:
        - a brute force alg would be to
        check every possible window
        and simply check whether it has the
        correct counts
        - we would then find the minimum
        size window and return that
        - sort of like an argmax across
        all potential substrings
        - this would take plenty of time
        however

        - Optimize? Use Spanning Sliding Windows
        Algorithm:
        - first: store the counts of all t
        in a window O(n)
        - use two pointers when iterating through
        s
        - idea1: increment right pointer keeping track
        of counts of all in s, until the counts
        of all vales in s, match the values in t
            - once right most boundary is found:
            try incrementing left until the counts
            are equal
            - if the left most bound not in t,
            keep incrementing until we find
            the left most bound in s as well
        - idea2:
            - addendum, we need to find the minimum
            - as long as we have all chars, increment left
            - when incrementing left pointer, we
            may be incrementing and decrementing
            counts in T so we need to keep doing so
            - there may be a min elsewhere
            - when this is broken, increment right again
    C:
     */
    public String minWindow(String s, String t) {
        if (t.isEmpty()) { // base case  when t is an empty string
            return "";
        }

        // countT maintains the count of the chars in t
        // window maintains the counts in the given window
        HashMap<Character, Integer> countT = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        // populate the counts of the chars in T
        for (char c: t.toCharArray()) {
            countT.put(c, 1 + countT.getOrDefault(c, 0));
        }

        int have = 0;
        int need = countT.size();

        int[] result = {-1, -1};
        int resLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        // update the right size window 
        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, 1 + window.getOrDefault(c, 0));

            // changing from == to .equals() fixed some issue?????
            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have +=1;
            }

            while (have == need) {
                // update our result
                if (right - left + 1 < resLength) {
                    resLength = right - left + 1;
                    result[0] = left;
                    result[1] = right; 
                }

                // as long as this result is met, try and 
                // increment left to find a minimal
                // size substring
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (countT.containsKey(leftChar) 
                    && window.get(leftChar) < countT.get(leftChar)) 
                {
                    have -= 1;
                }
                left += 1;
            }
        }
        return resLength == Integer.MAX_VALUE ? "" : s.substring(result[0], result[1] + 1);
    }
}