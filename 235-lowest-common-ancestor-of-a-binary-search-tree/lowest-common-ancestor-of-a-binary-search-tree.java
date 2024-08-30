/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // P: find lowest common ancestory, 
        /**    - some observations: if one of the nodes, is a descendant of the other
               - means lowest common ancestor is the node, higher up in the tree
               - otherwise, they are both for sure, descendants of their LCA
               - p != q
               - p and q exist
        */
        // E: 
        // D: - none
        // A: - we perform a simple value comparison
        /**
            - using the properties of binary trees, we know
            - child values, left of the parent node are smaller
            - and values to the right are greater
            - if we find and see that the two nodes happen to be
            -  one greater and one smaller, we know that 
            - the LCA is the parent node being examined
            - if both on the same side (both less), we cut the search
            - in half and recursively check the next node
            - if both greater, search the other half
            - in the case when parent node is one of the nodes,
            - then we know that node is the LCA
         */
        // C:
        
        TreeNode smaller;
        TreeNode larger;
        if(p.val < q.val) {
            smaller = p;
            larger = q;
        } else {
            smaller = q;
            larger = p;
        }

        // base case:
        if(smaller.val == root.val){ 
            return smaller;
        } else if(larger.val == root.val){
            return larger;
        }
        if(smaller.val < root.val && larger.val > root.val) {
            return root; // when root is the LCA as smaller is in
                         // the left subtree, while larger in right subtree
        } else if(smaller.val < root.val && larger.val < root.val){
            return lowestCommonAncestor(root.left, smaller, larger);
            // search left subtree
        } else {
            //if(smaller.val > root.val && larger.val > root.val){
            return lowestCommonAncestor(root.right, smaller, larger);
            // search right subtree
        }

    }
}