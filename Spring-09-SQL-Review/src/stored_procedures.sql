-- stored procedures can be used with DML commands
CREATE OR REPLACE PROCEDURE update_department(emp_id int)
    language plpgsql
    as
$$
    declare
    begin
        update employees
        set department = 'Toys'
        where employee_id = emp_id;
    end
$$;

call update_department(16);
select * from employees where employee_id = 16;

CREATE OR REPLACE PROCEDURE transfer_salary(emp_id1 int, emp_id2 int, amount int)
    language plpgsql
    as
$$
    declare
    begin

        update employees
        set salary = salary - amount
        where employee_id = emp_id1;

        update employees
        set salary = salary + amount
        where employee_id = emp_id2;

    end;
$$;

call transfer_salary(1, 2, 4864);
select * from employees where employee_id IN (1,2);