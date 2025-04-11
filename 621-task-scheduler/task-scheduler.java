class Solution {
    /**
    P:
        - GIVEN: - array of CPU tasks labelled from A to Z
                 - a number n
        - FIND: min number of CPU intervals required to complete all tasks
        - can be idle or allow the completion of one task
        - CONSTRAINT: at least an n interval gap between two tasks with the same label
    E:
        - examples are pretty clear
    D:
        - maybe a map to store the frequencies of the letters
        - a queue to keep track of used letters, and when they can be added back
        - a maxHeap to store frequencies of letters 
    A:
        - in a maxHeap store the frequencies of all the letters
        - increment interval

        while (maxHeap not empty && queue not empty)
            - get count of that letter from top of heap
            - count of letter --
            - add count of letter, and the current time + n
            to the queue, to track when it can be used next
            - if (queue.peek != null)
                - check if current top, time it can be used is equal to interval
                - if so, poll and add to priority queue
            - interval++

        return interval
    C:  
     */

    

    public int leastInterval(char[] tasks, int n) {
        int t = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<int[]> queue = new LinkedList<>();
        Map<Character, Integer> frequencies = new HashMap<>();

        // get character frequencies
        for (int i = 0; i < tasks.length; i++) {
            if (frequencies.containsKey(tasks[i])) {
                frequencies.put(tasks[i], frequencies.get(tasks[i]) + 1);
            } else {
                frequencies.put(tasks[i], 1);
            }
        }

        // add character frequencies to maxHeap
        for (Character c : frequencies.keySet()) {
            maxHeap.add(frequencies.get(c));
        }

        // determine max interval 
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            if (!maxHeap.isEmpty()) {
                int currentMaxFreq = maxHeap.poll();
                currentMaxFreq--;
                if (currentMaxFreq > 0){
                    // add the just removed max frequency and add it, and the next time
                    // it can be used to the queue, if the frequency is greater than 0 still
                    int[] freqAndNextAvailable = new int[]{currentMaxFreq, t + n}; 
                    queue.add(freqAndNextAvailable);
                }
            }
            // if queue is not empty, check the current top
            if (!queue.isEmpty()) {
                if (queue.peek()[1] == t) {
                    int freq = queue.poll()[0]; // get the frequency of a char at the top of the quuee
                    maxHeap.add(freq); // and add it back to the max heap
                }
            }
            t++; // increment interval
        }

        return t;
    }
}