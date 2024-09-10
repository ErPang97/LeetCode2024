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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // P:
        // E:
        // D: lists, and queues
        // A:
                /**
            - we use a breadth-first approach to this algorithm
            - create a queue, and begin by adding the root node
            to the queue
            - initiate a List<List<Integer>>  returnList
            - add root node to queue
            - while(queue not empty)
                - queue size is number of nodes in this queue
                - poll queue, (size of queue) times
                    - add the value of the node to an List<Integer> 
                    arrayList
                    - add the two children to the queue (if it has two)
                - append this array list to the return list
            - return the returnList
         */
        // C:

        List<List<Integer>> traversal = new ArrayList<>();
        Queue<TreeNode> bfsQueue = new LinkedList<>();

        if(root == null){
            return traversal;
        } else{
            bfsQueue.add(root);
            List<Integer> levelValues = new ArrayList<>();
            while(!bfsQueue.isEmpty()){
                
                int levelSize = bfsQueue.size();
                levelValues = new ArrayList<>();

                for(int i = 0; i < levelSize; i++){
                    TreeNode currentNode = bfsQueue.poll();
                    levelValues.add(currentNode.val);
                    if(currentNode.left != null){
                        bfsQueue.add(currentNode.left);
                    }
                    if(currentNode.right != null){
                        bfsQueue.add(currentNode.right);
                    }
                }
                traversal.add(levelValues);
            }
        }
        
        return traversal;
    }
}