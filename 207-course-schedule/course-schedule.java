class Solution {
    /**
    P:
        - given a total of numCourses, is it possible to finish all courses
        - we want to find if there exists a scheduling scheme of some
        sort that allows us to finish all courses
        - we consider this problem to be a directed graph 
        where in the prerequisite array, a node from course a points to course b,
        - if when traversing the graph, we find a cycle, then we know
        that it is impossible to take all courses
    E:
        - examples seem pretty okay. In the first example, given only 2 courses, [0, 1]
        the only pre-requisite is that you take 0 first before 1
        - however, for example 2, we similarly have two courses 0, 1
        but each is a pre-requisite for the other, so its impossible
    D:
        - we may want to store the courses that a particular course is a prerequisite to
        so might have to make our own or use a Map or Array to store the list of classes this course
        is a prerequisite to
    A:
        - 
    C:
    */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            List<Integer> neighbors = new ArrayList<>();
            adjacencyList.add(neighbors);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int prereq[] = prerequisites[i];
            List<Integer> listOfPrereqs = adjacencyList.get(prereq[0]); // get list of prereqs
            listOfPrereqs.add(prereq[1]); // add the prereq to the set
        }

        // now to find a cycle given our graph


        return cycleInGraph(adjacencyList);
    }

    // queue always contains set of nodes with no incoming edges
    public boolean cycleInGraph(List<List<Integer>> adjacencyList){
        int n = adjacencyList.size();
        int[] in_degree = new int[n];
        // calculate the in degree of all vertices
        for (int i = 0; i < n; i++) {
            for (int to: adjacencyList.get(i)) {
                in_degree[to] = in_degree[to] + 1;
            }
        }

        // queue contains nodes with no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in_degree[i] == 0) {
                queue.add(i);
            }
        }

        int index = 0;
        // int[] order = new int[n];
        while (!queue .isEmpty()) { // "remove" in-degree verts from graph
            int at = queue.poll();
            // order[index++] = at;
            index++;
            for (int to: adjacencyList.get(at)) { // process the node we're at
                in_degree[to] = in_degree[to] - 1; // "remove" 1 in degree from all 
                                                // neighbors this node points to
                if (in_degree[to] == 0) {
                    queue.add(to);
                }
            }
        }

        if (index != n) { 
        // if not all nodes we're processed and added
        // to queue, then we know there's a cycle 
            return false;
        } 
        return true;

    }
}