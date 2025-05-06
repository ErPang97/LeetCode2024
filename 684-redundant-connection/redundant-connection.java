class Solution {
    /**
    P:
        - given:
            - an undirected graph represented by an array of edges
            where edges[i] = [a_i,b_i] and a_i and b_i are two nodes
            in graph
            - we also know one additional edge was added that wasn't in the 
            initial tree graph
        - we want to return an edge that can be removed, that would result in a tree
        - recall a tree has no cycles, so how to handle?
    E:
        - overall relatively easy to follow
        - given a the first one ie, removing [2,3] breaks the cycle, and its the last in the input
    D:
        - certainly a set for keeping track of visited nodes
    A:
        - generally, we can find where a cycle occurred if when doing BFS/DFS, we
        encounter a previously visited node
        - if we know we find this node, we should be able to remove this and the node
        that led to this, breaking the cycle
        - first, build up the adj List representation
        - start a DFS at index 0, then
        look for the first revisited node, 
        and thus we know this is the cycle. However, we now need to
        return  the last that occurs in the input
    C:
     */


    public int[] findRedundantConnection(int[][] edges) {
        int[] removed = new int[2];

        // init and generate adjacency list
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= edges.length; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            adjList.get(a).add(b);
            adjList.get(b).add(a);

            boolean[] visited = new boolean[edges.length+1];
            // use DFS from the first edge, and
            if(dfs(visited, adjList, a, -1)) {
                return edge;
            }
        }
        
        return removed;
    }

    public boolean dfs(boolean[] visited, Map<Integer, List<Integer>> adjList, int current, int parent) {
        // we have found a visited node!
        // so we know a cycle exists
        if (visited[current]) {
            return true;
        }
        visited[current] = true;

        for (int neighbor : adjList.get(current)) {
            if (neighbor == parent) {
                continue;
            } else {
                if (dfs(visited, adjList, neighbor, current)) {
                    return true;
                }
            }
        }
        return false;
    }
}