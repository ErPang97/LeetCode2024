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
    public int goodNodes(TreeNode root) {
        // P:
        // E:
        /**
        Obs:
            - root node is always a good node
            - child node is good, 
            if greater than or equal to
            parent node, which is also a good node
            - however, not always true 
            if parent isn't good
         */
        // D:
        /** 
            - need some sort of Queue or Stack depending
            on traversal method
        */
        // A:
        /**
            - we may want to keep track of the maximum along
            the path
            - DFS seems ideal
            Alg:
            0. init stack
               init current_max = 0
               init good_count = 0
            1. start @ root node & add to stack
            2. while stack not empty:
                - if (max >= current_max)
                    - replace max and increment good count
                - check for children and add children to stack
            3. return good_count
         */
        // C:
        Stack<TreeNode> dfsStack = new Stack<>();
        Stack<Integer> maxTrack = new Stack<>();
        int current_max = root.val;
        int good_count = 0;

        dfsStack.add(root);
        maxTrack.add(current_max);
        while(!dfsStack.isEmpty()) {
            TreeNode current = dfsStack.pop();
            current_max = maxTrack.pop();
            if(current.val >= current_max){
                good_count++;
                current_max = current.val;
            }
            if(current.left != null){
                dfsStack.add(current.left);
                maxTrack.add(current_max);
            }
            if(current.right != null) {
                dfsStack.add(current.right);
                maxTrack.add(current_max);
            }
        }

        return good_count;
    }
}