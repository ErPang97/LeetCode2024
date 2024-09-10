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
    public int maxDepth(TreeNode root) {
        // P:
        // E:
        // D: none
        // A: 
        /**
            - pretty straightforward algorithm
            - use recursion to gradually go down
            - if the root node is null, we 
                return 0
            - else, we check the depth of the
              right and left subtrees
              and choose the depth that is greater
              and add it to the current depth
            - return that depth
         */

        int depth = 1;

        if(root == null) {
            return 0;
        } else {

            return depth + Math.max(
                (maxDepth(root.left)), (maxDepth(root.right))
            );

        }

    }
}