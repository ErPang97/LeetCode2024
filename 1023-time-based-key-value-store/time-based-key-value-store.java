class TimeMap {

    /**
     * We will use a HashMap that maps a given key
     * to another map, which is a TreeMap, to maintain
     * sorted Key ordeer, where the key is the timestamp.
     */
    Map<String, TreeMap<Integer, String>> keyToTimestampAndVal;

    public TimeMap() {
        this.keyToTimestampAndVal = new HashMap<>();
    }
    
    /**
     * Stores the given 'key' with 'value' at the given
     * 'timestamp'
     * @param key
     * @param value
     * @param timestamp 
     */
    public void set(String key, String value, int timestamp) {
        // if the map we have, contains the key provided
        // get the underlying timestampToVal map, and map the
        // given timestamp to the value
        TreeMap<Integer, String> timestampToVal;
        if(keyToTimestampAndVal.containsKey(key)){
            timestampToVal = keyToTimestampAndVal.get(key);
        } else {
            timestampToVal = new TreeMap<>();
        }
        timestampToVal.put(timestamp, value);
        keyToTimestampAndVal.put(key, timestampToVal);
    }
    
    /**
     * @return a string, with the same given 'key', 
     * and its timestamp_prev <= 'timestamp'
     * If there are multiple such values, return the one with 
     * the largest timestamp
     */
    public String get(String key, int timestamp) {
         if(keyToTimestampAndVal.containsKey(key)){
            TreeMap<Integer, String> timestampToVal = keyToTimestampAndVal.get(key);
            if(timestampToVal.containsKey(timestamp)){
                return timestampToVal.get(timestamp);
            } else if (timestampToVal.lowerKey(timestamp) != null) {
                return timestampToVal.get(
                    timestampToVal.lowerKey(timestamp)
                );
            } else {
                return "";
            }
         } else {
            return "";
         }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */