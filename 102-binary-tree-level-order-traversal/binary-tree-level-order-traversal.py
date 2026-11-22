# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    """
    P:
        - Given:
            - root -> a TreeNode serving as the root of a binary tree
        - Want:
            - to return a list of list of ints that represents the
            level order traversal of its nodes' values 
            - this means left to right, then level by level
            - based on the examples, each level will be captured in a list, and the values
            in that level are the ints of each node
    E:
        - example 1:
            - level 1: 3
            - level 2: 9, 20
            - level 3: 15, 7
        - result:
            - [ [3], [9, 20], [15, 7]]
        - it makes sense 
    D:
        - may need a queue or stack to keep track of nodes in queue
        - may also try to use a map to map level to list of nodes
    A:
        - IDEA 1:
            - use either BFS or DFS and use an int to keep track of depth
            in tree
            - if we use recursion, we could start at the root
            and map root [0] -> [root.val]
            - then we check pass the running map to the children to 
            map [1] -> [left_child.val] and [1] -> [right_child.val]
            and continue the pattern

        - IDEA 2:
            - use tuples when iterating through the tree (depth, node)
            and store in the list that way
    C:
    """
    def levelOrder(self, root: TreeNode | None) -> list[list[int]]:
        node = root

        if not node:
            return []

        result = []
        result_map = {}
        def recursion_update(node, result_map, depth):
            # stop recursion if None
            if not node:
                return

            if depth in result_map:
                result_map[depth].append(node.val)
            else:
                result_map[depth] = [node.val]
            
            recursion_update(node.left, result_map, depth+1)
            recursion_update(node.right, result_map, depth+1)

        recursion_update(node, result_map, 0)
        biggest_depth = max(result_map.keys())

        for i in range(biggest_depth+1):
            result.append(result_map[i])
        return result