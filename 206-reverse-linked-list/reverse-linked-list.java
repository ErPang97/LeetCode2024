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
    public ListNode reverseList(ListNode head) {
        // P: a classic, reverse a linkedList, where the head, is 
        // now the tail, and vice versa
        // E: 
        // D: none extra needed
        // A: starting from the head,
        // save current node to the next after head
        // set previous node to head
        // and set previous's next' = null
        // while current.next != null:
        // - update nextNode = current.next
        // - change current.next to previous
        // - update previous to current
        // - update current to nextNode
        // current.next = previous
        // return current
        
        if(head == null) return head;
        if(head.next == null) return head;

        ListNode current = head.next;
        ListNode previous = head;
        previous.next = null;

        while(current.next != null){
            ListNode nextNode = current.next;
            current.next = previous;

            previous = current;
            current = nextNode;
        }

        current.next = previous;

        return current;
        
    }
}