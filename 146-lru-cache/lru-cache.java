class LRUCache {

    /**
     * Node helper class for storing the key, and the age of
     * the key item
     */
    class Node {

        public int key;
        public int value;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }

    }


    /**
     * the maximum amount of key-value pairs that can be stored
     */
    private int capacity;

    private int currentSize = 0;

    /**
     * maps a key to its address in a LinkedList
     */
    private Map<Integer, Node> keyToNode = new HashMap<>();
    
    /**
     * we use a LL to keep track of the "age"
     */
    private List<Node> ageList = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    
    public int get(int key) {
        if(keyToNode.containsKey(key)) {
            Node keyVal = keyToNode.get(key);
            ageList.remove(keyVal);
            ageList.addLast(keyVal);
            // keyToAddress.replace(key, ageList.size()-1);
            return keyVal.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(keyToNode.containsKey(key)) {
            Node keyVal = keyToNode.get(key);
            ageList.remove(keyVal);
            keyVal.value = value;
            ageList.addLast(keyVal);
            // keyToAddress.replace(key, ageList.size()-1);
        }
        else {
            Node keyVal = new Node(key, value);
            if(currentSize == capacity){
                Node removed = ageList.removeFirst();
                keyToNode.remove(removed.key);
            } else {
                currentSize++;
            }
            ageList.addLast(keyVal);
            keyToNode.put(key, keyVal);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */