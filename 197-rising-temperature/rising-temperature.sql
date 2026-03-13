# Write your MySQL query statement below

/*
Given: a table called Weather:
    - id
    - recordDate
    - Temperature
Want: all dates' id with higher temperatures compared to the previous date
Approach:
    - we want to compare the previous row against the current row in 
    our SQL solution
    - we know we want only the ID, but we want to filter WHERE
    the currentRow temp is greater than previousRow
    - how do we compare two rows?
        - perhaps we can create two tables; one that is OFFSET by 1?

*/

WITH offset_weather AS (
    SELECT * FROM Weather
    ORDER BY recordDate
    LIMIT 1, 1000000
), sorted_weather AS (
    SELECT * FROM Weather
    ORDER BY recordDate
)

SELECT offset_weather.id FROM offset_weather
JOIN sorted_weather ON DATE_SUB(offset_weather.recordDate, INTERVAL 1 DAY) = sorted_weather.recordDate
WHERE offset_weather.temperature > sorted_weather.temperature;