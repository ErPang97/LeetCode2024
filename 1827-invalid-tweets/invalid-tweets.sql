# Write your MySQL query statement below
# a tweet is invalid if the content is greater than 15, strictly!
# we want to find the ids of invalid tweets
# use CHAR_LENGTH get length of string content

SELECT tweet_id
FROM Tweets
WHERE CHAR_LENGTH(content) > 15;