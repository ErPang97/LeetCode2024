# Write your MySQL query statement below

# P: we want to Query for all rows' product_id where 
# the corresponding low_fats  = 'Y' and recyclable = 'Y'
SELECT product_id
FROM products
WHERE low_fats = 'Y' AND recyclable = 'Y'