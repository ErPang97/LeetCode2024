class MinStack {

    // P: class design
    // E: 
    // D: LinkedList ?
    // A: 
    /**
     * - idea is to build a stack with a List implementation
       - we keep track of the top of the stack
       - and for each element, we keep track of pointers to
       the element just below it
       - we have member variables:
          1. some Node that represents the top of the stack
          2. some int that represents the minimum element
       - member functions:
          1. a Node class declaration, and constructor
          which would hold a pointer to the node below it
            - can also hold a minimum value that it knows when it was added
          2. push - adds an element to the top of the stack
            - if(top == null) {
                create a new node that contains the value of the node
                set the top of stack to this node
                set the min to the value held in the node
            } else {
                get the current top, create a new node that points to 
                this node as its next, and store the value
                make this new node, the new top of the stack
            }
          3. pop - remove an element from top of stack
            // challenge what if the value of the NODE is a minimum
            - if(top != null) {
                set the new top, to be the next of the current
                top
                check if the value of the min is the value of this node
                -- if it is... how to search for this minimum
                got it! great hint by somebody (keep track of the minimum
                that a given node sees when it is added)
            } else {
                -- doesn't matter as its not being operated on empty
                stacks
            }
          4. top - just return the value of the element from the top fo the stack
            - does the size change when top is called?
          5. getMin() - retrieve the global min.
     */


    int min;
    Node top;

    public class Node {
        int val;
        Node next;
        int minimumWhenAdded;

        public Node(int val, Node next, int minimum) {
            this.val = val;
            this.next = next;
            this.minimumWhenAdded = minimum;
        }
    }

    public MinStack() {
        this.top = null;
    }

    
    public void push(int val) {
        if(top == null) {
            Node current = new Node(val, null, val);
            this.top = current;
            this.min = val;
        } else {
            Node next = top;
            if(val < min) {
                Node current = new Node(val, next, val);
                this.top = current;
                this.min = val;
            } else if(min <= val){
                Node current = new Node(val, next, min);
                this.top = current;
            }
        }
    }
    
    public void pop() {
        if(top.next != null){
            this.top = top.next;
            if(top != null) this.min = top.minimumWhenAdded;
        } else {
            this.top = null;
        }
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */