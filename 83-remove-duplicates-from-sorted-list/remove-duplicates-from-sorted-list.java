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
    - given a SORTED LinkedList
    - want to remove duplicates from it
    - return the sorted linkedlist st that each element only appears once
    E:
    - in the first example, they removed the 1, as there are 2 1's
        - 1 -> 1 -> 2
        - 1 -> 2
    - in example 2, there are 2 1's and 2 3's so both are removed
        - 1 -> 1 -> 2 -> 3 -> 3
        - 1 -> 2 -> 3
    D:
    - we could use a set to keep track of already seen numbers, but
    this seems excessive in terms of space
    A:
    - we probably want to use a pointer to keep track of previous and current nodes
    - if current node holds same val as prev node, we update prev node next
    to the value of the current next

    - node prev = head
    - node current = prev.next

    - while current.next != null :
        // update prev when current is the same
        - if current.val == prev.val:
            prev.next = current.next
        current = current.next

    - update, fix as some logic there, but not quite right execution
    - we consider the base case when head is null or head.next is null:
        in either case return head
    - we use two pointers, next which keeps track of the next node
    and prev, which keeps the current node
        if prev.val == next.val
            set prev.next to next.next, skipping the original next
            set next to next.next
        else, no duplicates found, 
            just update both pointers up by 1
    C:
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;
   
        ListNode next = head.next;
        ListNode prev = head;

        while(next != null){
            if (prev.val == next.val) {
                // duplicate found, skip the next node
                prev.next = next.next;
                next = next.next;
            } else {
                // move both pointers forward
                prev = prev.next;
                next = next.next;
            }
        }
        return head;
    }
}