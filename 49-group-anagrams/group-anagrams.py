class Solution:
    """
    P:
        - given a List of str called strs
        - want: a List of List of str vals - result
            - want to group the anagrams together, meaning that the words
            in a result[i] are anagrams with each other (we can rearrange them to get any other
            word in the group)
            - populate ofc from strs 
        - constraints:
            - the List - strs is non-zero length up to 10^4
            - any individual str element can be 0 length (empty string) but no longer than 100 chars
            - str[i] consists of lowercase letters
    E:
        - the examples make sense overall
    D:
        - Could use a map, which would map from word to some representation
            - either a representation of letter counts
            - or - an ordered list of the characters themselves sorted
        - OR: a map that takes a representation and as a value
        collects the words that share the same representation
    A:
        - we know for a fact that anagrams will have the same exact letter counts
        - IDEA 1:
            - init reps_to_word = {}
            - for word in strs:
                - word_rep = get_representation(word)
                - if word_rep in reps_to_word:
                    - reps_to_word[word_rep].append(word)

            - def get_representation(word):
                - counts = {}
                - for char in word:
                    - counts[char] = counts.get(char, 0) + 1
                    

    C:
    """
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        result = [] # elements stored are List[str] where all elements of that interior list are anagrams
        reps_to_word = {} # maps from a word_rep to a list of the words that contain that rep
        def get_representation(word): 
            """
            Gets a representation of the word, 
            mapping from each char to the word with
            letters sorted in order
            """
            # word_rep = {}
            # for char in word:
            #     word_rep[char] = word_rep.get(char, 0) + 1
            # rep = []
            # for char in word_rep.keys():
            #     rep.append((char, word_rep[char]))
            # rep = tuple(sorted(rep, key=lambda char_count: char_count[0]))
            rep = ''.join(sorted(word))
            return rep
        
        # populate word counts representations
        for word in strs:
            word_rep = get_representation(word)
            if word_rep in reps_to_word:
                reps_to_word[word_rep].append(word)
            else:
                reps_to_word[word_rep] = [word]
        for key in reps_to_word.keys():
            result.append(reps_to_word[key])
        return result