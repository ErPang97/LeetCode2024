class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // P:
        // E:
        // D:   Stack
        // A:
        /** 
              iterate through the array 
                //(we create a monotonic decreasing stack)
                //(except we store an int array of size 2, with
                // index 0 being the index in original temperature array
                // and index 1 being the temperature itself)
                int timesPopped = 1
                - while(!stackIsEmpty and topOfStack[1] < currentElement)
                    - poppedVal = temperatureStack.pop 
                    - numDaysToWait[poppedVal[0]] = i - poppedVal[0]
                    - temperatureStack.pop 
                    - timesPopped++; 
                - add (index, temperature) to the stack

        */
        // C:

        int [] numDaysToWait = new int[temperatures.length];
        Stack<Integer[]> temperatureStack = new Stack<>();
        for(int i = 0; i<temperatures.length; i++) {
            int current = temperatures[i];

            int timesPopped = 1;
            while(!temperatureStack.isEmpty() 
                    && temperatureStack.peek()[1] < current){

                Integer[] popped = temperatureStack.pop();
                numDaysToWait[popped[0]] = i - popped[0];
                timesPopped++;

            }

            temperatureStack.add(new Integer[]{i, current});
        }


        // the last day is always 0
        numDaysToWait[numDaysToWait.length-1] = 0;
        return numDaysToWait;
    }
}