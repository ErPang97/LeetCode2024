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
            - root -> a TreeNode, representing the main root of a tree
            - subRoot -> a TreeNode, representing a possible sub root
            of tree
        - Want:
            - bool -> whether or not the node at subRoot is a subtree
            of the tree rooted at root
    E:
        - Example 1:
            - example 1 makes sense
        - Example 2: 
            - also makes sense, in particular, the node 2 has a child in
            the main tree, but not in the alleged subroot
    D:
        - likely a stack or queue is sufficient to keep track of the next nodes
        to visit
    A:
        - Core Idea is relatively simple
            - start at the root node and go down the tree until we
            find the start of subroot
            - then, check if the structure is exactly the same from that node
            on by checking all the nodes in both trees
            - if at any point, there is no shared note, stop

        - Addendum:
            - there are cases, where nodes in the tree can share repeat values. Instead,
            what if we treated every node as if it was a potential subroot
            - rather than looking for a specific val, pretend every node was a possible subroot
    C:
    """
    def isSubtree(self, root: TreeNode | None, subRoot: TreeNode | None) -> bool:

        tree_dfs = [root]

        while tree_dfs:
            possible_sub = tree_dfs.pop()

            # main_stack = [possible_sub]
            # sub_stack = [subRoot]

            if isSameTree(possible_sub, subRoot):
                return True


            # is_root = True
            # while main_stack and sub_stack:
            #     main_current = main_stack.pop()
            #     sub_current = sub_stack.pop()

            #     # if vals aren't the same, ignore as possible root
            #     if main_current.val != sub_current.val:
            #         is_root = False
            #         break
            #     # has a left child in main, but not the sub
            #     if (main_current.left and not sub_current.left) or (not main_current.left and sub_current.left):
            #         is_root = False
            #         break
            #     # has a right child in main, but not the sub
            #     if (main_current.right and not sub_current.right) or (not main_current.right and sub_current.right):
            #         is_root = False
            #         break

            #     if main_current.left:
            #         main_stack.append(main_current.left)
            #         sub_stack.append(sub_current.left)
            
            #     if main_current.right:
            #         main_stack.append(main_current.right)
            #         sub_stack.append(sub_current.right)

            # if sub_stack:
            #     break
            
            # if len(main_stack) == len(sub_stack) == 0 and is_root:
            #     return True

            
            if possible_sub.left:
                tree_dfs.append(possible_sub.left)        
            if possible_sub.right:
                tree_dfs.append(possible_sub.right)

        return False

def isSameTree(root: TreeNode | None, subRoot: TreeNode | None) -> bool:


    if (root and not subRoot) or (not root and subRoot):
        return False

    # base case, both null:
    if not root and not subRoot:
        return True

    if root.val != subRoot.val:
        return False

    return isSameTree(root.left, subRoot.left) and isSameTree(root.right, subRoot.right)