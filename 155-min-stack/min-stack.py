class MinStack:

    """
    P:
    - We want to design a stack class that supports push, pop, top and
    getMin 
    - Constraints:
        - each function must operate in O(1) - constant time complexity
        - -2^31 <= val <= 2^31 - 1
    E:
    - examples are relatively clear
    D:
    - either a linkedlist or array based implementation
    A:
    - ArrayImplementation's:
        - we'll need to keep track of the size of the stack (to know which element is last)
        - pushing an element is as easy as incrementing size, and storing the value in the right
        most portion of the list
        - popping is also easy, as we remove the last element in the list, and decrement size by 1
        - top is straightforward
        - getMin is bit tricky. 
            - Updating on push is straightforward, since we just need to 
            compare the new item to the last min.
            - Updating on pop is not so straightforward in the particular case of
            when the popped item is the min itself
                - what if we maintained a separate running stack impl, that simply keeps track
                of the min at the given frame.
                - whenver we pop the stack of the elements, we do the exact same
                for the minStack 
                - whenever we push, we push the min onto minStack
    C:
    """

    def __init__(self):
        self.stack = [] # list impl, push and pull elements
        self.minStack = [] # list impl, push and pull the minElement at every push and pop
        

    def push(self, value: int) -> None:
        """
        Pushes an element to the top of the stack
        :param: value - the element to be added to the stack
        """
        self.stack.append(value)
        if len(self.minStack)>0:
            if self.minStack[len(self.minStack)-1] > value:
                self.minStack.append(value)
            else:
                self.minStack.append(self.minStack[len(self.minStack)-1])
        else:
            self.minStack.append(value)


    def pop(self) -> None:
        """
        Removes the element that is located on the top of the 
        stack.
        """
        self.stack.pop()
        self.minStack.pop()
        

    def top(self) -> int:
        """
        Gets the element at the top of the stacj
        """
        return self.stack[len(self.stack)-1]


    def getMin(self) -> int:
        """
        Retrieves the minimum element from the stack
        """
        return self.minStack[len(self.minStack)-1]



# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(value)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()