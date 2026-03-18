# Write your MySQL query statement below

/*
Given: a table Activity
    machine_id
    process_id
    activity_type - either start or end
    timestamp - the time the activity took place
Want:
    average time EACH Machnie takes to complete a process
    - a table with machine_id and processing_time (rounded to 3 spots)
*/

with end_activity AS (
    SELECT * FROM Activity
    WHERE activity_type = "END"
), start_activity AS (
    SELECT * FROM Activity
    WHERE activity_type = "START"
)

SELECT start_activity.machine_id, ROUND(AVG(end_activity.timestamp - start_activity.timestamp), 3) AS processing_time
FROM start_activity INNER JOIN end_activity 
ON start_activity.machine_id = end_activity.machine_id AND start_activity.process_id = end_activity.process_id
GROUP BY start_activity.machine_id;