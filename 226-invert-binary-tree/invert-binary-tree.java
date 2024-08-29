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
    public TreeNode invertTree(TreeNode root) {
        // P: essentially swap the left and right children for all nodes
        // that have it
        // E: in problem example
        // D: perhaps a PriorityQueue
        // A:
        /** - Starting from root, store this root in a queue
            - while queue not empty:
                - check if node has left and right children
                - if it does, swap left and right children
                - if it only has one, swap from left to right
                - or from right to left
                - add the children in the queue
            - return root
         */

        // init. priority queue:
        TreeNode invertedRoot = root;
        Queue<TreeNode> bfsTracker = new LinkedList<>();

        if (root != null) bfsTracker.add(invertedRoot);

        while(!bfsTracker.isEmpty()) {
            TreeNode current = bfsTracker.poll();
            // swap
            if(current.left != null && current.right != null){
                TreeNode newLeft = current.right;
                current.right = current.left;
                current.left = newLeft;
                bfsTracker.add(current.left);
                bfsTracker.add(current.right);
            } else if(current.left != null) {
                current.right = current.left;
                current.left = null;
                bfsTracker.add(current.right);
            } else if(current.right != null){
                current.left = current.right;
                current.right = null;
                bfsTracker.add(current.left);
            }
        }
        return invertedRoot;
    }
}