class Solution {
    public char findTheDifference(String s, String t) {
        // P:
        // E:
        // D: HashMap is pretty easy
        // A: loop through a merged s+t string
        /**
            - count total occurrences of all chars in string
            loop through string t, and check HashMAp counts
            - if any are odd, return that string
         */
        // C:

        String mergedString = s+t;
        HashMap<Character, Integer> charCounts = new HashMap<>(); 
        for (int i = 0; i < mergedString.length(); i++){
            char c = mergedString.charAt(i);
            if(!charCounts.containsKey(c)) {
                charCounts.put(c, 1);
            } else{
                charCounts.replace(c, charCounts.get(c) + 1);
            }
        }

        char added = '0';
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if(charCounts.get(c) % 2 != 0){
                added = c;
                break;
            }
        }
        return added;
    }
}