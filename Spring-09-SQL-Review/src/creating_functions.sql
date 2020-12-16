CREATE OR REPLACE FUNCTION get_department_count_by_name(dep_name varchar)
    returns int
    language plpgsql
    as
$$
    declare
        department_count integer;
    begin
        SELECT count(*)
        INTO department_count
        FROM employees
        WHERE department = dep_name;

        return department_count;
    end;
$$;

SELECT get_department_count_by_name('Sports');

CREATE OR REPLACE function get_department(p_pattern varchar)
    returns table (
        employee_name varchar,
        employee_email varchar
                  )
    language plpgsql
    as
$$
    begin
        return query
        select first_name, email
        from employees
        where department ilike p_pattern;
    end;
$$;

select * from get_department('%oth%');

DROP FUNCTION get_department(p_pattern varchar);


