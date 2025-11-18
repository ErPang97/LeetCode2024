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
        - given 
            - a ListNode 'head' head of the linked list
            - an int k
                - k at a time? meaning, reverse k nodes, and then continue?
        - return the modified list (I assume the new head)
        - restrictions:
            - 0 < k <= len linkedList <= 5000
            - 0 <= node.val <= 1000

    E:
        - difficult to understand at first for sure!
        - we want to group the nodes into groups of size k, noting that no nodes
        are repeated in these groups, and that the order is the same
        - if a group isn't big enough, ie, the ending group can't be made in size k,
        then we don't reverse it
    
    D:
    - want to achieve O(1) memory in the most optimal 

    A:
    - observations: 
        - for every k nodes, we know that the new head
        of the group k is the last node in the original ordering
        - we also know that the original head of the group is the new last node
        and should point to the beginning of the next group
        - after reordering that next group, the last head of the previous group 
        should be updated as well
    - we may want to define a generic, reverse method that takes a node and reverses the
    k nodes after it
    k entries in the list
    - Brute Force Idea 1: using an ArrayList?
        - a naive idea would be to maybe create a list of the nodes
        - from there, we would simply reverse every k nodes in that 
        array list
        - then, we could iterate through the original array list
        and update the next pointers of each node
        - however, this can't accomplish O(1) extra memory space for sure
    - Idea 2: 
        - pointers! review for sure
    C:
    - 
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // dummy node to keep track of the head
        // which is the next after this node
        ListNode dummy = new ListNode(0, head);
        // keep track of the node previous to the
        // the current group
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            // means that the group isn't big enough, so leave as is
            if (kth == null) {
                break;
            }
            // looks at one node right after the current group
            ListNode groupNext = kth.next;

            // reverse group
            ListNode previous = kth.next;
            ListNode current = groupPrev.next;
            while (current != groupNext) {
                ListNode temp = current.next;
                current.next = previous;
                previous = current;
                current = temp;
            }

            // kth was last, now
            // we want kth to be the first in our group now

            // initially temp stores the first node in our group
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            // now we want it to be the last node
            groupPrev = temp;
            
        }

        return dummy.next;

    }

    /**
     get the kth node from the current list node
    */
    public ListNode getKth(ListNode current, int k) {
        while (current != null && k > 0) {
            current = current.next;
            k -= 1;
        }
        return current;
    }
}