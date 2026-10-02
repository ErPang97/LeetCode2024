# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    """
    P:
    - Given: 
        - A ListNode head (the head of the list)
        - An int n -> the nth node from the END of the list that we wish to remove (inclusive)
    - Want:
        - To edit the linked list and remove the nth node from the list
        - Then, return the HEAD
    - To remove the node, is effectively, update the node prior to the node that we
      want to remove so that it points to the node, immediately after the node that was removed
    - Constraints:
        - number of nodes in the list is sz
        - 1 <= sz <= 30
        - 0 <= Node.val <= 100
        - 1 <= n <= sz
    E:
    - EX1 makes sense:
        - n = 2, means the second node away from the end, hence why (4) was removed
    D:
    - what if we used a map, to store the actual index of the nodes, using 1-index start
    - then we can simply find the node to remove, using the map, and update the pointers
    of the node prior to it
    A:
    - IDEA 1:
        - use a HashMap to store the index and map it to the node
        - then, using len(keys) - n + 1, we can get the node that is to be removed
        - finally, get the node prior to it, and update its next pointer, to the node after it
    - important Base Cases:
        - if removing the beginning of the list, can't update the previous pointer
            - simply update the head to the next val of the list and return that
        - if removing the end of the list, 
    C:
    """
    def removeNthFromEnd(self, head: ListNode | None, n: int) -> ListNode | None:
        index_to_node = {}
        i = 1
        pointer = head
        # map index to node
        while pointer:
            index_to_node[i] = pointer
            pointer = pointer.next
            i+=1 
        
        # by the end of the iteration, i is the number of nodes + 1
        index_to_remove = i - n
        
        # base case: head is to be removed
        if index_to_remove == 1:
            return head.next

        node_to_remove = index_to_node[index_to_remove]
        previous = index_to_node[index_to_remove - 1]
        previous.next = node_to_remove.next

        return head

        