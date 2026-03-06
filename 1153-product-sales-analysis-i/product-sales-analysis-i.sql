# Write your MySQL query statement below
/*
    given two Tables: Sales, Product
    want: to report the product_name, year, price for EACH sale_id
*/


SELECT product_name, year, price FROM Sales 
JOIN Product ON Sales.product_id = Product.product_id;
