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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // P:
        // E:
        // D:
        // A:
            /**
            - if either is null, and not the other
                return false
            - else if both are null
                return true
            - if both don't have the same value
                return false
            - else
                check their right and left subtrees
                if they are the same
                if they aren't return false
                else return true
             */
        // C:
        boolean isSameTreeLeft = false;
        boolean isSameTreeRight = false;
        if(p == null && q != null){
            return false;
        } else if(p != null && q == null){
            return false;
        } else if(p == null && q == null){
            return true;
        }
        if(p.val != q.val){
            return false;
        } else {
            isSameTreeLeft = isSameTree(p.left, q.left);
            isSameTreeRight = isSameTree(p.right, q.right);
        }
        if(isSameTreeLeft && isSameTreeRight){
            return true;
        }
        return false;
    }
}