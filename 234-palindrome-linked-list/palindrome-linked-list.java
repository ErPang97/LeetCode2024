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

        int length = 0;
        ListNode current = head;
        while(current != null){ // add elements to stack
            length++;
            current = current.next;
        }

        current = head;
        ListNode prev = null;
        for(int i = 0; i < length/2; i++){

            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }

        ListNode revHead = prev;
        ListNode currentCopy;
        if(length % 2 != 0){
            currentCopy = new ListNode(current.val, revHead);
            revHead = currentCopy;
        }

        ListNode currentRev = revHead;
        
        if(length % 2 == 0)
            for(int i = 0; i < length/2 ; i++){
                System.out.println("Rev: " + currentRev.val + " Forward: " + current.val);
                if(currentRev.val != current.val) return false;
                currentRev = currentRev.next;
                current = current.next;
            }

        else {
            for(int i = 0; i < length/2 + 1 ; i++){
            System.out.println("Rev: " + currentRev.val + " Forward: " + current.val);
            if(currentRev.val != current.val) return false;
            currentRev = currentRev.next;
            current = current.next;
        }
        }

        return true;
    }
}