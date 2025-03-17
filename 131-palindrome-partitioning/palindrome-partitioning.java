class Solution {
    public List<List<String>> partition(String s) {
        // P: 
        /**
         * Given a word s, we want to partition the string
         * s, st the substrings of the partition are also a palindrome
         */
        // E: 
        /**
         * The first example is pretty demonstrative. "aab".
         * If we partition, a string s after every letter we get:
         * 1 : ["a", "a", "b"]
         * If we partition the string once after the two "aa"'s 
         * we get:
         * 2: [["aa", "b"]]
         */
        // D:
        /**
         * Lists ofc
         */
        // A:
        /**
         * 
         */
        // C:
        List<List<String>> result = new ArrayList<>();
        List<String> partition = new ArrayList<>();

        depthFirstSearchPalindromes(s, result, partition, 0);
        return result;
    }

    public void depthFirstSearchPalindromes(String s,
        List<List<String>> palindromes, List<String> partition, int i) {
        // as long as i, the partition index, is greater than or equal to 
        // length of the string, we've exhausted the possible partition
        // spots, so we want to add this to the palindromes list and return from
        // this recursion
        if (i >= s.length()) {
            palindromes.add(copy(partition));
            return;
        }

        // We now check for possible partitions spots j after the current index
        // i, and if the substring[i, j] is also a palindrome, add it to the current 
        // partition list, and further check if that palindrome can be split further
        // Only have the one partition variable, so after checcking for all possible combos
        // given the last partition added, we remove this variable
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                partition.add(s.substring(i, j+1));
                depthFirstSearchPalindromes(s, palindromes, partition, j+1);
                partition.remove(partition.size()-1);
            }
        }
    }

    public ArrayList<String> copy(List<String> list) {
        ArrayList<String> copy = new ArrayList<>();
        for (String s: list) {
            copy.add(s);
        }
        return copy;
    }

    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

}