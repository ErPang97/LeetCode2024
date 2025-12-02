class Solution {
    /**
    P:
        Given: 
            - a String beginWord
            - a String endWord
            - a List of String, wordList
        Want:
            - a new List of String
            in so far as, starting with the first word beginWord
            the following word has to have only one letter different
            from the previous word, until we have the endWord
            - essentially it's like we're transforming beginWord to
            endWord, changing one letter at a time, and we're finding
            this sequence of resulting words. However, each word must exist in the 
            wordList
        Return:
            - the number of words for the shortest transformation sequence from 
            beginWord to endWord, or 0 if no such sequence exists
    E:
        - the examples make sense
    D:
        - Perhaps an Adjacency List? As BFS was the approach I was thinking of
    A: 
        - an approach I was thinking was to generate a tree using beginWord
        as the root:
            - at each node of this tree, where each node has a String value, 
            the children of said node, if any, are the words in wordList 
            that differ by one character, but we make sure not to revisit nodes, by
            keeping a set of nodes that have been added
            - however, there may be a difficulty regarding the time complexity of 
            generating this tree, so how to solve?
        - Afterwards, a general BFS will work
    C:
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // endWord is not found in the list
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // will serve as our adjacency list
        Map<String, List<String>> neighbors = new HashMap<>();
        wordList.add(beginWord);
        for (String word: wordList) {
            for (int j = 0; j < word.length(); j++) {
                // replace a char with a 
                String pattern = word.substring(0, j) + "*" + word.substring(j+1, word.length());
                // create pattern as key in dict if not
                // already done. Then, add the word to the matching
                // pattern
                if (!neighbors.containsKey(pattern)) {
                    neighbors.put(pattern, new ArrayList());
                }
                neighbors.get(pattern).add(word);
            }
        }

        // BFS
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int result = 1;
        while (!queue.isEmpty()) {
            int layerSize = queue.size();
            // loop throguh current layer first
            for (int i = 0; i < layerSize; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return result;
                }
                // find neighbors of word, by looking
                // at patterns this word falls into
                for (int j = 0; j < word.length(); j++) {
                String pattern = word.substring(0, j) + "*" + word.substring(j+1, word.length());
                    for (String neighbor: neighbors.get(pattern)) {
                        // ignore seen words
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }   
                    }
                }
            }
            // increment depth after current layer exhausted
            result+=1;
        }
        return 0;
    }
}