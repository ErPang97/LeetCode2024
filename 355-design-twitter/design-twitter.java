class Twitter {
    /**
    P:
        - we want to make a simple twitter
        - each tweet will have a userId of the user 
        that posted it and it has a tweetId itself
        - a few different functions:
            - Twitter() object
            - a postTweet method that makes a new tweet with tweetId
            and userId
            - a list function that returns the 10 most recent tweetId's
            made
            - ordered from most to least recent
            - void follow (followerId, followeeId), followerId following followeeId
            - void unfollow (followerId, followeeeId), followerId unfollows followeeId
                - user can't unfollow themself
    E:
        - pretty clear
    D:
        - HashMap maybe to map the followerId to followeeIds AND/OR 
        to their List of tweetId and their time
        - HashSet for followeeId store
        - PriorityQueue to track the most recent tweetIds in the user's feed... easier said than done!
            - store tweetId and time instead!
    A:
        - when creating a new Twitter() object, init the map followerId to followeeId
        - and the map of followerId to priorityqueues of tweetId's
        - init a global tweetId val, and everytime postTweet is called, increment
        it by +1 after creating the tweet
    C:
     */

    private Map<Integer, Set<Integer>> followsStore;
    private Map<Integer, List<int[]>> tweetsStore;
    private int time = 0; 

    public Twitter() {
        followsStore = new HashMap<>();
        tweetsStore = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        List<int[]> tweets;
        if (tweetsStore.containsKey(userId)) {
            tweets = tweetsStore.get(userId);
        } else {
            tweets = new ArrayList<>();
        }
        int[] tweetAndTime = new int[]{tweetId, time};
        time--;
        tweets.add(tweetAndTime);
        tweetsStore.put(userId, tweets);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        // retrieve tweets from all users and obtain an aggregate list
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        
        Set<Integer> followees = followsStore.get(userId);

        // the current userId
        List<int[]> tweetAndTimeList = tweetsStore.get(userId);
        if (tweetAndTimeList != null ) {
            for (int[] tweetAndTime: tweetAndTimeList) {
                maxHeap.add(tweetAndTime);
            }
        }

        // now for all the users they follow
        if (followees != null) {
            for (int followeeId: followees) {
                tweetAndTimeList = tweetsStore.get(followeeId);
                if (tweetAndTimeList != null ) {
                    for (int[] tweetAndTime: tweetAndTimeList) {
                        maxHeap.add(tweetAndTime);
                    }
                }
            }
        }

        while(!maxHeap.isEmpty() && result.size()!= 10) {
            result.add(maxHeap.poll()[0]);
        }
        return result;
    }  
    
    public void follow(int followerId, int followeeId) {
        Set<Integer> setOfFollowees;
        // if null, create a set to represent the set of followers
        if (followsStore.containsKey(followerId)) {
            setOfFollowees = followsStore.get(followerId);
        } else {
            setOfFollowees = new HashSet<>();
        }
        // add the followeeId to the follower's set of Followees
        setOfFollowees.add(followeeId);
        followsStore.put(followerId, setOfFollowees);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // do nothing if follower and followee are the same
        Set<Integer> setOfFollowees;
        // if null, return null
        if (followsStore.containsKey(followerId)) {
            setOfFollowees = followsStore.get(followerId);
             // remove the followeeId from the set of followees
            setOfFollowees.remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */