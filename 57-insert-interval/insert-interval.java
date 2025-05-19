class Solution {
    /**
    P:
    - given: an array called intervals
        - intervals[i] = [start_i, end_i] -> reps start and end of the i-th interval
        - none of these intervals overlap
            - overlapping seems to mean that the start time, occurs at some point
            in between another interval, inclusive perhaps?
        - this array is sorted in ascending order by start_i
    - given: a newInterval = [start, end] -> reps the start and end of another interval
        - want to insert this, and ensure that intervals array still sorted in
        ascending by start order, and no intervals overlap
    - return intervals
        - can be a new array! doesn't need to be modified in-place
    E:

    D:  
        - maybe a Set to store the indices of the intervals our interval overlaps with
    A:
        - we can use a search to find the correct position based on the start 
        interval for sure
        - once we know where the start index is, we need to determine, what intervals
        do overlap:
            - while looping through intervals, we'll compare start and end
            times of each
                - if newInterval start/end is in between the intervals, add 
                interval index to Set
        - init newMergeInterval as copy of newInterval
        - then, iterate through the indices of overlapping intervals
            if overlap[0] < newMergeInterval[0]:
                newMergeInteravl[0] = overlap[0]
            if overlap[1] > newMergeInterval[1]:
                newMergeInterval[1] = overlap[1]
        - finally, we create a new array, add the intervals that aren't in set
        and also add the newMergeInterval in the appropriate spot
         (the insertion will be, then, the smallest index in the overlap set)
        
        - UPDATE: a much simpler way after looking at SOLN!
        - we can do all this in one loop
        iterate through the intervals:
            - if newInterval end is less than the start at the 
            current index
                - we know where to insert, so we insert the new 
                Interval here, there is no merger at this point, 
                and just add the rest afer
            - else, if newInterval start greater than end of 
            current index
                - we haven't found the insertion yet, so 
                just add the current index
            - else
                we know we're in an onverlap zone, so 
                we need to modify newInterval to capture
                the range:
                    - we know start will be the min
                    between current index start and
                    newInterval start
                    - end will be max of currentIndex end
                    and newInterval end
    C: 
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int numIntervals = intervals.length;

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            // newInterval end does not overlap with current index start
            // so append result
            if (newInterval[1] < intervals[i][0]){
                result.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                return result.toArray(new int[numIntervals][2]);
            } 
            // newInterval start greater than current index end
            // so it doesn't overlap and we know current index
            // then occurs here, as its in order
            else if (newInterval[0] > intervals[i][1]) {
                result.add(intervals[i]);
            } 
            // an overlap! so we must merge merge newInterval with 
            // current interval
            else {
                newInterval = new int[] {
                    Math.min(newInterval[0], intervals[i][0]),
                    Math.max(newInterval[1], intervals[i][1])
                };
                numIntervals--;
            }
        }
        result.add(newInterval);

        return result.toArray(new int[numIntervals][2]);
    }
}