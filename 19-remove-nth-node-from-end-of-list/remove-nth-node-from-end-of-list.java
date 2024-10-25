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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // P:
        // E:
        // D: none extra
        // A:
        /**
         * two pointers
         * add a dummy node to the beginning of the list
         * set left pointer to dummy node
         * set right pointer to n spaces after the original head
         * while(right != null)
         *    right = right.next
         *    left = left.next
         * left.next = left.next.next
         */
        // c:

        // one edge case when the size of the head == n == 1
        if(n == 1 && head.next == null) return null;

        ListNode left = new ListNode();
        left.next = head;
        ListNode right = head;
        for(int i = 0; i < n; i++){
            right = right.next;
        }

        while(right != null){
            right = right.next;
            left = left.next;
        }

        // edge case when head is removed
        if(head == left.next) {
            head = left.next.next;
        }
        left.next = left.next.next;

        return head;
    }
}