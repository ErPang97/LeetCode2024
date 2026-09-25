class Solution:
    """
    P:
        - given:
            - s -> a string
        - want:
            - the LENGTH  (an int) of the longest substring
            w/o duplicate characters
        - recall:
            - a substring is any set of contiguous adjacent characters
            - non-repeating of course means to not have any character 
            more than one time
        - constraints:
            0 <= s.length <= 10^5
    E:
        - Example 1:
            - abcabcbb
                "abc" -> 3
                "bca" -> another one
                "cab" -> yet another length 3
            - no longer one
        - Example 2:
            - all repeating b's so naturally only one longest subsequence
        - Example 3:
            - "wke" 
            - "kew" are the two candidates that work here and are length 3
            - no other sequence exists that is longer
    D:
        - immediatley the DS I thought of could be a map 
        that is used to maintain the number of chars that our current substring has
        - MAp from char to count of characters
    A:
        - Brute Force first:
            - this method would involve searching all possible substrings, and subsequently
            counting the character counts in each substring
            - for i in range (len(string)):
                - for j in range(i+1, len(string))):
                    - for k in substring chars (string(i, j) inclusive):
                        - count the chars with a map
                        - if any count > 1:
                            continue
                        - else:
                            if (j - i)+1 > best_length
                                update best length
            - clearly this is O(n**3) and wouldn't suffice
            - can we do better?
        - What if we used a Two-Pointer approach?
            - basic premise, initialize two pointers, left and right
            - left = 0, right = left
            - we start with a sliding window of length 1:
                - we update right and update a map containing the counts of characters
                - if at some point, we find that updating the count of characters results in
                a count that has at least 2, we already exhausted the longest substring w/o repeating 
                characters starting at some index left
                - so we update left accordingly
            - this way we avoid having to go all the way back and doing a long loop
                - the reason this should work is because at the point when right is the index of a duplicate
                character, as long as that duplicate character is within the range left to right-1, we know we
                won't get any longer substrings starting at some index k that is that duplicate between left to right-1

        - previous solution: 
                *   Declare a HashMap, with key as a character,
                    and value as the index of where the character was
                    last found. (instead of maintaing counts, keep check of the index where the last character was found)
                *   Declare three other ints, longestSubString, currentLength,
                    and leftPointer.
                *   Iterate through the string, with the iteration index
                    representing the right pointer.
                        - When a character has been seen and its last
                        seen index is greater than current left,
                        update leftPointer so that its one past 
                        where that char originally was
                *       - Regardless, if char is in map, update the map
                *       - if char isn't in map, add it, and the current index
                *       - calculate currentLength as: i - left + 1;
                *       - update longestSubstring if greater than currentLength
                *   Return longestSubString
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        char_to_last_index = {}
        # base case -> len(s) = 0
        if len(s) == 0:
            return 0
        
        left = 0
        len_longest_substring = 0

        for right in range(len(s)):
            current_char = s[right]
            if current_char in char_to_last_index and char_to_last_index[current_char] >= left:
                last_seen = char_to_last_index[current_char]
                left = last_seen + 1 # update so that we remove duplicate and its one past where it was last
            
            char_to_last_index[current_char] = right
            current_length = right - left + 1 # obtain the length at the moment
            if current_length > len_longest_substring:
                len_longest_substring = current_length
        
        return len_longest_substring