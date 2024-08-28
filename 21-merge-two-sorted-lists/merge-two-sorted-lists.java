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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // P: merge list values together in sorted increasing order
        // E:
        // D: linkedLists
        // A:
        /** 
            if either are null, return the other
            if list1 == null: return list2
            if list2 == null: return list1

            if list1.val > list2.val
            - set mergedListHead to list2
            else
            - set mergedListHead to list1
            mergedList = mergedListHead

            two pointers, for list1 and list2
            while list1 != null || list2 != null:
            - if list1.val < list2.val:
              - mergedList.next = list1
              - list1 = list1.next
            - else 
              - mergedList.next = list2
              - list2 = list2.next
            - mergedList = mergedList.next
         */

        if(list1 == null){
            return list2;
        } else if(list2 == null){
            return list1;
        }

        ListNode mergedListHead; // start with the smaller of the two lists

        if (list1.val > list2.val) {
            mergedListHead = list2;
            list2 = list2.next;
        } else {
            mergedListHead = list1;
            list1 = list1.next;
        }

        // keeps track of current spot in mergedList
        ListNode mergedList = mergedListHead;
    
        while(list1 != null || list2 != null) {
            if(list1 != null && list2 != null){
                if (list1.val > list2.val) {
                    mergedList.next = list2;
                    list2 = list2.next;
                } else if(list2.val >= list1.val){
                    mergedList.next = list1;
                    list1 = list1.next;
                }
            } else if(list1 == null){
                mergedList.next = list2;
                list2 = list2.next;
            } else {
                mergedList.next = list1;
                list1 = list1.next; 
            }
            mergedList = mergedList.next;
        }

        return mergedListHead;
    }
}