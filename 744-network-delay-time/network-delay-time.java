class Solution {
    /**
    P:
        - we're given a graph represented as a 2D array: times
            - times[i] defines an edge, where times[i] = (u_i, v_i, w_i)
                - u_i is source node, v_i is target node, and w_i is the time for signal to travel
            - effectively this is a weighted-edge directed graph
        - also given a node k, from which all other nodes are to receive a signal from
        - we want to return the minimum time it takes for all n nodes to receive the sigal
        - if impossible return -1
    E:
        - not too difficult conceptually to understand ^
    D:
        - at the moment possibly thinking a Set for possibly a BFS related approach

    A:
        - recall Dijkstra's Shortest Path Alg!
        
        init dist array(or map) 
        - holds current best distance from source to a given vertex v


        - for each vertex in V
            dist[v] = infinity
            prev[v] = -1 // holds pointers
            to prev-hop nodes on the shortest path
            from source to given vert (not necessary for
            given problem)
            add v to Queue (min priority queue, which looks at current distance)
        - dist[source] = 0
        - while Queue not empty
            u -> vertex in Queue with min distance
            remove u from Queue

            for each neighbor v of u in queue
                lengthOfCurrentPath = dist[u] + weight(u,v)
                if lengthOfCurrentPath < dist[v]
                    dist[v] = lengthOfCurrentPath
                    prev[v] = u
                    queue.add(v, lengthOfCurrentPath)
    C:
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Custom comparator
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            // compare the distance which will be stored at index 1
            public int compare(int[] a, int[] b) {
                return Integer.compare(b[1], a[1]);
            }
        };

        // generate neighbor list
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge: times){
            int a = edge[0]; // source
            int b = edge[1]; // target
            int time = edge[2];
            adjList.get(a-1).add(new int[]{b, time}); 
            // directed graph so we'll only store neighbors this node can reach, and the time
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        queue.add(new int[]{k, 0}); // store initial vertex and distance as 0
        int[] dist = new int[n]; // init distance vector, and all set to 0
        // now set eveything except k to the largest val possible
        for(int i = 1; i <= n; i++) {
            if (i != k) {
                dist[i-1] = Integer.MAX_VALUE;
            }
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll(); // remove best value at the moment
            int currentVert = current[0];
            // loop through neigbors
            for (int[] neighborAndTime : adjList.get(currentVert-1)) {
                int neighbor = neighborAndTime[0];
                int time = neighborAndTime[1];
                int lengthCurrentPath = dist[currentVert-1] + time;
                if (lengthCurrentPath < dist[neighbor-1]) {
                    dist[neighbor-1] = lengthCurrentPath;
                    queue.add(new int[]{neighbor, lengthCurrentPath});
                }
            }
        }

        int maxTime = -1;
        for (int i : dist) {
            maxTime = Math.max(maxTime, i);
        }

        if(maxTime == Integer.MAX_VALUE) return -1;

        return maxTime;
    }

    
}