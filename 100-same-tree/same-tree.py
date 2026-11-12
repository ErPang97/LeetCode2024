# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    """
    P:
        - Given 
            - p -> a root of one tree
            - q -> a root of another tree
                - both are binary trees
        - Want  
            - a bool -> True if same tree, False otherwise
                - this just means that they're structurally identical
                and that the values at each node are the same 
    E:
        - the examples are quite clear
        - Ex. 1 is straightforward
        - Ex. 2 even though the values at the nodes are the same, the tree
        on the left doesn't have a right child node, and the opposite is true for the other node
    D:
        - likely a Queue or Stack of some sort, to process the nodes in a specific order
    A:
        - IDEA 1:
            - start at the root node of both and compare the value at the node
                - if true, continue
            - then check, if both have either a left node and a right node
                - if structure different, obv. return False
                - else, add the left node first, then add the right node
            - pop the next node in both trees from the stack/queue and then compare values 
            once again
        - if at the end of the iteration, return True
    C:
    """
    def isSameTree(self, p: TreeNode | None, q: TreeNode | None) -> bool:

        # base case:
        if not p and not q:
            return True
        elif p and not q:
            return False
        elif q and not p:
            return False
        
        p_stack = [p]
        q_stack = [q]
        
        while p_stack and q_stack:
            p_current = p_stack.pop()
            q_current = q_stack.pop()

            # check val is the same
            if p_current.val != q_current.val:
                return False
            
            # then check if structurally the same
            if p_current.left and not q_current.left:
                return False # p has a left, but q does not
            elif q_current.left and not p_current.left:
                return False # similar but check q has a left, but p does not instead
            elif p_current.right and not q_current.right:
                return False # p has right, q does not
            elif q_current.right and not p_current.right:
                return False # q has right, p does not
            
            # add left and then right to the stack if exists
            if p_current.left and q_current.left:
                p_stack.append(p_current.left)
                q_stack.append(q_current.left)
            if p_current.right and q_current.right:
                p_stack.append(p_current.right)
                q_stack.append(q_current.right)

        return True