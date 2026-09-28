class Solution:
    """
    P:
        - given:
            - s1 -> string
            - s2 -> a string
        - want:
            - a boolean 
                - if s2 contains a permutation of s1
        - recall:
            - a permutation is simply any rearrangement (including itself)
            of a string
        - constraints:
            - 1 <= s1.length, s2.length <= 10^4
            - s1 and s2 consist of lowercase English letters only
    E:
        - the examples make sense here
            - "ab" has 2 permutations:
                - "ab" -> not found in either
                - "ba" -> found in Ex. 1 s2, but not in Ex. 2
    D:
        - None thought of at the moment
        - Perhaps a trie DS, to represent a sort of decision tree
        representing all the possible permutations that we can get
        - or a set/map to keep track of the counts of the letters seen/counts
    A:  
        - Brute Force Approach:
            - to check a single permutation of s1 if its in s2 is not too tricky
            - as soon as we find the first letter of the permutation of s1, in s2
                - we use two pointers one iterating through s1, and the other through s2
                - if we reach the end of s1, we can return True; it is contained
            - so to extend this, we would need to generate all of the possible permutations
            - we can then iterate through each of the permutations, and check if it is in s2
            - once exhausted, return false, if not found 
            - however, this would definitely be time consuming

        - Idea 1:
            - sort s1 so that its always in sorted order
            - iterate through s2, using a window of size len(s1)
                - if current substring s2 sorted == s1 sorted:
                    return True
            - return False

        - Idea 2:
            - the above solution works, but it is at the limit in terms of speed
            - what else can we say? 
            - permutations must share character frequencies
            - what if we precompute the number of each char in s1
            - do the same for each substring in s2, but pre-compute with the very first substring:
                - first substring == s2[0: len(s1)]
                - obtain counts of that substring
            - we also want to keep track of MATCHES (the number of characters that match)
            - the goal is to ensure they match all letters
    """
    def checkInclusion(self, s1: str, s2: str) -> bool:
        # sorted_s1 = sorted([c for c in s1])
        # for left in range(len(s2) - len(s1)+1):
        #     current_sub = sorted([c for c in s2[left:left+len(s1)]])
        #     if "".join(sorted_s1) == "".join(current_sub):
        #         return True
        # return False

        # edge case where s1 is longer than s2
        # can be no permutation because not all 
        # chars in s1 can be found in s2
        if len(s1) > len(s2):
            return False

        s1_count, s2_count = [0]*26, [0]*26
        for i in range(len(s1)):
            s1_count[ord(s1[i]) - ord('a')] += 1
            s2_count[ord(s2[i]) - ord('a')] += 1
    
        matches = 0
        for i in range(26):
            if s1_count[i] == s2_count[i]:
                matches += 1
        
        left = 0
        for right in range(len(s1), len(s2)):
            if matches == 26:
                return True
            
            # index of character that was just added
            index = ord(s2[right]) - ord('a')
            s2_count[index] += 1
            if s1_count[index] == s2_count[index]:
                matches += 1
            # if match WAS equal, decrement matches
            elif s1_count[index] + 1 == s2_count[index]:
                matches -= 1


            # index of character that was just removed
            index = ord(s2[left]) - ord('a')
            s2_count[index] -= 1
            if s1_count[index] == s2_count[index]:
                matches += 1
            # if match WAS equal, decrement matches
            elif s1_count[index] - 1 == s2_count[index]:
                matches -= 1
            left += 1
        
        if matches == 26:
            return True
        else:
            return False