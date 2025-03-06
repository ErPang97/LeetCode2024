class LetterNode {

    public Map<Character, LetterNode> nextLetters;
    boolean isWord = false;

    public LetterNode() {
        this.nextLetters = new HashMap<>();
    }

    public LetterNode(Map<Character, LetterNode> nextLetters){
        this.nextLetters = nextLetters;
    }

    public void setIsWord(){
        this.isWord = true;
    }

}



class WordDictionary {
    // P:
    // E:
    // D:
    /**
     *  - perhaps some sort of LinkedList like structure, where
     *  each node corresponds to a letter, and each node, has a list or map of
     *  nodes that also correspond to a letter, that it points to
     */
    // A:
    /**
        - ideally it seems we want to make a Trie of some sort
        - recall that a trie, is like a list of letters, where each letter
        points to another letter that follows it, in the words it stores
        - ie: suppose we have a trie, with the word Hello, the first entry point
          we have is H, which then points to e, which then points to l, then to l,        
          then finally to 0, and so forth
        - the trickier aspect, for sure, is the '.' character, which serves as a wildcard
          that can be any letter we wish
     */
    // C:

    public LetterNode head;

    public WordDictionary() {
        head = new LetterNode();
    }
    
    public void addWord(String word) {
        char[] wordArr = word.toCharArray();
        LetterNode current = head;
        for(int i = 0; i < word.length(); i++) {
            if(current.nextLetters.containsKey(wordArr[i])) {
                current = current.nextLetters.get(wordArr[i]);
            } else {
                Map<Character, LetterNode> nextLetter = new HashMap<>();
                LetterNode next = new LetterNode(nextLetter);
                current.nextLetters.put(wordArr[i], next);
                current = next;
            }
        }
        current.setIsWord();
    }
    
    public boolean search(String word) {
        LetterNode current = head; 
        return depthFirstSearch(0, current, word);
    }

    public boolean depthFirstSearch(int j, LetterNode root, String word){
        LetterNode current = root;
        for(int i = j; i < word.length(); i++) {
            char c = word.charAt(i);

            if (c == '.'){ // when the letter could be any letter
                for (LetterNode child: current.nextLetters.values()) {
                    if(child!=null && depthFirstSearch(i + 1, child, word)){
                        return true;
                    }
                }
                return false;
            } else {
                if (!current.nextLetters.containsKey(c)){
                    return false;
                }
                current = current.nextLetters.get(c);
            }
        }
        return current.isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */