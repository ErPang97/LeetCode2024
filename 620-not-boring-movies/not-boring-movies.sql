# Write your MySQL query statement below

/*
Given: a table called Cinema
    - id
    - movie
    - description
    - rating
Want: 
    - to SELECT the rows WHERE description IS NOT "boring"
    - and the movies are odd-numbered ID's
    - ORDER BY Rating in DESC order
*/

SELECT * FROM Cinema
WHERE NOT description = "boring" 
AND id%2!=0
ORDER BY rating DESC;