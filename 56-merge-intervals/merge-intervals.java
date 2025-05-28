class Solution {
    /**
    P:
    - we are given: an array called intervals where
        - intervals[i] = [start_i, end_i], representing the start and end of the intervals
        respectively
    - we want to merge all overlapping intervals, and return an array of the non-overlapping
    intervals that cover all intervals in input
    - seems somewhat similar to the add interval problem, except now at the point of just merging
    - follow-up questions: 
        - are we guaranteed that overlaps occur? doesn't say so we assume so
        - are intervals sorted? doesn't say again
    E:
    - example 1 makes sense as [1,3] and [2,6] certainly overlap, to make [1,6]
    - example 2 as well makes sense
    D:
    - none extra needed
    A:
    - sort intervals array in order of of the starting index
    - currentInterval = first interval
    - for each interval in the intervals array
        - if currentInterval overlaps with interval
            - adjust start of currentInterval to be the min of this interval and the other
            - adjust end of currentInterval to be max of this interval and interval
        - else
            - add interval to result interval
    - return result interval
    C:
     */
    public int[][] merge(int[][] intervals) {
        // sort the array by first element
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        for(int[] interval: intervals) {
            // interval overlaps with currentInterval if start falls in between
            // the currentInterval, including if start is equal to either edge
            if (interval[0] >= currentInterval[0] && interval[0] <= currentInterval[1]) {
                currentInterval[0] = Math.min(interval[0], currentInterval[0]);
                currentInterval[1] = Math.max(interval[1], currentInterval[1]);
            } 
            // doesn't overlap, so just add currentInterval, and set to the 
            // current interval of the loop iteration
            else { 
                result.add(currentInterval);
                currentInterval = interval;
            }
        }
        result.add(currentInterval);

        return result.toArray(new int[result.size()][]);
    }
}