# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    """
    P: Given:
        - root -> a TreeNode represnting the root of a binary tree
       Want:
        - to invert the tree
            - based on the observation, it seems that inverting the
            tree means to set the right node, to the left node and vice versa
            for each node in the tree
        - return the root
        
    E:
        - the examples seem relatively clear
    D: 
        - we could try using a queue or stack to help decide what nodes to proceed
        to next
        - we can also use a set to keep track of the nodes that we already inverted
    A:
        - the most straightforward approach that I can think of is to start at the root
        - while not empty:
            - at current node 
                - temp = self.left
                - set self.left = self.right
                - set self.right to temp (original self.left)
                - add both nodes to a queue/stack 
            - queue =  top node from queue
    """
    def invertTree(self, root: TreeNode | None) -> TreeNode | None:
        stack = []
        stack.append(root)
        while stack:
            current = stack.pop()
            if current:
                temp = current.left
                current.left = current.right
                current.right = temp
                stack.append(current.left)
                stack.append(current.right)
        return root        