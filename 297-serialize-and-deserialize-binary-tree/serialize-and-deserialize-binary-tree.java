/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /**
 P:
    Encode function:
    - we want to encode tree to a single string
    - given: a TreeNode root
    - return: a string representation of the Tree

    Decode function:
    - take the decoded string rep and turn it back into 
    the original Tree structure 

 E:
    - 
 D:

 A:

 C:
  */
public class Codec {

    // global variable for keeping track of 
    // what nodes to add 
    int i;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfsSerialize(root, result);
        String resString = result.get(0);
        for(int i = 1; i < result.size(); i++) {
            resString += "," + result.get(i);
        }
        return resString;
    }

    // DFS helper function for serializing
    public void dfsSerialize(TreeNode node, List<String> result) {
        if (node == null) {
            result.add("N");
            return;
        }
        result.add(""+node.val);
        dfsSerialize(node.left, result);
        dfsSerialize(node.right, result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        // global variable
        this.i = 0;

        return dfsDeserialize(data, vals);
    }

    // DFS helper function for deserializing
    public TreeNode dfsDeserialize(String data, String[] vals) {
        if (vals[this.i].equals("N")) {
            this.i += 1;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(vals[this.i]));
        this.i +=1;
        node.left = dfsDeserialize(data, vals);
        node.right = dfsDeserialize(data, vals);
        return node;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));