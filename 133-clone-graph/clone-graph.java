/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // P: o
        /** On the assumption that the node
         *  being passed in, is the root of the graph,
         *  then, we just want to create a DEEP COPY of hte
         *  graph.
         *  Recall: A deep copy is where, essentially every value,
         *  including internal data structures and all, is a copy
         *  of another. However, they are not simply reference to the 
         *  same object, they are different objects altogether
         */
        // E: 
        /**
         *  pretty much the example there is pretty clear
         */
        // D:
        /**
         *  We might need to keep track of what nodes we already visited
         *  and thus already took care of cloning its neighbors.
         *  We might need a map to map the copy nodes to the nodes they
         *  are a reference of 
         */
        // A:
        /**
         *  We'll want to do either one of our favorite search algorithms,
         *  whether it be BFS or DFS
         *  As we search through the graph, we will copy each node, and copy 
         *  each node neighbor, mapping the original node to the copy node, as 
         *  we need to copy its neighbors as well
         *  Then we add the neigbhor nodes to our Queue or Stack, and then rinse and repeat
         *  on the next nodes in our queue
         */

        // maps the original node to the node it is a copy of 
        HashMap<Node, Node> originalToCopy = new HashMap<>(); 
        LinkedList<Node> queue = new LinkedList<>();

        // if the input node is null, return null
        if (node == null) return node;

        // create a copy of the head node, and map the originalToCopy
        Node copyHead = new Node(node.val);
        originalToCopy.put(node, copyHead);
        queue.add(node);
 
        while (!queue.isEmpty()) {

            Node current = queue.poll(); // remove first element
            Node copyCurrent = originalToCopy.get(current);

            List<Node> neighbors = current.neighbors;
            List<Node> copyNeighbors = copyCurrent.neighbors;

            for (Node neighbor: neighbors) {
                if(!originalToCopy.containsKey(neighbor)) {
                    // mapping copyNeighbor to its original;
                    Node copyNeighbor = new Node(neighbor.val);
                    originalToCopy.put(neighbor, copyNeighbor);
                    queue.add(neighbor);
                }
                copyNeighbors.add(originalToCopy.get(neighbor));
            }

        }

        return copyHead;
    }
}