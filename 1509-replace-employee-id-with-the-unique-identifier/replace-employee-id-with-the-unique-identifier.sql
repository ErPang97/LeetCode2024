# Write your MySQL query statement below
/**
P: 
   - we are given two tables, "Employees" and "EmployeeUNI"
   - we want to return a table with the unique_id of each employee
   along with their name
   - we note that Employees store the id and name of the employee
   and EmployeeUNI stores the unique_id
   - however, we note that some employees may not have one, so
   it may be null (if not found in EmployeeUNI table)
   - in that case just display null
A: 
   - in this case, we LEFT JOIN Employees with EmployeeUNI, as
   we want to keep all employees, even if they don't have unique_id's
*/

SELECT unique_id, Employees.name
FROM Employees
LEFT JOIN EmployeeUNI ON Employees.id = EmployeeUNI.id;