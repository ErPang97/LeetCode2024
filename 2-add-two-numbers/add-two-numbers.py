# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    """
    P:
    - Given: 
        l1 -> ListNode head of the first linkedlist
        l2 -> ListNode head of the second linkedlist
        - these represent two non-negative integers 
            - they are stored in reverse order
            - each node contains a single digit
    - Want:
        - add the two numbers, and return the sum as a linkedlist (likely, we want to return the head
        of the list, and of course store in reverse order)
    - Some important things:
        - the lists may or may not be of the same length as evident by Ex3.
    - Constraints:
        - num_nodes can be in range [1, 100] -> def overflow can happen
        - 0 <= Node.val <= 9
        - list will not have leading zeros (meaning head cannot be a zero valued node)
    E:
    - Ex1:
        - overall relatively clear
        (2) -> (4) -> (3)
        +
        (5) -> (6) -> (4)
        =
        (7) -> (0) -> (8)
        - for the second digit, we had to carry over the resulting sum, and add it to
        the following digit in the linkedlist
    D:
        - probably don't need any extra DS's
    A:
        - Brute Force:
            - the simplest idea would probably be to actually calculate that the two numbers
            are in integer Form. 
            - 1's place = val * 10^0
            - 10's place = val * 10^1 
            ...
            - 10^n's place = val * 10^(n-1) 
            - so computing this isn't too difficult, but we may run into an issue regarding overflow
            - we'd just have to reverse the list and then proceed, then iterate through a string
            variant of the result int, and store that into a linkedlist
        - However, is there a simpler way? One that is guaranteed to avoid running into any sort of overflows?
            - what if we compute the sum dynamically, adding nodes as we evaluate each list's sum
            - idea: use two pointer's to evaluate the sum of the values at two nodes, at a given place
            - ALGO:
                - init two pointers first, and second for the second list
                - populate the shorter of the two lists, with 0's at the end
                - init a dummy starter node
                - current = dummy
                - carry = 0
                - while first_ptr is not Null:
                    - val = first_ptr.val + second_val.ptr + carry
                    - carry = 0 # reset the carry val
                    - if val >= 10:
                        - carry = 1 # this val must be added to the next node
                        - val = val%10 # get the remainder and store that as val
                    - ListNode next = ListNode(val)
                    - current.next = next
                    - current = current.next
                - return dummy.next
        - ADDENDUM, we cannot populate the shorter of the two nodes, so we need to 
        avoid doing that and simply iterate through the longer node
    C:
    """
    def addTwoNumbers(self, l1: ListNode | None, l2: ListNode | None) -> ListNode | None:
        first = l1
        second = l2
        
        
        len_first = 0
        while first:
            len_first+=1
            first = first.next
        len_second = 0
        while second:
            len_second +=1
            second = second.next

        if len_first > len_second:
            shorter = l2
            longer = l1
        else:
            shorter = l1
            longer = l2

        # to easily recall the head of the result node
        dummy = ListNode()
        current = dummy
        carry = 0 # the value that must be carried over
        while longer:
            val = 0
            if shorter:
                val += shorter.val
                shorter = shorter.next

            val += longer.val + carry
            carry = 0 # reseting the carry over val
            if val >= 10:
                val = val%10
                carry = 1
            current.next = ListNode(val)
            current = current.next
            longer = longer.next
        if carry == 1: # if at end of list, and still have a carry, create new node
            current.next = ListNode(carry)

        return dummy.next