# Write your MySQL query statement below

/*
Given:
- a Table called Activity with the following columns:
    - player_id - int
    - device_id - int
    - event_date - date
    - games_played - int
    - (player_id, event_date) -> primary key 
- each row is a record of a player who logged in and played 
a number of games before logging out on someday using some device
Want:
    - find the FIRST Login Date for Each player
    - Result consists of 
        - player_id
        - first_login (From event_date)

Approach:
    - we know we only want the first occurrence of each player
    - a player could login via multiple different devices
    - we'd have to ignore the device_id, and keep only DISTINCT player_id's
    - we'd also want to sort the event_dates in ASC ORDER so that
    keeping DISTINCT player_id's we'd maintain the earliest record

    - OBSERVATION: 
        - it seems using DISTINCT, when the primary key is a combination of
        values, it doesn't actually ignore other values if still SELECT-ing 
        information from other columns
        - how to handle this?
        - what if we LEFT JOIN distinct ID's query to a player_id, event_date
        query, so that we only keep the columns distinct ID, dropping 
        the unneeded columns from the RIGHT table
    
    - SOLVED:
        - didn't need to do any JOINs or DISTINCT ec., a simple AGGREGATE function (MIN in this case)
        GROUP-ing BY player_id
        - forgot DATE's can be compared as well.
*/

-- get DISTINCT - player_id
-- SELECT DISTINCT player_id FROM Activity;

-- ORDER BY event_date
-- SELECT player_id, event_date FROM Activity
-- ORDER BY event_date ASC;

SELECT player_id, MIN(event_date) AS first_login
FROM Activity
GROUP BY player_id
ORDER BY event_date ASC;