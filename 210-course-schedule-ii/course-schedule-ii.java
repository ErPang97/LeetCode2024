class Solution {
    /**
    P:
    - we are given:
        - numCourses -> the number of courses we have to take
        - prerequisites -> an array where, prerequsites[i] = [a_i, b_i]
            where if we have a course a_i, we must have taken b_i first
        - we want to return, an ordering of the courses that is valid
    E:
        - overall, the problem isn't too difficult to understand
    D:
        - for data structures, we want to for sure make a graph, 
        using Lists, to get an AdjacencyList, is one way for sure
    A:
        - consider a directed graph, where courses have in-edges pointed 
        towards them by prerequisite courses
        - we can determine if a possible schedule exists by seeing
        if a cycle exists
        - to do this, we use Kahn's algoritm:

        init a queue
        init topo-sort array filled with 0's of length numCourses
        init a in-degree array, and populate this with current in-degrees of all the vertices
        init index = 0
        for vertices in graph
            add if in_degree(vertices) == 0
        while queue not empty
            current at vertex = queue.poll
            order[index++] = at
            for to-vertex in at's adjacencyList:
                "remove" vertex by removing in_degree[to-vertex] = in_degree[to-vertex] - 1
                if in_degree(to-vertex) is 0
                    add to-vertex to queue
        return ordering if index = n
        else return empty array

    C:
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Generate the Adjacency list of the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int to = prerequisites[i][0];
            int from = prerequisites[i][1];
            List<Integer> fromList = adjacencyList.get(from);
            fromList.add(to);
        }

        // look for valid topo-sort
        return topologicalSort(adjacencyList);
    }

    public int[] topologicalSort(List<List<Integer>> adjacencyList) {
        int n = adjacencyList.size();
        int[] in_degree = new int[n];

        // Generate the in_degrees for the vertices
        // loop through the vertices
        for (int i = 0; i < adjacencyList.size(); i++) { 
            // loop through the vertices that, the current vertex points to
            for (int to : adjacencyList.get(i)) { 
                in_degree[to] = in_degree[to] + 1;
            }
        }

        // init index, which serves as the current vertex we have just added to the sort
        int index = 0; 
        Queue<Integer> queue = new LinkedList<>();
        // add the 0 in-degree vertices to the queue
        for (int i = 0; i < in_degree.length; i++) {
            if (in_degree[i] == 0) {
                queue.add(i);
            }
        }

        int[] order = new int[n];
        while (!queue.isEmpty()){
            int from = queue.poll();
            order[index] = from;
            index++;
            // loop through the vertices that, the current vertex points to
            for (int to : adjacencyList.get(from)) {
                in_degree[to] = in_degree[to] - 1;
                if (in_degree[to] == 0) {
                    queue.add(to);
                }
            }
        }

        // if we didn't add all vertices to the order
        if (index != n) {
            return new int[0];
        } 
        return order;
    }
}