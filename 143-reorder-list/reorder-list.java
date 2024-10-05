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
    public void reorderList(ListNode head) {
        // P:
        /*
         * point i to length - 1 - i, 
         * up to the halway point basically
         */
        // E:
        // D: no extra
        // A:
        /*
         * USe a slow and fast pointer to find the midpoint
         of the linkedlist. 
         - Slow moves by 1, and fast moves by 2
         - second half of the list, begins on slow.next after iteration
         - then reverse the second half of the list, and merge the
         - original list and the second half together
         - alternating with original -> second half
         */
        // C:

        ListNode slow = head;
        ListNode fast = head.next;

        // find the midpoint of the list
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // midpoint is the next node after slow reaches its end
        // head of second half:
        ListNode second = slow.next;
        // set the next after the slow pointer to null, as 
        // this node is the end of the LinkedList
        slow.next = null;

        // reverse second half of list:
        ListNode currentSecond = second;
        ListNode prev = null;
        while(currentSecond != null) {
            ListNode next = currentSecond.next;
            currentSecond.next = prev;
            prev = currentSecond;
            currentSecond = next;
        }

        // merge two halves
        ListNode first = head;
        second = prev;
        while(second != null){
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }

    }
}