class TrieNode{
    HashMap<Character, TrieNode> children;
    boolean isWord;
    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }

    public void addWord(String word) {
        TrieNode current = this;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) { // if char does not exist
                current.children.put(c, new TrieNode()); // create node for particular char
            }
            current = current.children.get(c);
        }
        current.isWord = true;
    }
}

class Solution {
    /**
    P:
    - given an m by n, char[][] board
    - and String[] words
    - want to return a List<String> of all words on the board
    - constraint: each word must be constructed from
    letters of sequentially adjacent cells, where adjacent cells
    are horizontally or vertically neighboring 
    - same letter cell may not be used more than once in a word
    E:
    - the examples make sense! 
    - in example 1, it looks there's no necessity of having
    to go left to right (as in "eat" is found as "tae")
    D:
    - we can attempt to do this with a Trie
    A:
    - 
    C:
     */

    HashSet<String> results;
    // don't want to repeat chars more than once
    boolean[][] visit;

    int ROWS;
    int COLS;

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }

        ROWS = board.length;
        COLS = board[0].length;

        results = new HashSet();
        // don't want to repeat chars more than once
        visit = new boolean[ROWS][COLS];

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                dfs(board, r, c, root, "");
            }
        }

        List<String> res = new ArrayList<>();
        for (String word: results) {
            res.add(word);
        }
        return res;
    }

    // helper func
    public void dfs(char[][] board, int row, int col, TrieNode node, String word) {
        if (row < 0 || col < 0 ||
            row >= ROWS || col >= COLS || 
            visit[row][col] || 
            !node.children.containsKey(board[row][col])) {
            return;
        }

        visit[row][col] = true;
        node = node.children.get(board[row][col]);
        word += board[row][col];
        if (node.isWord) {
            results.add(word);
        }

        dfs(board, row - 1, col, node, word);
        dfs(board, row + 1, col, node, word);
        dfs(board, row, col - 1, node, word);
        dfs(board, row, col + 1, node, word);

        visit[row][col] = false;
    }
}