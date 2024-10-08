class Solution {
    public int lengthOfLongestSubstring(String s) {
        // P: 
        // E:
        // D: maybe a HashTable to save seen values
        // A:
        /*
        *   Declare a HashMap, with key as a character,
        *   and value as the index of where the character was
        *   last found.
        *   Declare three other ints, longestSubString, currentLength,
        *   and leftPointer.
        *   Iterate through the string, with the iteration index
        *   representing the right pointer.
        *       - When a characte has been seen and its last
        *       seen index is greater than current left,
        *       update leftPointer so that its one past 
        *       where that char originally was
        *       - Regardless, if char is in map, update the map
        *       - if char isn't in map, add it, and the current index
        *       - calculate currentLength as: i - left + 1;
        *       - update longestSubstring if greater than currentLength
        *   Return longestSubString
        */  
        // C:

        // HashMap <Character, String> 
        // key is a character
        // value is the index of where the character was last found
        int longestSubstring = 0;
        int currentLength = 0;
        int left = 0;


        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        // i represents the right pointer
        for(int i = 0; i < s.length(); i++){
            // update left index when character is found, and the index of 
            // where the character was found just before, is greater
            // than the current left index
            // we set left to be just after the index of the last occurrence 
            // of a repeated character
            if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= left) {
                // updates left so that its one past 
                // where that char originally was
                left = map.get(s.charAt(i)) + 1;
                // update location of char to new location
                map.replace(s.charAt(i), i);
            }
            else if(map.containsKey(s.charAt(i))) {
                // update location of char to new location no matter what ^
                map.replace(s.charAt(i), i);
            }
            else if(!map.containsKey(s.charAt(i))){ // new character, place index
                map.put(s.charAt(i), i);
            }
            currentLength = i - left + 1;
            if(currentLength > longestSubstring){
                longestSubstring = currentLength;
            }
        }
        return longestSubstring;
    }
}