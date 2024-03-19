class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // P: group anagrams together given an array of strings
        // return some sort of array of arrays with strings grouped together 
        // based on if they share the same set of letters
        // -- lower case English letters
        // E: provided by problem
        // D: HashMap of ArrayLists
        // -- key: word with letters in sorted order
        // -- value: ArrayList that contains words that have the same
        // -- exact letters
        // A: a general loop, loop throguh array of strings
        // sort the word, without changing the original word ofc
        // if sorted word exists:
        // add word to the arrayList that it maps to
        // else:
        // create array list, and add
        // og word to arraylist
        // put that new sorted word
        // to hashmap,  with arraylist as value
        // then at the end, return an array of size, number of keys, 
        // and each ArrayList as an object

        HashMap<String, ArrayList<String>> 
            sortedListToWordsArray = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            if(sortedListToWordsArray.containsKey(sortedString)){
                sortedListToWordsArray.get(sortedString).add(strs[i]);
            }
            else {
                ArrayList<String> wordsArray = new ArrayList<>();
                wordsArray.add(strs[i]);
                sortedListToWordsArray.put(sortedString, wordsArray);
            }
        }
        List<List<String>> groupedAnagrams = new ArrayList<>();
        Object[] keyArray = sortedListToWordsArray.keySet().toArray();
        for(int i = 0; i < keyArray.length; i++){
            groupedAnagrams.add(
                sortedListToWordsArray.get(keyArray[i])
            );
        }
        return groupedAnagrams;
    }
}