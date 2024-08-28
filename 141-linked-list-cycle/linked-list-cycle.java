/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // P: if there is a cycle, return true
        // else return false
        // E: 
        // D: pointers, no extra DS's (alt. use HashTable 
        // and simply see if there are seen nodes already)
        // A:
        /**
            - use two pointers, one is updated once to the next node
            the other is updated twice next (if null hits however, return False)
            - if they're ever equal return True

         */

        if(head == null) return false;
        if(head.next == null) return false;

        ListNode plusOne = head.next;
        ListNode plusTwo = plusOne.next;
        if(plusOne == null || plusTwo == null) {
            return false;
        }

        while(plusOne != null && plusTwo != null){
            if(plusOne == plusTwo) {
                return true;
            } else {
                plusOne = plusOne.next;
                plusTwo = plusTwo.next;
                if (plusTwo == null){
                    return false;
                } else {
                    plusTwo = plusTwo.next;
                }
            }
        }

        return false;
    }
}