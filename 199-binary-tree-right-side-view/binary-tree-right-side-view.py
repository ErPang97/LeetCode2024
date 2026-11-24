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
            - a root -> TreeNode representing the very root of a binary tree
        - Want:
            - to return the values of the nodes from top to bottom, as if we were looking
            at the right side of the tree
        - Constraints:
            - anywhere from [0, 100] nodes in the tree
            - -100 <= Node.val <= 100
    E:
        - the examples are pretty clear
    D:
        - we may want to use a stack or queue depending on the tree traversal algorithm
    A:
        - Idea 1:
            - Observation: if we can obtain the nodes of the tree and turn into a list of lists, adding 
            top to bottom, where each level is a list, and the values of a node in a level are stored in
            that list from left to right we know for sure that the value at the right end is the node 
            that would be visible from the right side
            - Assuming that this is a valid binary tree, we may be able to assume that the value
            at the right is also the max value at that level
            - do we need a list of lists howvever? Can we simplify using a map, perhaps mapping
            from level to the right most node?
            - if we proceed down the list starting with the left most nodes, and 
            replace with the values that appear to the right, we should be able to store
            the right most node pretty easily
            - we can also use recursion here  

            ALGO:
                - traverse from the top, starting at level 0, and root
                - def recursive_function(node, level, right_map)
                    - if node is null
                        - return
                    right_map[level] = node.val
                    recursive_function(node.left, level + 1, right_map)
                    recursive_function(node.right, level + 1, right_map)
                - level = 0
                - right_map = {}
                - recursive_function(root, level, right_map)
                - max_depth = max(right_map.keys)
                - result = []
                - for i in range(max_depth):
                    - result.append(right_map[i])
    C:
    """
    def rightSideView(self, root: TreeNode | None) -> list[int]:
        # maps from level in tree to current right most node
        right_most = {}
        level = 0
        result = []
        # base case:
        if not root:
            return result

        def recursion(node, level, right_map):
            if not node:
                return
            right_map[level] = node.val
            recursion(node.left, level+1, right_map)
            recursion(node.right, level+1, right_map)
        
        recursion(root, level, right_most)
        max_depth = max(right_most.keys())
        for i in range(max_depth+1):
            result.append(right_most[i])
        return result