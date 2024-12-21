class Solution {
    public String longestCommonPrefix(String[] strs) {
       // P: longest first chars that all words share
       // E: 
       // D:
       // A: 
       /**
       - general idea:
       -- build up the Trie with all the words
       -- then from root of trie:
       -- while number of child nodes equals 1, build up common
       prefix and proceed to child node

       - general idea 2: vertical matching
       -- start with empty "match" string
       -- have a while(i < length of shortest string) loop
       --- inside, we keep reiterating over strs
       --- and read each string from left to right,
       --- if at end, they all match, add that char to match
       --- string
       - return "match"
        */
       // C:

        int shortest = 200;
        for(String s: strs){
            if(s.length() < shortest){
                shortest = s.length();
            }
        }

        String match = "";
        for(int i = 0; i < shortest; i++){
            char current = strs[0].charAt(i);
            for(String s: strs){
                if(s.charAt(i) != current){
                    return match;
                }
            }
            match = match + current;
        }
        return match;
    }
}