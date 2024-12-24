class Trie {

    char letter;
    Map<Character, Trie> children;

    public Trie() {
        this.letter = ' ';
        this.children = new HashMap<>();
    }

    /**
        Associates the Trie object node with a letter
     */
    public void associateLetter(char c){
        this.letter = c;
    }
    

    public void insert(String word) {
        Map<Character, Trie> currentChildren = this.children;
        for (char c: word.toCharArray()){
            Trie current;
            if(currentChildren.containsKey(c)){
                current = currentChildren.get(c);
                currentChildren = current.children;
            } else {
                current = new Trie();
                current.associateLetter(c);
                currentChildren.put(c, current);
                current = currentChildren.get(c);
                currentChildren = current.children;
            }
        }
        
        // insert terminal character ' '
        if(!currentChildren.containsKey(' ')){
            Trie current = new Trie();
            current.associateLetter(' ');
            currentChildren.put(' ', current);
            current = currentChildren.get(' ');
            currentChildren = current.children;
        }
    }
    

    public boolean search(String word) {

        if(word.length() == 0){
            return false;
        }

        Map<Character, Trie> currentChildren = this.children;
        for(char c: word.toCharArray()){
            Trie current;
            if(currentChildren.containsKey(c)){
                current = currentChildren.get(c);
                currentChildren = current.children;
            } else {
                return false;
            }
        }

        // check for terminal character ' '
        if(!currentChildren.containsKey(' ')){
            return false;
        }

        return true;
    }
    
    public boolean startsWith(String prefix) {

        if(prefix.length() == 0){
            return false;
        }

        Map<Character, Trie> currentChildren = this.children;
        for(char c: prefix.toCharArray()){
            Trie current;
            if(currentChildren.containsKey(c)){
                current = currentChildren.get(c);
                currentChildren = current.children;
            } else {
                return false;
            }
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */