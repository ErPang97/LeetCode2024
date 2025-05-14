class Solution {
    /**
    P:
    - given: a string s
        - want to partition s into AS MANY PARTS as possible (a max!)
            - each letter can only appear in one part
        - return list of integers representing the size of the parts
    - have to be consecutive parts as we must be able to concatenate the parts
    to make s
    E:
    -  the examples are relatively clear
    D:
    - could we use a hash table to perhaps, keep track of how many of the letters 
    we have, or perhaps we can keep track of maybe, what group its in? which could be the its 
    furthest index perhaps
        - ie: in the first example, ababcbaca, the last index of a is last[a] = 8
    A:
    - 
    C:
     */
    public List<Integer> partitionLabels(String s) {
        // init and put lastOccurrences of each character
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastOccurrence.containsKey(c)) {
                lastOccurrence.replace(c, i);
            } else {
                lastOccurrence.put(c, i);
            }
        }

        // init the partitions' size array
        List<Integer> res = new ArrayList<>();
        int size = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // for each new char, increment size by 1
            size++;
            
            // we check if we reached a valid end of a partition
            // essentially as we loop through a partition, we ensure
            // the current char at i, has no later occurrence than the
            // previously stored end. If so, then we haven't found the true
            // end of the partition, so we update it.
            end = Math.max(end, lastOccurrence.get(s.charAt(i)));
            if (i == end) {
                res.add(size);
                size = 0;
            }
        }
        return res;
    }
}