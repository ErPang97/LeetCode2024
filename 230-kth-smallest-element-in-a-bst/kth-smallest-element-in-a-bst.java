/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // P:
        // E:
        // D:
        /**
        - we use the stack to keep track
        of visited nodes, 
         */
        // A:
        /**
        - essentially we want to do an in-order traversal
        - to do this, we try to follow the left subtree as far as 
        left as possible, as we know this reaches the minimum
        - then, if that minimum node has a right subtree, we explore that
        and again proceed left of that tree if possible
         */
        // C:

        int n = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // explore the left subtree, proceeding as
            // far left as possible, adding each node to the stack
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            // once we reach the left most, proceed to its right children
            current = stack.pop();
            n++;
            if(n == k) return current.val;
            current = current.right;
        }
        return current.val;
    }
}