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
    public boolean isValidBST(TreeNode root) {
        // P:
        // E:
        // D: one type of queue to handle DFS or BFS depending
        // A:
        /**
        - we solve this using recursive DFS
        - initialize left-most and right-most possible value for node as negative and positive infinity
        - starting at the root, check that it is valid (within negative and positive infinity)
        - check the left child, updating the right-most as the value of its parent
        - do the same with the right child, updating the left-most pointer to the
        value of its parent
        - for the recursive function, if the node is null return true,
        otherwise check that the value of the node is strictly between the value
        of the left and the right-most values and return false if not

        algorithm:
        
        left = -infinity
        right = infinity

        node current = root
        return isValid(root, left, right)

        def isValid(node, left, right)
            - if node == null  
                return true
            - else 
                if node.val < left || node.val < right
                    return false

                // check if the left side is valid
                validLeft = isValid(node.left, left, node.val) 
                // check if right side is valid
                validRight = isValid(node.right, node.val. right)

                return validLeft && validRight
         */
        // C:
        long left = Long.MIN_VALUE;
        long right = Long.MAX_VALUE;

        return isValid(root, left, right);
    }

    public boolean isValid(TreeNode node, long left, long right){
        if(node == null){
            return true;
        } else {
            if(node.val <= left || node.val >= right){
                return false;
            }
            // check if the left side is valid (update right pointer)
            boolean validLeft = isValid(node.left, left, node.val);

            // check if right side is valid (updarte left pointer)
            boolean validRight = isValid(node.right, node.val, right);
            return validLeft && validRight;
        }
    }

}