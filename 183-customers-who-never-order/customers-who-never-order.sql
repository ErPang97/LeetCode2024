# Write your MySQL query statement below
/*
    - Given: 
        -a table Customers
            - id - int
            - name - VARCHAR
        - a table Orders
            - id - int
            - customerId
    - Want: 
        - to query for all Customers who never ordered anything
        - this customerId is not found in the Orders

    - Approach:
        - we can use a join, and since we want to identify all the customers
        in particular, I'm thinking of a LEFT JOIN
        - so LEFT JOIN Customers to ORDERS, ON Customers.id = Orders.customerId
        - then, once we have that query, we'll want to then filter, WHERE
        customerId is NULL
*/

SELECT name AS Customers FROM Customers 
LEFT JOIN Orders
ON Customers.id = Orders.customerId
WHERE customerId IS NULL;