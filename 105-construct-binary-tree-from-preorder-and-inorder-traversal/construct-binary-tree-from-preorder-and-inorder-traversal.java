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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // P:   
        // E:   
        // D:   
        /**
                HashTable to keep track of the indices of the 
                nodes in the in-order traversal
        */
        // A:   
        /**
            - Brainstorm Notes:
                - we always start at the root in a pre-order traversal
                - we know we always start at the node that is furthest left
                in the in-order traversal
                - recall that pre-order starts at root, then goes left, then
                goes right
                - recall that in-order goes left, then root, then right
         */
        // C:   
        // base case: when preorder or inorder length == 0,
        // there are no more nodes to create so return null
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }

        // map each number to its location in the inorder
        // array
        HashMap<Integer, Integer> inorderIndex = new HashMap<>();
        for(int i = 0; i< inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        // we use recursion to construct the tree
        TreeNode root = new TreeNode(preorder[0]);

        // midpoint; we know that the root in the preorder is always
        // first, and in the inorder, whatever comes left, is
        // part of the left subtree, and whatever is right, is
        // on the right subtree
        int mid = inorderIndex.get(preorder[0]);

        // we construct the left tree by 
        // calling build tree on the subarray, where
        // the next node is the root, in preorder
        // and in inorder, we we know that only the 
        // values left of the current root are part of the
        // left subtree
        root.left = buildTree(
            Arrays.copyOfRange(preorder, 1, mid+1), 
            Arrays.copyOfRange(inorder, 0, mid)
        );

        // similarly, we construct the right subtree, treating the
        // next node as the root, and the right subtree consists only of
        // that which is to the right of the current root
        root.right = buildTree(
            Arrays.copyOfRange(preorder, mid+1, preorder.length),
            Arrays.copyOfRange(inorder, mid+1, preorder.length)
        );

        return root;
    }

}