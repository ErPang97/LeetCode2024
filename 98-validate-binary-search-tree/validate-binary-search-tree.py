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
            - root -> TreeNode, representing the root of a binary tree
        - Want:
            - bool -> return True if it is a valid BST, and False otherwise
        - Recall:
            - left subtree of a node must contain only nodes with keys
            STRICTLY LESS THAN the current node's key
            - right subttree of a node contains only nodes with keys
            STRICTLY GREATER THAN the current node's key
            - Both left and right subtrees must also be binary search trees
    E:
        - the examples are pretty clear
    D:
        - we may want to use a stack or queue to visit all the nodes
    A:
        - IDEA 1:
            - since both the left and right subtrees must also be valid
            BST's, the parent node must also be a valid BST, if the MAX
            node on the left subtree is less than the Parent val, and the MIN
            in the right subtree is strictly greater than the Parent val
            
            - as we proceed down the tree, we'll want to somehow pass back up
            the max in the left subtree of a given node, and the min in the
            right subtree of a given node

            - thinking on recursion:
            - at a leaf node, this is of course a valid BST
            - if its not a leaf node, find_max in left subtree
            and find_min in right subtree
            if max_in_left < node.val < min_in_right:
                - this is still a valid subtree
            - but how to define, find_min and find_max?
                - if node is a leaf node -> min and max are itself
                - these vals are then returned to a non-leaf node
                - here, the parent of this node, compares the min
                at left and max at right, and returns either
                the min for find_min, and max for find_max
    C: 
    """
    def isValidBST(self, root: TreeNode | None) -> bool:
        
        def find_min(node):
            if not node:
                return float('inf') # use max_val
            else:
                min_at_left = find_min(node.left) if node.left else float('inf') # use max val 
                min_at_right = find_min(node.right) if node.right else float('inf') 
                return min(min_at_left, min_at_right, node.val)

        def find_max(node):
            if not node:
                return float('-inf')
            else:
                max_at_left = find_max(node.left) if node.left else float('-inf') # use max val 
                max_at_right = find_max(node.right) if node.right else float('-inf') 
                return max(max_at_left, max_at_right, node.val)
        
        # base case
        def isValidRecursion(node):
            if not node:
                return True
            else:
                max_at_left = find_max(node.left)
                min_at_right = find_min(node.right)
                if not (max_at_left < node.val < min_at_right):
                    return False
                return isValidRecursion(node.left) and isValidRecursion(node.right)

        return isValidRecursion(root)