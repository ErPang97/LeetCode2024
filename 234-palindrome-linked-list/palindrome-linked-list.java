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
    public boolean isPalindrome(ListNode head) {
        // P:
        // E:
        // D: perhaps a stack for the simple O(n) space method
        // A: 
        /**
        // for simple O(n) solution:
            - iterate through the linked list once and add the val
            of each node to the stack
            - iterate again through the linekdlist
                - pop the val from the stack
                - if val != current.val return false
            - return true 

        // for the O(1) space
            - iterate left to right of LL, and determine length of LL
            - iterate again reversing the first half of the nodes, and 
            - finally, compare the reversed half to the latter half
            returning false when not equal
         */
        // C:

        ListNode current = head;
        Stack<Integer> stack = new Stack<>();
        while(current != null){ // add elements to stack
            stack.push(current.val);
            current = current.next;
        }

        current = head;
        while(current != null){
            if(stack.pop() != current.val) return false;
            current = current.next;
        }

        return true;
    }
}