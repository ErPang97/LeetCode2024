class Solution {

    private int numIslands;

    class UnionFind {
        int n;
        int m;
        int[] sites;

        public UnionFind(int n, int m) {
            this.n = n;
            this.m = m;
            this.sites = new int[n*m];

            // set every sites root to itself
            for(int i = 0; i < this.sites.length; i++) {
                this.sites[i] =  i;
            }
        }

        /**
         * Assumption is that the root of the highest
         * root in the tree is itself
         */
        public int root(int p) {
            if(this.sites[p] != p){
                return root(this.sites[p]);
            }
            return p;
        }

        public boolean union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (rootP == rootQ) return false; // do nothing
            if (rootP < rootQ){
                this.sites[root(q)] = rootP;
            } else {
                this.sites[root(p)] = rootQ;
            }
            return true;
        }
    }


    public int numIslands(char[][] grid) {
        // P: we want to determine how many islands there are
        // E: for the first grid example, there is only 1 continuous land
        // mass represented by a single connected component of 1's
        // Here land is represented by "1" and "0" for water
        // D: One idea I have is to use the a Union-Find constructed DS and join
        // together all adjacent 1's
        // A: 
        /**
         * Main Idea: 
         * - initiate a Union-Find DS, which starts as a list of 
         *   n*m sites, corresponding to each site in the grid
         * - then, we iterate through each site, and check the four corners
         *   of each site, and perform a union with any neighbors it has that are 
         *   of value 1
         * - finally once we have established all the unions, we iterate once more
         *   and find the number of unique roots, ensuring that, ofc. the root holds a 1, 
         *   in the grid
         */

        // get number of rows and columns respectively
        int m = grid.length;
        int n = grid[0].length;
        numIslands = 0;

        UnionFind connectedComponents = new UnionFind(m, n);
        for (int i = 0; i < m; i++) { // loop through rows
            for (int j = 0; j < n; j++){ // loop through cols
                if(grid[i][j] == '1') { // check if an island
                    numIslands++;
                    joinIslandNeighbors(i, j, connectedComponents, grid);
                }
            }
        }

        HashSet<Integer> islandCCs = new HashSet<>();
        // int numIslands = 0;
        // for (int i = 0; i < connectedComponents.sites.length; i++){
        //     int row = i/grid[0].length;
        //     int col = i - row*grid[0].length;
        //     // System.out.println("i: " + i + " Row: " + row + " Col: " + col);
        //     // System.out.println("Grid: " + grid[row][col]);
        //     if (grid[row][col] == '1') {      
        //         islandCCs.add(connectedComponents.root(i));
        //     }
        // }

        // System.out.println("Num Islands: " + numIslands);
        return numIslands;
    }


    public void joinIslandNeighbors(int row, int col, UnionFind connectedComponents, char[][] grid) {
        int site = (row)*grid[0].length + col;
        // check above [row - 1][col]
        int newRow = row-1;
        int newCol = col;
        int neighborSite = (newRow)*grid[0].length + newCol;
        if(validSite(newRow, newCol, grid)){
            if (grid[newRow][newCol] == '1') {
                if(connectedComponents.union(site, neighborSite)) numIslands--;
            }
        }

        // check below [row + 1][col]
        newRow = row+1;
        newCol = col;
        neighborSite = (newRow)*grid[0].length + newCol;
        if(validSite(newRow, newCol, grid)){
            if (grid[newRow][newCol] == '1') {
                if(connectedComponents.union(site, neighborSite)) numIslands--;
            }
        }


        // check left [row][col - 1]
        newRow = row;
        newCol = col-1;
        neighborSite = (newRow)*grid[0].length + newCol;
        if(validSite(newRow, newCol, grid)){
            if (grid[newRow][newCol] == '1') {
                if(connectedComponents.union(site, neighborSite)) numIslands--;
            }
        }

        // check right [row][col + 1]
        newRow = row;
        newCol = col+1;
        neighborSite = (newRow)*grid[0].length + newCol;
        if(validSite(newRow, newCol, grid)){
            if (grid[newRow][newCol] == '1') {
                if(connectedComponents.union(site, neighborSite)) numIslands--;
            }
        }
    }

    public boolean validSite(int row, int col, char[][] grid){
        if (row < 0 || row >= grid.length){
            return false;
        }
        if (col < 0 || col >= grid[0].length) {
            return false;
        }
        return true;
    }

}