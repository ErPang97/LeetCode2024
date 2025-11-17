/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
    P:
    - given: a list of linked-lists, lists
    of size k
    - want to return a single linkedlist that
    is sorted
    - seems similar to sorting two, but generalized to k
    E:
    - the examples are pretty clear!
    D:
    - priority queue?
    A:
    Initial Algorithm Idea:
    - a brute force algorithm would be to maintain
    k different pointers for each linkedlist, 
    and for each linked list, we compare across the k current pointers
    and find the minimum
    - the minimum would then be added to the current return linkedlist
    and we would update the pointer of the list that it came from
    - then we would repeat, until all the linkedlists have been used up
    - let n be the maximal length linked list; we would have to iterate through
    all n items eventually for that list, and that would have to be repeated
    k times for each linkedlist, so this appears to be O(k*n)?

    Could we improve upon this?
    - another idea is to simply iterate through the list of LinkedList:
    suppose we have list i, and list i+1
    - set the initial list to merge as i = 0 (before looping)
    - we merge the current list to that of i+1, and then increment i by 1
    - then udpate current list to the current i, and repeat until we
    have merged all the lists
    C:
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // base cases: when list length is 0:
        int k = lists.length;
        if (k == 0) { // just return the list
            return null; 
        }

        // base cases: when lists is length 1:
        if (k == 1) {
            return lists[0];
        }

        for (int i = 1; i < lists.length; i++) {
            lists[i] = mergeTwoLists(lists[i], lists[i - 1]);
        }
        return lists[lists.length - 1];
    }

    /**
     Helper function to merge two different lists
     */
    public ListNode mergeTwoLists(ListNode nodeOne, ListNode nodeTwo) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (nodeOne != null && nodeTwo != null) {
            if (nodeOne.val <= nodeTwo.val) {
                current.next = nodeOne;
                nodeOne = nodeOne.next; // update 
            } else { // if (nodeTwo.val < nodeOne.val) 
                current.next = nodeTwo;
                nodeTwo = nodeTwo.next; // update
            }
            current = current.next;
        }

        if (nodeOne != null) {
            current.next = nodeOne;
        } else {
            current.next = nodeTwo;
        }

        return dummy.next;
    }
}