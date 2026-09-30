# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    """
    P:
        - Given 
            - a class ListNode
            - in the function paramter, we're given the head a LinkedList
                - recall a linkedlist is a collection of nodes that have pointers to another
                node, in some ordered manner 
                - for example, lets say we have 3 nodes, A, B and C.
                    - A can point to B, and B can point to C : like A->B->C
            - head is a ListNode
        - Want:
            - to reverse the LinkedList, which means, we want to reverse the pointers in each node
            s.t., the original tail is the new head, the head is the new tail, and each interior node
            points to the node that came before it, in the list
            - want to return the reversed list, meaning the new head
        - Constrains:
            - 0 <= num_nodes <= 5000
            - -5000 <= node.val <= 5000
    E:
        Ex1: 
            1 -> 2 -> 3 -> 4 -> 5
            reversed:
            5 -> 4 -> 3 -> 2 -> 1
        Ex2:
            1 -> 2
            reversed:
            2 -> 1
    D:
        - no extra DS is needed
    A:
        - iterative method:
            - current_node = head
            - previous = null
            - while current_node has a next node:
                next_node = current_node.next
                current_node.next = previous_node # reverse the pointer
                previous_node = current_node
                current_node = next_node # make the next_node the new current_node

        - recursive solution:
            - start at the head node -> current
            - BC: if head is null
                - return head
            - get the next node
            - set current.next = previous
            - call recursively reverseList on the next node and return the result of that
    """
    def reverseList(self, head: ListNode | None) -> ListNode | None:
        
        ### iterative solution
        # # base case: empty linked_list
        # current = head
        # if not current:
        #     return None

        # previous = None
        # while current:
        #     next_node = current.next
        #     current.next = previous # reverse the pointer
        #     previous = current
        #     current = next_node # update current to the next node
        
        # return previous # at the end of the loop, we're one passed the tail, so return the tail

        ### recursive solution
        def recursion(current, previous):
            # base case
            if not current:
                return previous
            next_node = current.next
            current.next = previous # reverse the pointer
            previous = current
            return recursion(next_node, previous)
        return recursion(head, None)
        

