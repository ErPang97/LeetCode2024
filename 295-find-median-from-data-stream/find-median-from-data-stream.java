/**
P:
- creating a class called MeanFinder
- it has a constructor MeanFinder()
that initializes the object
- we have a function addNum(int num) that
adds an integer num from the datastream to
this datastructure
- we have a function findMedian() that returns
a double that returns the medians of the elements thus far
- Recall:
    - a median is the mean of the two middle values,
    if the number of elements in consideration is 
    odd; otherwise, it is simply the middle number
E:
- the examples make sense
D:
- An Array could work if we had no constraint 
on time or memory space
- Heap/PriorityQueue to keep track of 
the middle two points in some form
A:

C:
 */

class MedianFinder {

    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    public MedianFinder() {
        // two heaps, large, small, minheap, maxheap respectively
        // heaps should be roughly equal size
        small = new PriorityQueue(Comparator.reverseOrder());
        large = new PriorityQueue();
    }
    
    public void addNum(int num) {
        small.add(num);

        // make sure every num in small is <=
        // to every num in large 
        if (small.size() !=0 && large.size() !=0 
        && small.peek() > large.peek()) {
            // pop from small and add to 
            // large
            int val = small.poll();
            large.add(val);
        }

        // size uneven? (len diff > 1)
        if (small.size() > large.size() + 1) { 
            // pop from small and add to 
            // large
            int val = small.poll();
            large.add(val);
        }
        if (large.size() > small.size() + 1) { 
            // pop from large and add to 
            // small
            int val = large.poll();
            small.add(val);
        }

    }
    
    public double findMedian() {

        if (small.size() > large.size()) {
            return small.peek();
        }
        if (large.size() > small.size()) {
            return large.peek();
        }
        return (small.peek() + large.peek())/2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */