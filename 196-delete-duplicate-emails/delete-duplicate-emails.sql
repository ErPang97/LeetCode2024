# Write your MySQL query statement below

/*
P:
    - GivenL a table Person with the columns
        - id - int
        - email - varchar
    - Want:
        - DELETE all duplicate emails, keeping only ONE of the unique emails with
        the smallest id
A:
    - First, we need to find what id's of the duplicate emails
    - ORIGINAL approach:
        - I used MIN(id), grouping by email to find the min_id per email
        - then, I DELETED using a subquery
    - FOLLOW-UP Approach:
        - join on itself to find the ID's that are considered duplicates
        - 
*/

-- Approach 1:
-- WITH cte AS(SELECT MIN(id) AS min_id
-- FROM Person
-- GROUP BY email)

-- DELETE FROM Person
-- WHERE id NOT IN (SELECT * FROM cte);

-- Approach 2:
DELETE p1 # we delete p1, which are those that have p1.id > p2.id
FROM Person p1
JOIN Person p2 # cross-join, cartesian product, every corresponding row
ON p1.email = p2.email
AND p1.id > p2.id; 