# Write your MySQL query statement below
/*
P:
    - given: two Tables:
        - Visits
        - Transactions
        - they have a one-to-one relationship
    - want: 
        - find the ID of users (customers) who visited, without 
        making any transactions
        - want:
            - customer_id from Visits
            - however, we want specifically, the count
            of times a customer visited, with 0 transactions
                - can we get the visit_id's that DO NOT have
                a matching transaction_id?
                - we want all the visit_id's
            - JOIN Transactions and Visits,
                - Perhaps Visits LEFT JOIN Transactions
                - ON visit_ids
            - somehow, filter for the NULL transaction entries
            - then COUNT the visits, grouped by customer_id
*/

SELECT customer_id, COUNT(*) AS count_no_trans FROM
Visits LEFT JOIN Transactions 
ON Visits.visit_id = Transactions.visit_id
WHERE transaction_id is null
GROUP BY customer_id;
