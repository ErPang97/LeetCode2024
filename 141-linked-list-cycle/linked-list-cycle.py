# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    """
    P:
        - Given:
            - a class ListNode
                - each node stores a value, and 
                also a pointer to the node that follows it in the LinkedList
            - in our function, we are given a parameter
                - head -> ListNode representing a node in the linkedList, namely the first node (head) in the list
        - Want:
            - bool -> true if there is a cycle, false otherwise
        - Constraints;
            - number of nodes in list is in range [0, 10^4]
            - -10^5 <= Node.val <= 10^5 
            - position is -1, or a valid index in the linked-list
    E:
        - Ex1 makes sense as there's an edge from the last node (-4) -> (2) allowing
        one to "walk" from (2) -> (0) -> (-4) and back to (2)
        - Ex2 is straightforward as well, as (2) -> (1) -> (2)
        - Ex3, as there's only one node, and no pointer, it is for sure no cycle
    D:
        - no DS needed
        - though we may be able to use a set() to possibly store visited nodes
        as another alternative solution
    A:
        IDEA 1:
        - to handle this problem, we want to use the concept of slow and fast pointers
        - what we mean by this, is at each iteration of some loop, the slow pointer updates
        only once, and the faster pointer updates next pointer twice
        - the idea behind this, is that if there's a cycle, at some point, both pointers will
        point to the same node. 
            - if we had used the same speed of updates, we would never find a case where both pointers end
            up on the same node, unless we start them on the same node, which is not really helpful in this case

        - Algorithm:
            - BC:
                - if not head:
                    - return false (empty list)
            - slow = head 
            - fast = head
            - while fast:
                - fast = fast.next
                - if fast.next:
                    - fast = fast.next
                - else:
                    - return false (no cycle as end of list already with no valid net pointer)
                - slow = slow.next
                - if fast == slow:
                    return true
            - return false
    """
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        # base_case:\
        if not head:
            return False
        slow = head
        fast = head
        while fast:
            fast = fast.next
            if fast:
                fast = fast.next
            else:
                return False # we cannot update anymore, meaning end of list, so return false as no cycle detected
            slow = slow.next
            if fast == slow:
                return True
        return False