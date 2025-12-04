class Solution {
    /**
        P:
        - given an int[][] intervals
            - intervals[i] = [left_i, right_i]
            - size of an interval = right_i - left_i + 1
            (number of integers it contains, inclusive
            of both left_i and right_i
        - given an int[] queries
            - answer to queries[j]:
                - size of smallest interval i st
                left_i <= queries[j] <= right_i
                - if it doesn't exist, answer is -1
        - want an int[] answers such that
            - answer[j] is the answer to queries[j]
        E:
            - the examples make sense
            for example 1 ie:
                - query = 2 is contained in [1,4], [2,4], and [2,4] 
                is certainly the smaller of the two
                - more cleear query = 4, is contained in [1,4], [2,4],
                [3,6],[4,4], and the smallest is [4,4]
        D:
            - a minHeap?
        A:
            - Brute Force is quite straight forward:
            - initialize answers = new int[queries.length]
            - for each query in queries: 
                - int minSizeInterval = infinity
                - for each interval:
                    - if interval contains the query and size of interval is smaller
                    than minSizeInterval
                        - update the minSizeInterval
            - update answers[j] to minSizeInterval
            - if size of queries is n, and size of intervals is m
            then the time complexity would be O(m*n), can we do better?

            - first thing we mway want to consider is to sort the intervals
            and the queries
        C:
        
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // stores the size, followed by the right part of the interval
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0]) // compare by first element
        );

        // copy query array and sort it
        int[] sortedQueries = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            sortedQueries[i] = query;
        }
        Arrays.sort(sortedQueries);

        // map every query to smallest int it belongs to
        Map<Integer, Integer> result = new HashMap<>();
        int i = 0;

        for (int query: sortedQueries) {
            while (i < intervals.length && intervals[i][0] <= query) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                minHeap.add(new int[]{right-left+1, right});
                i+=1;
            }

            // remove all intervals current query doesn't belong to
            while (!minHeap.isEmpty() && minHeap.peek()[1] < query) {
                minHeap.poll();
            }
            // if minHeap is not empty, than we know first val
            // is smallest interval
            if (!minHeap.isEmpty()) {
                result.put(query, minHeap.peek()[0]);
            } else {
                result.put(query, -1);
            }
        }
        
        int[] answer = new int[queries.length];

        for (int j = 0; j < queries.length; j++) {
            int query = queries[j];
            answer[j] = result.get(query);
        }

        return answer;
    }
}