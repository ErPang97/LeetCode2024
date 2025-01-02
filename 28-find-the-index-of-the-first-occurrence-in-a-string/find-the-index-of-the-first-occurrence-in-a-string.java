class Solution {
    public int strStr(String haystack, String needle) {
        // P:
        // E:
        // D:
        // A:
        /**
        - two pointers, left and right
        - we want the right pointer, to be needle.length indices away
        - first check if needle.length < haystack.length
            - if not return -1
        - iterate across haystack, with left pointer being the index
        of iteration, and right pointer should be left + needle.length 
            - if substring(left, right) equals needle, return left
            - if right = haystack.length
                break
        - if iteration finishes, return -1
         */
        // C:

        if (haystack.length() < needle.length()) return -1;

        for (int left = 0; left < haystack.length(); left++) {
            int right = left + needle.length();
            if(right > haystack.length()) break;
            if(haystack.substring(left, right).equals(needle)){
                return left;
            }
        }

        return -1;
    }
}