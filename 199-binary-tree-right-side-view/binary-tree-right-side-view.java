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
    public List<Integer> rightSideView(TreeNode root) {
        // P:
        // E:
        // D: just Lists
        // A:
        /**
            - go level by level
            - and only look at the right most node (meaning the last node)
            - using a BFS construction
            - first add the root node to a queue
            - then while queue isnt empty
            - check the size of the level
                for i in range of size:
                    - pop from queue
                    - and add the children of the nodes to the queue
                - if its last node in the queue, append its value to the
                - return list
         */
        // C:


        ArrayList<Integer> rightView = new ArrayList<>();

        if(root == null){
            return rightView; // return an empty list
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int sizeOfLevel = queue.size();
            for(int i = 0; i < sizeOfLevel; i++){
                TreeNode current = queue.poll();
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
                if(i == sizeOfLevel - 1){
                    rightView.add(current.val);
                }
            }
        }

        return rightView;
    }
}