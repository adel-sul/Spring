SELECT employee_id, first_name, department FROM employees WHERE department = 'Sports';

SELECT * FROM employees WHERE department LIKE '%nit%';

SELECT * FROM employees WHERE salary > 100000;

SELECT * FROM employees WHERE department = 'Sports' AND salary > 100000;

SELECT * FROM employees WHERE email IS NOT NULL;

SELECT * FROM employees WHERE salary  BETWEEN 80000 AND 90000;

SELECT first_name, email FROM employees WHERE gender = 'F' AND department = 'Tools' AND salary > 110000;

SELECT first_name, hire_date FROM employees WHERE salary > 165000 OR department = 'Sports' AND gender = 'M';

SELECT *
FROM employees
ORDER BY employee_id;

-- display top 3 rows only
SELECT DISTINCT department
FROM employees
FETCH FIRST 3 ROWS ONLY;

-- table alias
SELECT salary as ZARPLATA
FROM employees;

SELECT  *
FROM students
ORDER BY age desc
FETCH FIRST 4 ROWS ONLY;

SELECT  *
FROM students
WHERE age <=20
AND (student_no BETWEEN 3 AND 5 OR student_no = 7 )
OR (age > 20 AND student_no >=4);

-- FUNCTIONS --
SELECT upper(first_name), lower(department)
FROM employees;

SELECT length(first_name)
FROM employees;

SELECT length(first_name)
FROM employees;

-- concatenating values
SELECT first_name || ' ' || last_name as FULL_NAME
FROM employees;

-- will show as boolean value for each row
SELECT (salary > 100000)
FROM employees;

SELECT length(first_name)
FROM employees;

SELECT SUBSTRING('THIS IS A TEST DATA' FROM 1 FOR 4) as test_data;
SELECT SUBSTRING('THIS IS A TEST DATA' FROM 5) as test_data;

-- rep[lasing values to display
SELECT department, replace(department, 'Clothing', 'Clothes')
FROM departments;

SELECT max(salary)
FROM employees;

SELECT min(salary)
FROM employees;

SELECT round(avg(salary))
FROM employees;

SELECT department, round(avg(salary))
FROM employees
GROUP BY department;

SELECT last_name, upper(substring(department, 1, 3)) department, salary, hire_date FROM professors;

-- not equals
SELECT max(salary), min(salary) FROM professors WHERE last_name <> 'Wilson';

SELECT count(*), department
FROM employees
GROUP BY department;

SELECT sum(salary), department
FROM employees
GROUP BY department
ORDER BY sum(salary) desc;

-- POSITION used as indexOf()
SELECT substring(email, position('@' IN email)+1) as domain, count(*)
FROM employees
WHERE email IS NOT NULL
GROUP BY domain
ORDER BY count(*) desc;

SELECT d.department
FROM employees e, departments d;

-- SUBQUERIES
SELECT *
FROM employees
WHERE department NOT IN (
    SELECT department
    FROM departments
    );

SELECT *
FROM (SELECT * FROM employees WHERE salary > 10000) as a;

SELECT *
FROM employees
WHERE department IN (SELECT department FROM departments WHERE division = 'Electronics');

SELECT *
FROM employees
WHERE salary > 130000
AND region_id IN (SELECT region_id FROM regions WHERE country = 'Asia' OR country = 'Canada');

SELECT first_name, (SELECT max(salary) FROM employees) - salary as difference
FROM employees
WHERE region_id IN (SELECT region_id FROM regions WHERE country = 'Asia' OR country = 'Canada');

-- ANY and ALL keywords
SELECT * FROM employees
WHERE department = ANY(SELECT department FROM departments WHERE division = 'Kids')
AND hire_date > ALL(SELECT hire_date FROM employees WHERE department = 'Maintenance');

SELECT student_name
FROM students
WHERE student_no IN (
    SELECT student_no
    FROM student_enrollment
    WHERE course_no IN (
        SELECT course_no
        FROM courses
        WHERE course_title = 'Physics'
        OR course_title = 'US History')
    );

SELECT first_name, salary,
CASE
    WHEN salary < 100000 THEN 'UNDER PAID'
    WHEN salary > 100000 THEN 'PAID WELL'
    ELSE 'UNPAID'
END as category
FROM employees;

-- JOINS
SELECT first_name, email, division, d.department
FROM employees e, departments d, regions r
WHERE e.department = d.department
AND e.region_id = r.region_id;

CREATE TABLE Towns (
                       id SERIAL UNIQUE NOT NULL,
                       code VARCHAR(10) NOT NULL, -- not unique
                       article TEXT,
                       name TEXT NOT NULL -- not unique
);