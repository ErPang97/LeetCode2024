public class Solution {
    // solution using Hierholtzer's algortihm
    public List<String> findItinerary(List<List<String>> tickets) {

        // generate the adjacency list representation of the graph
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            // we use a priority queue for each neighbor, so that
            // the order of the strings is maintained
            adj.computeIfAbsent(src, k -> new PriorityQueue<>()).offer(dst);
        }

        List<String> res = new ArrayList<>();
        // starting with JFK, use DFS
        // to recursively find the itinerary
        dfs(adj, "JFK", res);


        // we reverse the result, because first node
        // in itinerary is not going to be added last
        // as we don't add nodes until we 
        // exhaust all neighbors, and after pursuing each
        // neighbor's neighbors
        Collections.reverse(res);
        return res;
    }

    private void dfs(Map<String, PriorityQueue<String>> adj,
                     String src, List<String> res) {
        
        // get current nodes adjacency list
        PriorityQueue<String> queue = adj.get(src);

        // while there are still outgoing edges
        // from this node use DFS to pursue a route
        // and then after exhausted all nodes
        // add the current node to result
        while (queue != null && !queue.isEmpty()) {
            String dst = queue.poll();
            dfs(adj, dst, res);
        }
        res.add(src);
    }
}