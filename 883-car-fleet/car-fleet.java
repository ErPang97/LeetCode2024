class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        // P:
        // E: 
        // D: TreeMap to map positions to their speeds, but in key-sorted order
        // A: 
        /*
        *   - init. a stack that will be monotonically strictly
        *   decreasing (popping if equal too!)
        *   in terms of the time it takes for the cars to reach the end goal
        *   without blocking. 
        *   - this is because, we know that if considering positions in sorted
        *   order, if a car that started before another, has a calculated time
        *   to reach target, less than or equal to another car after it, it means that 
        *   it will reach the other car before reaching the end, thus they became a fleet
        *   - so, I think, the result is the size of the stack after
        *   - iterate once through the arrays and store the position and speed
        *   into the TreeMap
        *   - iterate now through the TreeMap
        *       - calculate
        */
        // C:

        // maps, in sorted key-order, the position of the car
        // to its speed
        Map<Integer, Integer> posToSpeed = new TreeMap<>();
        Stack<Double> timesToReachEnd = new Stack<>();
        for(int i = 0; i < position.length; i++){
            posToSpeed.put(position[i], speed[i]);
        }

        for(int pos : posToSpeed.keySet()){
            double spd = (double) posToSpeed.get(pos);
            double timeToReachEnd = (double)(target - pos)/spd;
            while(!timesToReachEnd.isEmpty() &&
            timesToReachEnd.peek() <= timeToReachEnd) {
                timesToReachEnd.pop();
            }
            timesToReachEnd.add(timeToReachEnd);
        }
        System.out.println(timesToReachEnd);
        return timesToReachEnd.size();

    }
}