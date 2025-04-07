class Solution {
    /**
    P:
    - find the kth largest element in a given array
    - specifically without sorting
    - also not necessarily unique, just what is the kth largest element
    E:
    - easy to understand

    D:
    - a Heap likely is sufficient

    A:
    - add the elements to a priority queue, using reverse order
    - then, just pop off k times, with the last element being the k-th largest

    C:
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : nums) {
            maxHeap.add(i);
        }
        int kthLargest = 0;
        for (int i = 0; i < k; i++){
            kthLargest = maxHeap.poll();
            if (i == k - 1){
                break;
            }
        }
        return kthLargest;
    }
}