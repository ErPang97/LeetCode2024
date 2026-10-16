# Write your MySQL query statement below

/**
Given: 
    - a table Employee:
        - empId - int -> employee id
        - name - varchar -> name of employee
        - supervisor - int -> id of their manager
        - salary - int -> their salary
    - a table Bonus:
        - empId - int - employeeId (foreign key) 
        - bonus - int -> their bonus
Want:
    - to write a query:
        - name and bonus of each employee
        WHERE employee has a bonus < 1000
        - or did not get any bonus

Approach:
    - Finding an employee that did not get any bonus
    is not too difficult.
        - this should be handled by LEFT JOINING 
        Employee to Bonus, and keeping any empId
        that has no bonuses (meaning their bonus column is likely null)
    - Finding an employee that has a bonus of <1000, should also
    not be too difficult. Similar, we'd filter for employees
    that have a bonus < 1000
*/

-- query to select employees that have no bonuses
SELECT e.name, b.bonus FROM 
Employee e
LEFT JOIN
Bonus b
ON e.empId = b.empId
WHERE b.bonus IS NULL OR b.bonus < 1000;

