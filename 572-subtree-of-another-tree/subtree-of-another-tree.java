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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // P:
        // E:
        // D:
        // A: maybe a stack
        /**
            - we must confirm first whether subRoot, if a similar node
            with the same value exists in the tree of root
            - then, once we find it, check if the same subroot in
            the root tree and the tree defined by subroot are the same
            trees (note there are multiple candidates)
            - if none are subtrees, then we know its not a subroot
            - do this by recursion:
            - first check base case, if rootSubRoot == null and subroot == null
                return true
            - then check if root == subroot
                if so: then check children of the roots:
                store if root.left == subroot.left
                and store if root.right == subroot.right
         */
        // C:

        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;

        Stack<TreeNode> rootStack = new Stack<>();
        rootStack.add(root);

        while(!rootStack.isEmpty()) {
            TreeNode current = rootStack.pop();
            if(current.val == subRoot.val){
                if(isSameTree(current, subRoot)) return true;
            } 

            if(current.left != null){
                rootStack.add(current.left);
            }
            if(current.right != null){
                rootStack.add(current.right);
            }
            
        }

        // if none-found return false
        return false;
    }

    public boolean isSameTree(TreeNode root1, TreeNode root2){

        // base case, both are null, return True
        // if either aren't null, but other is, return false;
        if(root1 == null && root2 == null) return true;
        else if(root1 != null && root2 == null){
            return false;
        } else if(root1 == null && root2 != null){
            return false;
        }
        // return false right away if not equal vals
        if(root1.val != root2.val) return false;

        boolean sameLeft =  isSameTree(root1.left, root2.left);
        boolean sameRight = isSameTree(root1.right, root2.right);

        return (sameLeft == true && sameRight == true);
    }
}