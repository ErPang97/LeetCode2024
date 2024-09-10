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
    public boolean isBalanced(TreeNode root) {
        /**
            - P: 
            - E:
            - D:
            - A:
                - start a the root, and if root == null
                - the tree is balanced
                - if not null, compare the abs difference between the 
                heights of the right and left subtrees. If greater than 1,
                return false 
                - then check the right and left subtrees if they are balanced
                - the height is calculated via recursion, incrementing height 
                by 1, as we go down the tree
            - C:
         */

        boolean isBalanced = false;

        // given a root, return true
        if(root == null){
            isBalanced = true;
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if(Math.abs(leftHeight - rightHeight) > 1){
                isBalanced = false;
            } else{
                isBalanced = true;
            }

            boolean isLeftBalanced = isBalanced(root.left);
            boolean isRightBalanced = isBalanced(root.right);

            if(isLeftBalanced == false || isRightBalanced == false){
                isBalanced = false;
            }
        }   

        return isBalanced;
    }

    /**
        helper function for finding height given a node
        using recursion here
     */
    public int height(TreeNode root) {

        int height = 0;

        if(root == null) {
            return height;
        } else {
            height++;
            return height + Math.max(height(root.left), height(root.right));
        }
    }

}