class Solution {
    /**
    P:
        given: 
          -a 2d int[] array named triplets
            - triplets[i] = [a_i, b_i, c_i]
          - a 1d int[] array named target
            - target = [x, y, z], what we wish to obtain
        we can do any number of times the following opps:
          - Choose two indices (0-indexed), i and j where i != j
          - Update triplets[j] = [max(a_i, a_j), max(b_i, b_j), max(c_i, c_j)]
        return:
          - given a triplets array, can we obtain the target triplet
          using the operations provided
    E:
        - the examples make sense
    D:
        - Maybe some sort of set or map that contains 
        triplets we care about, as in the values they contain must not be 
        greater than the values of the target
        - Nevermind, we can just use a list
    A:
        Brainstorm:
        - Bruteforce way?
        - First thing to do is make sure all the values in target are 
        found in their correct spots
        - We need to see if target[0] can be found in column 0, 
        target[1] can be found in column 1, and target[2] can be found
        in column 2
        - If it can't we know its impossible
        - but what if they do exist? 
        - we might want to start with one that resembles the target the most
        - we certainly want to ignore tripets that have values greater than
        the target

        Now knowing the concepts:
        - We generate some collection of useful triplets, by
        comparing looping through triplets, and keeping only triplets
        whose values at indices 0,1, and 2 are <= to the corresponding 
        values in target
        - Now with the remaining, as long as the corresponding values
        are found in the remaining, then we're guaranteed a solution
    C:
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        List<int[]> validTriplets = new ArrayList<>();
        for (int[] triplet: triplets) {
            // we found an invalid triplet
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            } else {
                // add the valid triplet
                validTriplets.add(triplet);
            }
        }

        boolean one = false;
        boolean two = false;
        boolean three = false;

        // now just check if the values from target can be found
        for (int[] triplet: validTriplets) {
            if (triplet[0] == target[0])
                one = true;
            if (triplet[1] == target[1])
                two = true;
            if (triplet[2] == target[2])
                three = true;
        }

        return one && two && three;
    }
}