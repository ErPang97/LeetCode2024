class Solution {
    /**
    P:
    - given: 
        - an int array: hand 
        - an int: groupSize
    - return   
        - boolean: if we can return a groupSize list of cards,
        of size, groupSize that are consecutive integers
    E:
    -   examples relatively straigthforward!
    D:
    -   Map
    -   
    A:
    -   a bruteforce idea:
        for each int in hand:
            for i in range(1, groupSize):
                if (int + i is in hand)
                    continue
                else 
                    break
        - basically we're looking for whether or not consecutive numbers can be found
        by doing two loops where the outer loop is the starting number
        and the inner loop looks for whether the following ints exist
        - this would be O(n^2) however
        
        - to improve, we can try sorting this, but we also need to take care of
        repeats, so perhaps can use a TreeSet, which maintains sorted order
        - another way is to use two pointers perhaps, along with the set, and we make
         the following assumption:
            given pointers left and right: 
                if we find a point where left and right, we cannot make a continuous 
                number, then we know we cannot starting on any point in between the left 
                and right pointer, so we upgrade the left pointer to where the right pointer 
                is

        - Misunderstood! 
        - UPDATE: we have a left and right pointer, where right pointer would represent the end
        of the group; we simply loop through the sorted, no duplicates array, and if we find locations
        where left and right are as expected, hand[left] + groupSize - 1 == hand[right], increment
        a counter
        - UPDATE 2:
            - use a Map to compute occurrences
            - use a MinHeap to keep track of the minimum key in the map
        - what the question is asking is if we can divide our array into 
        various groups, of groupSize size
        - so we do need to keep track of REPEATS
        - and to do this, we use a map to keep track of occurrences
        - notice that we always try to pop the minimum, and if we find
        we are not trying to pop the minimum it means we have a hole, 
        and cannot actually have a sequence using the number
    C:
    */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : hand) {
            if (count.containsKey(n)) {
                count.replace(n, count.get(n) + 1);
            } else {
                count.put(n, 1);
            }
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : count.keySet()) {
            minHeap.add(n);
        }

        while (!minHeap.isEmpty()) {
            int min = minHeap.peek();
            // check that each number in the sequence exists in
            // the hand array
            for (int i = min; i < min + groupSize; i++) {
                if (!count.containsKey(i)) {
                    return false;
                }
                // we used this number so decrement its count
                count.replace(i, count.get(i) - 1);
                if (count.get(i) == 0) {
                    // the number to be removed, is not the minimum, 
                    // so we have a hole in the sequence
                    if (i != minHeap.peek()){
                        return false;
                    }
                    minHeap.poll();
                }
            }
        }
        return true;
    }
}