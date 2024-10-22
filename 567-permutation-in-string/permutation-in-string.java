class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // P: - a permutation of s1, does it exist as a substring
        // in s2?
        // E:
        // D:
        /**
         * A Map
         * One HashMAp that maps the characters to
         * the number of times they appear in that string
         * Another HashMap, that will maintain the counts, as
         * we build a substring from s2
         */
        // A: 
        /**
         * we establish
         * init s1Counts Map
         * iterate over char c in s1
         *   - if s1Counts[c] not null, s1Counts(c)++
         *   - else s1Counnts[c] = 1
         *
         * init left = 0;
         * init right = 0;
         * init s2SubCounts;
         * s2Counts.put(s2[left], 1)
         * while(left < right)
         *    if(right - left + 1 == s1.length()) 
         *       check the counts in s1 and s2 and if theyre the same:
         *           return true
         *       s2Counts[s2[left]]--
         *       left++
         *    (build-up right substring counts, as long as 
         *    length less than s1.length())
         *    else if(right - left + 1 < s1.length)
         *        s2Counts[s2[right]]++
         *        right++
         *    else 
         *        s2Counts[s2[left]]--
         *        left++
         */
        // C:

        if(s1.length() > s2.length()) return false;
        if(s1.length()==1) return s2.contains(s1);
        Map<Character, Integer> s1Counts = new HashMap<>();
        for(char c: s1.toCharArray()){
            if(s1Counts.containsKey(c)){
                s1Counts.replace(
                    c, s1Counts.get(c)+1
                );
            } else{
                s1Counts.put(c, 1);
            }
        }

        int left = 0;
        int right = 1;
        HashMap<Character, Integer> s2Counts = new HashMap<>();
        s2Counts.put(s2.charAt(left), 1);

        // possible case where char at left and char at rgiht are the same
        s2Counts.put(s2.charAt(right), 
            s2Counts.getOrDefault(s2.charAt(right), 0)+1);

        while(left < s2.length()){
            if((right - left + 1) == s1.length()){
                // when we create substring of length equal to s1,
                // check the counts of the characters and see if they match
                if(s2Counts.equals(s1Counts)) return true;

                // decrement char at left count and update the left pointer
                s2Counts.put(
                    s2.charAt(left), 
                    (s2Counts.get(s2.charAt(left)) - 1)
                );
                if(s2Counts.get(s2.charAt(left)) == 0) {
                    s2Counts.remove(s2.charAt(left));
                }
                left++;
            }

            else if ((right - left + 1) < s1.length() && right < s2.length()-1){
                // build up window size to the size of the s1 string
                // increment the right pointer, and increment the count
                // of char at right
                right++;
                s2Counts.put(
                    s2.charAt(right), 
                    (s2Counts.getOrDefault(
                        s2.charAt(right), 0
                    )+1)
                );
            } else {
                // the right pointer, reached the end of the string
                // decrement char at left count and update the left pointer
                s2Counts.put(
                    s2.charAt(left), 
                    (s2Counts.get(s2.charAt(left)) - 1)
                );
                if(s2Counts.get(s2.charAt(left)) == 0) {
                    s2Counts.remove(s2.charAt(left));
                }
                left++;
            }
        }

        return false;

    }
}