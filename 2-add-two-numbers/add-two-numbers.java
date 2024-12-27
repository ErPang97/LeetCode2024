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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // P:
        // E:
        // D:
        // A:
        /**
        - while l1 or l2 are not null:
            - create new node, and store in it, the val
            of the sum of l1 and l2 values (either are 0 if null)
            (noting that there is possible carry over)
            - point the previous node to this new node
            - update both l1 and l2 to their next value
        - at the end, do one more check on the carry over and add
        it as a new node if not 0
         */
        // C:


        ListNode sumOfNums;
        if(l1 == null ){
            return l2;
        } else if(l2 == null) {
            return l1;
        }
        
        ListNode previous = null;
        ListNode head = null;
        int carryOver = 0;

        int l1Val = 0;
        int l2Val = 0;

        while(l1 != null || l2 != null){

            if(l1 != null){
                l1Val = l1.val;
            } else {
                l1Val = 0;
            }

            if(l2 != null){
                l2Val = l2.val;
            } else {
                l2Val = 0;
            }

            int sumOfVals = l1Val + l2Val + carryOver;

            if(sumOfVals >= 10){
                carryOver = 1;
                sumOfVals = sumOfVals - 10;
            } else {
                carryOver = 0;
            }

            sumOfNums = new ListNode(sumOfVals);
            if(previous != null){
                previous.next = sumOfNums;
            } else {
                head = sumOfNums;
            }
            previous = sumOfNums;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carryOver != 0){
            previous.next = new ListNode(carryOver);
        }

        return head;
    }
}