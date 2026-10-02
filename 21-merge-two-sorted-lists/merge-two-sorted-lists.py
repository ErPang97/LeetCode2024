# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    """
    P:
    - given:
        - list1 -> ListNode, head of the first list
        - list2 -> ListNode, head of the second list
    - want: 
        - merge the two lists together and 
        RETURN the head (ListNode) of the merged linked list
    - constraints:
        - number of nodes is between [0, 50]
        - -100 <= Node.val <= 100
        - both list1 and list2 are sorted in non-decreasing order
    E:
    - Example 1: 
        - (1) -> (2) -> (4)
        - (1) -> (3) -> (4)
        - (1) -> (1) -> (2) -> (3) - (4)
        - pretty clear
    - Example 2, an empty list returns an empty list
    - Example 3, an empty list and a non-empty list should return the non-empty list
    D:
    - a BruteForce approach might use a list and sorting by value
    - but, we shouldn't need any extra DS's
    A:
    - IDEA 1:
        - use two pointers, one that points to the nodes in one list
        - and one that points to the nodes in the other list
        - main idea is, because we know this is sorted, we can take a look
        at the two lists and compare the nodes that the two pointers are pointing at
        - if we compare the value, we can make a decision on which node to add to our resultant list
        first
        - if one is less than or equal to the other, add that node to the resultant list, and update
        its pointer to the next node in the list

        - ALGO
            - pointer_1 = list1
            - pointer_2 = list2
            - result = the node between pointer_1 and pointer_2
                that stores the lesser of the two values
            - while pointer_1 or pointer_2 has values:  
                - if one of them has no values, simply add the other node to the list
                and update the pointer 
                - compare value stored at pointer_1 to pointer_2 
                    - whichever is less, update the result to point to the less value
                    - update the pointer
            - return result
        - However, we must consider the base cases where one or both of the lists are empty
    C:
    """
    def mergeTwoLists(self, list1: ListNode | None, list2: ListNode | None) -> ListNode | None:
        pointer_1 = list1
        pointer_2 = list2
        # base case 1: both lists are empty
        if not pointer_1 and not pointer_2:
            return None

        # base case 2: one list is empty
        if not pointer_1:
            return pointer_2
        elif not pointer_2:
            return pointer_1

        # initiate the head which is guaranteed to be the lesser of the two non-null values
        if pointer_1.val <= pointer_2.val:
            head = pointer_1
            pointer_1 = pointer_1.next 
        else:
            head = pointer_2
            pointer_2 = pointer_2.next 

        current = head
        while pointer_1 or pointer_2:
            # consider when both are not empty
            if pointer_1 and pointer_2:
                if pointer_1.val <= pointer_2.val:
                    current.next = pointer_1
                    pointer_1 = pointer_1.next
                else:
                    current.next = pointer_2
                    pointer_2 = pointer_2.next
            elif pointer_1:
                current.next = pointer_1
                pointer_1 = pointer_1.next
            else: # must be pointer_2 so update the list using the remaining values there
                current.next = pointer_2
                pointer_2 = pointer_2.next
            current = current.next
        return head
                    
        