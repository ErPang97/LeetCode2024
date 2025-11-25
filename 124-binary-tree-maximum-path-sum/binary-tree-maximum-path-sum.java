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
    /**
    P:
    - we're given a TreeNode root
    - we're told a path sum is the sum of
    the values of the node's in the path
    - a path is a sequence of nodes where
    every adjacent node has an edge connecting them
    - we want to find the maximum path sum
    of any non-empty path (an int)
    E:
    - the first example:
        starting from the root, we can create two paths:
        1 -> 2, with pathSum = 3
        and 1 -> 3 with pathSum = 4
    - starting from either child 2, or 3:
        we can construct the path 2 -> 1 -> 3, which
        has a pathSum of 6
    - there are no more examples, so the pathSum of 6 is the
    max
    D:
    - since we're trying to navigate the tree, using a
    Set to keep track of visited nodes may make sense
    - perhaps one could create an adjacency list
    representation of every node as well
    A:
    - Brute Force:
        - a brute force method would be to take
        every node in the graph and enumerate
        all the possible paths
        - then for every path, compute the sum of
        the paths
        - return the maxium path sum
        - to do this, we would develop an adjacency
        list for all nodes
        - then iterating through the list of nodes 
        once, treating each node as a root node 
        for the path
        - this could potentially have us visit all nodes
        in each iteration, and if we calculate the number
        of edges too, it could easily be O(n^2*m)
    - Better Algorithm?
        - we definitely still want to enumerate all
        possible paths, but how to do so without revisiting?
        - recursion!
    C:
     */
    public int maxPathSum(TreeNode root) {
        // global variable to keep track of result
        List<Integer> result = new ArrayList();
        result.add(root.val);
        dfs(root, result);
        return result.get(0);
    }

    /**
        return max path sum without split
     */
    public int dfs(TreeNode root, List<Integer> result) {
        if (root == null) {
            return 0;
        }
        // recursively compute 
        int leftMax = dfs(root.left, result);
        int rightMax = dfs(root.right, result);
        leftMax = Math.max(leftMax, 0);
        rightMax = Math.max(rightMax, 0);

        // compute max path sum with split
        result.set(0, 
            Math.max(result.get(0), root.val + leftMax + rightMax));

        return root.val + Math.max(leftMax, rightMax);
    }
}